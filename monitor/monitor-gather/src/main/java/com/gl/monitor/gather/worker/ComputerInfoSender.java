package com.gl.monitor.gather.worker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.gl.monitor.gather.config.SharedObjectsHolder;
import com.gl.monitor.gather.util.DateTimeUtils;
import com.gl.monitor.gather.vo.ComputerInfo;
import com.gl.monitor.gather.vo.Msg;
import com.gl.monitor.gather.vo.MsgHeader;

public class ComputerInfoSender {
	private static final Logger log = LoggerFactory.getLogger(ComputerInfoSender.class);

	@Autowired
	private SharedObjectsHolder holder;
	private BlockingQueue<ComputerInfo> infoQueue;

	private String remoteServerHost = "localhost";
	private String remoteServerPort = "19009";
	private String remoteServerContext = "monitor";
	private String remoteServerPath = "messages";

	private String srcSystemId = System.getProperty("sysId", "client-00");

	public ComputerInfoSender() {
		String host = System.getProperty("remoteServerHost");
		if (StringUtils.isNotBlank(host)) {
			remoteServerHost = host.trim();
		}

		String port = System.getProperty("remoteServerPort");
		if (StringUtils.isNotBlank(port)) {
			remoteServerPort = port.trim();
		}

		String context = System.getProperty("remoteServerContext");
		if (StringUtils.isNotBlank(context)) {
			remoteServerContext = context.trim();
		}

		String path = System.getProperty("remoteServerPath");
		if (StringUtils.isNotBlank(path)) {
			remoteServerPath = path.trim();
		}

		log.info(String.format("http://%s:%s/%s/%s", remoteServerHost, remoteServerPort, remoteServerContext,
				remoteServerPath));
	}

	@PostConstruct
	public void execute() {
		infoQueue = holder.getInfoQueue();
		new Thread(new ComputerInfoProcessor()).start();
	}

	private class ComputerInfoProcessor implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					ComputerInfo info = infoQueue.take();
					send(info);
				} catch (InterruptedException e) {
					log.error("", e);
				} catch (ClientProtocolException e) {
					log.error("", e);
				} catch (IOException e) {
					log.error("", e);
				}

			}

		}

		protected void send(ComputerInfo info) throws ClientProtocolException, IOException {
			log.info(String.format("Send:%s", info));

			Msg msg = buildMsg(info);
			String msgContent = JSONObject.toJSONString(msg);

			log.info(String.format("SEND:%s", msgContent));

			String strURL = String.format("http://%s:%s/%s/%s", remoteServerHost, remoteServerPort, remoteServerContext,
					remoteServerPath);
			HttpPost post = new HttpPost(strURL);

			HttpEntity httpEntity = new StringEntity(msgContent, ContentType.APPLICATION_JSON);

			post.setEntity(httpEntity);

			HttpClient httpClient = HttpClients.createDefault();

			HttpResponse resp = httpClient.execute(post);

			HttpEntity respEntity = resp.getEntity();
			InputStream content = respEntity.getContent();

			byte[] buf = new byte[1024];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = content.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

			content.close();

			log.info(String.format("RECV:%s", bos.toString()));
		}

		protected String genMsgId(ComputerInfo info) {
			return UUID.randomUUID().toString();
		}

		protected Msg buildMsg(ComputerInfo info) {
			Msg msg = new Msg();

			MsgHeader header = new MsgHeader();
			header.setMsgId(genMsgId(info));
			header.setTargetSysId("server-10080");
			header.setSrcSysId(srcSystemId);

			msg.setHeader(header);

			try {
				InetAddress localInetAddr = InetAddress.getLocalHost();
				info.setIpAddr(calLocalIpAddress());
				info.setHostName(localInetAddr.getHostName());
			} catch (UnknownHostException e) {
				log.error("", e);
			} catch (SocketException e) {
				log.error("", e);
			}

			Date now = new Date();
			info.setCreateOn(DateTimeUtils.date2string(now));
			info.setCreateAt(DateTimeUtils.time2string(now));
			info.setOprDate(DateTimeUtils.date2string(now));
			info.setOprTime(DateTimeUtils.time2string(now));

			msg.setBody(info);

			return msg;
		}

		protected String calLocalIpAddress() throws SocketException {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			String localIpAddr = "";
			System.out.println("interfaces:" + interfaces.hasMoreElements());
			while (interfaces.hasMoreElements()) {
				NetworkInterface inter = interfaces.nextElement();
				Enumeration<InetAddress> inets = inter.getInetAddresses();
				while (inets.hasMoreElements()) {
					InetAddress inet = inets.nextElement();
					System.out.println("inet class:" + inet.getClass().getName());
					System.out.println("inet:" + inet.getHostAddress());
					System.out.println("isSiteLocalAddress:" + inet.isSiteLocalAddress());
					System.out.println("isLoopbackAddress:" + inet.isLoopbackAddress());
					System.out.println("isLinkLocalAddress:" + inet.isLinkLocalAddress());
					if (inet.isSiteLocalAddress() && !inet.isLoopbackAddress()
							&& (inet.getHostAddress().indexOf(":") == -1)) {
						localIpAddr = inet.getHostAddress();
						System.out.println("localIpAddr:" + localIpAddr);
						break;
					}
				}

				if (localIpAddr != null && !"".equals(localIpAddr)) {
					break;
				}
			}

			return localIpAddr;
		}

	}

}
