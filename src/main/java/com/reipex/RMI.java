package com.reipex;

import java.net.*;
import java.util.*;

/**
 * The RMI object handles or delegates network communication for all RMI requests.
 * RMI requests are stored in a public class variable, and RMI responses are placed into another public class variable.
 * In the future, a queue system will exist, allowing for high-volume RMI calls.
 * Additionally, the RMI currently provides no security. In the final release, RMI will require authentication.
 * RMI calls are used to send and receive coins, as well as publish blocks.
 */
public class RMI extends Thread {
    private int listenPort;
    public String request = null;

    public List<RMIThread> RMIThreads;

    public boolean shouldRun = true;

    /**
     * Standard RMI port is 8016, one above the P2P networking port.
     */
    public RMI() {
        this.listenPort = 8016;
        this.RMIThreads = new ArrayList<>();
    }

    /**
     * Alternate, currently-unused constructor to listen on a non-standard RMI port.
     *
     * @param listenPort Port to listen on
     */
    public RMI(int listenPort) {
        this.listenPort = listenPort;
        this.RMIThreads = new ArrayList<>();
    }

    /**
     * Starts listening and handles RPCThreads.
     */
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(listenPort);
            while (shouldRun) {
                RMIThreads.add(new RMIThread(socket.accept()));
                RMIThreads.get(RMIThreads.size() - 1).start();
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

