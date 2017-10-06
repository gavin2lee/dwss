package com.gl.sync.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSyncServer {
    private String localFileDir = "/home/gavin/Dev/Books";
    private String port = "10035";
    private AtomicBoolean stopServer = new AtomicBoolean(false);

    private List<Socket> clients = new ArrayList<Socket>();
    
    public boolean stopServer(){
        return stopServer.compareAndSet(false, true);
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(Integer.parseInt(port));
        System.out.println("server listen on " + port);
        while (true) {

            if (stopServer.get()) {
                for (Socket s : clients) {
                    if (!s.isClosed()) {
                        s.close();
                    }
                }

                break;
            }

            for (Socket s : clients) {
                if (s.isClosed()) {
                    clients.remove(s);
                }
            }

            Socket client = server.accept();

            clients.add(client);

            new FileSyncHandler(client, this, localFileDir).start();
        }

        server.close();

        System.out.println("exit normally");
    }
    
    public static void main(String...args){
        try {
            new FileSyncServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
