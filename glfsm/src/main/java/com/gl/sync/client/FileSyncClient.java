package com.gl.sync.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.sync.CommonUtils;

public class FileSyncClient {
    private String parentDir = "/home/gavin/Dev/Tmp";
    private String serverPort = "10035";

    public static int CONTENT_TYPE_STRING = 0;
    public static int CONTENT_TYPE_BIT = 1;
    public static int CONTENT_TYPE_EOF = -1;

    private int timeout = 60 * 1000;

    private static final Logger LOG = LoggerFactory.getLogger(FileSyncClient.class);

    private void handleSingleDownload(Socket socket, String bookName) throws Exception {
        LOG.info("handle single : " + bookName);
        InputStream socketIn = null;
        OutputStream fileOut = null;

        int outLen = bookName.getBytes("utf-8").length;
        OutputStream socketOut = socket.getOutputStream();
        socketOut.write(CommonUtils.intToBytes(outLen));
        socketOut.write(CommonUtils.intToBytes(CONTENT_TYPE_STRING));
        socketOut.write(bookName.getBytes("utf-8"));
        socketOut.flush();

        byte[] recvBuf = new byte[2048];

        socketIn = socket.getInputStream();

        int recvLen = Integer.MIN_VALUE;

        String fileName = null;
        File targetFile = null;

        byte[] buf = new byte[4096];
        int headLen = Integer.MIN_VALUE;
        int headType = Integer.MIN_VALUE;
        // int recvBufLen = 0;

        int bufLen = 0;

        boolean recvBreak =false;
        while ((recvLen = socketIn.read(recvBuf)) != -1) {
            LOG.info("recv len:" + recvLen);
            if (buf.length < (bufLen + recvLen)) {
                LOG.info("buf need expand to " + (bufLen + recvLen));
                byte tmpBuf[] = new byte[bufLen + recvLen];
                System.arraycopy(buf, 0, tmpBuf, 0, bufLen);
                buf = tmpBuf;
            }
            System.arraycopy(recvBuf, 0, buf, bufLen, recvLen);
            bufLen = (bufLen + recvLen);

            if (bufLen < 8) {
                LOG.info("less than 8");
                continue;
            }

            byte bytesHeadLen[] = new byte[4];
            System.arraycopy(buf, 0, bytesHeadLen, 0, 4);

            headLen = CommonUtils.bytesToInt(bytesHeadLen, 0);

            if (bufLen < (headLen + 8)) {

                LOG.info("less than " + (headLen + 8));
                continue;
            }

            while ((headLen + 8) <= bufLen) {
                
                byte bytesHeadType[] = new byte[4];
                byte[] bytesToProc = new byte[headLen];

                System.arraycopy(buf, 4, bytesHeadType, 0, 4);
                System.arraycopy(buf, 8, bytesToProc, 0, headLen);
                headType = CommonUtils.bytesToInt(bytesHeadType, 0);

                bufLen = bufLen - headLen - 8;
                LOG.info("bufLen:"+bufLen);
                if (bufLen > 0) {
                    System.arraycopy(buf, (headLen + 8), buf, 0, bufLen);
                }
                
                if(bufLen >= 8){
                    System.arraycopy(buf, 0, bytesHeadLen, 0, 4);
                    headLen = CommonUtils.bytesToInt(bytesHeadLen, 0);
                }
                
                LOG.info(String.format("recv Len:%s - Type:%s", headLen, headType));
                
                if (headType == CONTENT_TYPE_STRING) {
                    fileName = new String(bytesToProc, "utf-8");
                    LOG.info("File Name:" + fileName);
                    targetFile = new File(parentDir, fileName);

                    if (!targetFile.exists()) {
                        targetFile.createNewFile();
                    }

                    fileOut = new FileOutputStream(targetFile);
                } else if (headType == CONTENT_TYPE_BIT) {
                    if (fileOut != null) {
                        fileOut.write(bytesToProc);
                        LOG.info("Write " + bytesToProc.length);
                    }
                } else if (headType == CONTENT_TYPE_EOF) {
                    LOG.info("Finished " + fileName);
                    fileOut.close();
                    
                    recvBreak = true;
                    break;
                }
            }
            
            if(recvBreak){
                LOG.info("break");
                break;
            }
        }
    }

    public void start() throws Exception {
        List<String> bookNames = new ArrayList<String>();

        bookNames.add("亿级流量网站架构核心技术.pdf");
        bookNames.add("企业应用架构模式-带书签.pdf");
        bookNames.add("大数据架构师指南.pdf");
        bookNames.add("敏捷软件开发：原则模式和实践.pdf");
        bookNames.add("理解OAuth 2.pdf");

        Socket socket = new Socket();
        InetSocketAddress endpoint = new InetSocketAddress("127.0.0.1", Integer.parseInt(serverPort));

        socket.connect(endpoint, timeout);

        for (String bookName : bookNames) {
            handleSingleDownload(socket, bookName);
        }

        OutputStream socketOut = socket.getOutputStream();
        socketOut.write(CommonUtils.intToBytes(0));
        socketOut.write(CommonUtils.intToBytes(CONTENT_TYPE_EOF));
        socketOut.flush();

        socket.shutdownOutput();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            new FileSyncClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
