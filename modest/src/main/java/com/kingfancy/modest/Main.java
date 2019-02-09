package com.kingfancy.modest;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import com.kingfancy.modest.Server;

/**
 * Modest HTTP
 * A simple, yet robust HTTP server.
 * 
 * Author: Spencer Burgess
 * Version: 0.01
 * License: GNU GENERAL PUBLIC LICENSE
 */
public class Main {
	
	static final int PORT = 8080;
	static final boolean verbose = true;
	
    public static void main( String[] args ) {
    	
    	try {
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server started. Listening on port " + PORT);
			
			// we listen until user halts server execution
			while (true) {
				Server myServer = new Server(serverConnect.accept());
				
				if (verbose) {
					System.out.println(new Date() + " - Connecton opened");
				}
				
				// create dedicated thread to manage each client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}
			
		} catch (IOException e) {
			System.err.println("Connection error : " + e.getMessage());
		}
    }
}
