package com.reipex;

import java.io.*;
import java.net.*;

/**
 * RPCThreads are server threads, allowing client communication with the daemon.
 */
public class RMIThread extends Thread {
    private Socket socket;

    public String request;
    public String response;

    /**
     * RMI Threads require a socket to function.
     *
     * @param socket Socket
     */
    public RMIThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Manages the server<->client communication.
     */
    public void run() {
        try {
            request = null;
            response = null;
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input = "";
            out.println("Reipex");
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("HELP")) {
                    out.println("Commands: ");
                    out.println("send <amount> <destination>");
                    out.println("getinfo");
                    out.println("getbalance <address>");
                    out.println("submittx <rawtx>");
                    //out.println("submitcert <cert>");
                    out.println("gethistory <address>");
                    out.println("");
                } else {
                    request = input;
                    while (response == null) {
                        Thread.sleep(25);
                    }
                    out.println(response + "\n</>");
                    request = null;
                    response = null;
                }
            }
        } catch (Exception e) {
            System.out.println("An RMI client has disconnected.");
            //e.printStackTrace();
        }
    }
}
