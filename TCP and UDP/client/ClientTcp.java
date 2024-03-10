package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import static server.ServerConfig.*;

public class ClientTcp extends Thread {
    private BufferedReader serverInput;
    private PrintWriter out;
    private int port;
    private final Logger logger = Logger.getLogger(ClientTcp.class.getName());

    @Override
    public void run() {
        try (Socket serverSocket = new Socket(SERVER_ADDRESS, SERVER_PORT_NUMBER)) {
            port = serverSocket.getLocalPort();
            establishConnection(serverSocket);
            receiveMessages();
        } catch (IOException e) {
            logger.severe("[Client] Client Tcp connection error: " + e.getMessage());
        }
    }

    private void establishConnection(Socket serverSocket) throws IOException {
        serverInput = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);

        logger.info("[Client] You are at " + serverSocket.getLocalSocketAddress());
    }

    void receiveMessages() {
        try {
            String message;
            while ((message = serverInput.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            logger.severe("Error while client receive message: " + e.getMessage());
        }
    }

    void sendMessages(String userMessage) {
        out.println(userMessage);
        logger.info("Message sent! Message content: \"" + userMessage + "\"");
    }

    public void close() {
        try {
            logger.info("Your TCP connection is closed.");
            out.println(QUIT_SEQUENCE);
        } catch (Exception e) {
            logger.severe("Error while closing TCP client socket: " + e.getMessage());
        }
    }

    public int getPort() {
        return port;
    }
}
