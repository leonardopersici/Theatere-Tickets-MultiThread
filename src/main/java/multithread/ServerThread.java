package multithread;

import java.io.*;
import java.net.*;

import static java.lang.Integer.parseInt;

public class ServerThread extends Thread {
    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    String bookRequest;

    // the constructor argument is an established socket
    public ServerThread(Socket s) {
        connectionSocket = s;
        try {
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));
            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String clientSentence;
        String capitalizedSentence;
        try {

            // wait for 10 seconds
            Thread.sleep(5000);

            if (Reservations.freeSeats() > 0) {
                Reservations.seats --;
                bookRequest = "Biglietto prenotato. Rimangono " + Reservations.seats + " biglietti disponibili";
            }
            else
                bookRequest = "Mi spiace, i biglietti sono esauriti";
            outToClient.writeBytes(bookRequest);

            connectionSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
