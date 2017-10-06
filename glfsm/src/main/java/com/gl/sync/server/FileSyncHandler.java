package com.gl.sync.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.sync.CommonUtils;

public class FileSyncHandler extends Thread {
    private Socket client;
    private FileSyncServer fsServer;
    private String parentDir;

    public static int CONTENT_TYPE_STRING = 0;
    public static int CONTENT_TYPE_BIT = 1;
    public static int CONTENT_TYPE_EOF = -1;
    
    private static final Logger LOG = LoggerFactory.getLogger(FileSyncHandler.class);

    public FileSyncHandler(Socket client, FileSyncServer fsServer, String parentDir) {
        super();
        this.client = client;
        this.fsServer = fsServer;
        this.parentDir = parentDir;
    }

    private void handleSingleRequest(String fileName) {
        System.out.println("handle " + fileName);
        byte[] buf = new byte[2040];
        OutputStream socketOut = null;
        InputStream fileIn = null;

        // String fileName = "亿级流量网站架构核心技术.pdf";

        try {
            byte[] nameBytes = fileName.getBytes("utf-8");
            int len = nameBytes.length;
            
            LOG.info("Send File name:"+fileName);
            File fileToSend = new File(parentDir, fileName);
            socketOut = client.getOutputStream();
            socketOut.write(CommonUtils.intToBytes(len));
            socketOut.write(CommonUtils.intToBytes(CONTENT_TYPE_STRING));
            socketOut.write(nameBytes);
            
            socketOut.flush();

            System.out.println("write len:" + len);
            fileIn = new FileInputStream(fileToSend);
            System.out.println("write len:" + fileIn.available());
            while ((len = fileIn.read(buf)) != -1) {
                socketOut.write(CommonUtils.intToBytes(len));
                socketOut.write(CommonUtils.intToBytes(CONTENT_TYPE_BIT));
                socketOut.write(buf, 0, len);

                LOG.info("server write len:"+len);
            }
            
            
            LOG.info("finished "+ fileName);

            len = 0;
            socketOut.write(CommonUtils.intToBytes(len));
            socketOut.write(CommonUtils.intToBytes(CONTENT_TYPE_EOF));

            System.out.println("write len:" + len);
            LOG.info("send EOF "+ fileName);

            socketOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle() throws IOException {
        InputStream socketIn = client.getInputStream();

        byte[] buf = new byte[2048];
        int headLen = Integer.MIN_VALUE;
        int headType = Integer.MIN_VALUE;

        int bufLen = 0;
        int recvLen = Integer.MIN_VALUE;
        byte[] recvBuf = new byte[2048];

        while ((recvLen = socketIn.read(recvBuf)) != -1) {
            if (buf.length < (bufLen + recvLen)) {
                System.out.println("buf need expand to " + (bufLen + recvLen));
                byte tmpBuf[] = new byte[bufLen + recvLen];
                System.arraycopy(buf, 0, tmpBuf, 0, bufLen);
                buf = tmpBuf;
            }
            System.arraycopy(recvBuf, 0, buf, bufLen, recvLen);
            bufLen = (bufLen + recvLen);

            if (bufLen < 8) {
                System.out.println("less than 8");
                continue;
            }

            byte bytesHeadLen[] = new byte[4];
            System.arraycopy(buf, 0, bytesHeadLen, 0, 4);

            headLen = CommonUtils.bytesToInt(bytesHeadLen, 0);
            
            

            if (bufLen < (headLen + 8)) {

                System.out.println("less than " + (headLen + 8));
                continue;
            }

            byte bytesHeadType[] = new byte[4];
            byte[] bytesToProc = new byte[headLen];

            System.arraycopy(buf, 4, bytesHeadType, 0, 4);
            System.arraycopy(buf, 8, bytesToProc, 0, headLen);
            headType = CommonUtils.bytesToInt(bytesHeadType, 0);

            if (bufLen > (headLen + 8)) {
                bufLen = bufLen - headLen - 8;
//                byte[] bytesRemain = new byte[bufLen];
                System.arraycopy(buf, (headLen + 8), buf, 0, bufLen);
//                buf = bytesRemain;
            } else {
                bufLen = 0;
            }

            if (headType == CONTENT_TYPE_STRING) {
                String fileName = new String(bytesToProc, "utf-8");
                LOG.info("handle "+fileName);

                handleSingleRequest(fileName);
                
                LOG.info("Finished handle "+fileName);

            } else if (headType == CONTENT_TYPE_BIT) {
                System.out.println("Wrong content type : " + headType);
            } else if (headType == CONTENT_TYPE_EOF) {
                System.out.println("finished");
                client.close();
            }
        }
    }

    @Override
    public void run() {

        try {
            handle();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fsServer.stopServer()) {
            System.out.println("Server to stop");

            Socket s;
            try {
                s = new Socket("127.0.0.1", 10035);
                s.getOutputStream().write("bye".getBytes());
                s.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Server to stop failed");
        }
    }

}
