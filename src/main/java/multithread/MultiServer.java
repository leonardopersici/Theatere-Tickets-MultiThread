package multithread;

import java.io.*;
import java.net.*;

class MultiServer {
    public static void main(String argv[]) throws Exception {
        int totBiglietti;
        // input stream initialization (from user keyboard)
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("\n SERVER STARTS");
        System.out.println("Inserire il numero totale di biglietti: ");
        totBiglietti = Integer.parseInt(inFromUser.readLine());

        new Reservations(totBiglietti);

        System.out.println("\n BIGLIETTI DISPONIBILI: " + Reservations.freeSeats());

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();

            // thread creation passing the established socket as arg
            ServerThread theThread =
                    new ServerThread(connectionSocket);

            // start of the thread
            theThread.start();
        }
    }
}
