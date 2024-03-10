package server;

import client.ClientChatData;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static server.ServerConfig.SERVER_ADDRESS;
import static server.ServerConfig.SERVER_PORT_NUMBER;

public class Server {
    private static final Map<UUID, ClientChatData> clients = new HashMap<>();
    private static final Lock lock = new ReentrantLock();
    private static final Logger logger = Logger.getLogger(ServerTcpClientHandler.class.getName());

    public static void main(String[] args) {
        ServerSocket serverTcpSocket;
        DatagramSocket serverUdpSocket;

        try {
            serverTcpSocket = new ServerSocket(SERVER_PORT_NUMBER);
            logger.info("[SERVER] TCP Connection established");

            serverUdpSocket = new DatagramSocket(SERVER_PORT_NUMBER);
            logger.info("[SERVER] UDP Connection established");
            handleUdpConnection(serverUdpSocket);

            System.out.println("Chat server is waiting for clients...");

            logger.info("[SERVER] Chat Server started at " + SERVER_ADDRESS + ":" + serverTcpSocket.getLocalPort());
            handleClientConnection(serverTcpSocket);

        } catch (IOException e) {
            logger.severe("Error while starting server socket: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void handleUdpConnection(DatagramSocket serverUdpSocket) {
        Thread udpServer = new ServerUdpClientsHandler(serverUdpSocket, clients, lock);
        udpServer.start();
    }

    private static void handleClientConnection(ServerSocket serverTcpSocket) throws IOException {
        while (true) {
            try {
                Socket clientSocket = serverTcpSocket.accept();
                UUID clientId = UUID.randomUUID();
                ServerTcpClientHandler clientHandler = new ServerTcpClientHandler(clientSocket, clients, clientId, lock);
                clients.put(clientId, ClientChatData.builder()
                        .clientId(clientId)
                        .serverTcpHandler(clientHandler)
                        .port(clientSocket.getPort())
                        .build());
                clientHandler.start();
            } catch (IOException e) {
                logger.severe("[SERVER] Client connection error: " + e.getMessage());
            }
        }

    }
}