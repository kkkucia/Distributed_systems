package server;

import client.ClientChatData;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

import static server.ServerConfig.QUIT_SEQUENCE;

public class ServerTcpClientHandler extends Thread {
    private final UUID chatClientId;
    private final Socket clientTcpSocket;
    private final Map<UUID, ClientChatData> serverClients;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;
    private final Lock lock;
    private static final Logger logger = Logger.getLogger(ServerTcpClientHandler.class.getName());

    public ServerTcpClientHandler(Socket clientSocket, Map<UUID, ClientChatData> serverClients, UUID chatClientId, Lock lock) {
        this.clientTcpSocket = clientSocket;
        this.serverClients = serverClients;
        this.chatClientId = chatClientId;
        this.lock = lock;
        this.running = true;
    }

    @Override
    public void run() {
        try {
            establishStreams();

            logServerInformation("connected. Client ID: " + chatClientId);

            notifyClientsAboutNewConnection();

            handleIncomingMessages();

        } catch (IOException e) {
            logger.info("[SERVER] Client with Id: " + chatClientId + " has left the chat");
        }
    }

    private void establishStreams() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientTcpSocket.getInputStream()));
        out = new PrintWriter(clientTcpSocket.getOutputStream(), true);
    }

    private void notifyClientsAboutNewConnection() {
        lock.lock();
        try {
            serverClients.values().forEach(clientChatData -> {
                if (clientChatData.getClientId() != chatClientId) {
                    clientChatData.getServerTcpHandler().sendMessage(
                            "Client with ID " + chatClientId.toString() + " has joined the chat.");
                }
            });
        } finally {
            lock.unlock();
        }
    }

    private void notifyClientsAboutDisconnection() {
        lock.lock();
        try {
            serverClients.values().forEach(clientChatData -> {
                if (clientChatData.getClientId() != chatClientId) {
                    clientChatData.getServerTcpHandler().sendMessage("Client with ID " + chatClientId.toString() + " is disconnected");
                }
            });
        } finally {
            lock.unlock();
        }
    }

    private void sendMessageToAllClients(String message) {
        lock.lock();
        try {
            serverClients.values().forEach(clientChatData -> {
                if (clientChatData.getServerTcpHandler() != this) {
                    clientChatData.getServerTcpHandler().sendMessage(chatClientId.toString() + ": " + message);
                }
            });
        } finally {
            lock.unlock();
        }
    }

    private void handleIncomingMessages() throws IOException {
        String message;
        while (((message = in.readLine()) != null) && running) {
            if (quitMessageWasSent(message)) {
                closeConnection();
            } else {
                sendMessageToAllClients(message);
                logServerInformation("has sent message to all clients");
            }
        }
    }

    private void sendMessage(String message) {
        out.println(message);
    }

    private boolean quitMessageWasSent(String message) {
        return Objects.equals(message, QUIT_SEQUENCE);
    }

    private void closeConnection() {
        try {
            clientTcpSocket.close();
            removeClientFromServer();
            notifyClientsAboutDisconnection();
            running = false;
        } catch (IOException e) {
            logger.severe("Error while closing client socket: " + e.getMessage());
        }
    }

    private void removeClientFromServer() {
        lock.lock();
        try {
            serverClients.remove(chatClientId);
        } finally {
            lock.unlock();
        }
    }

    private void logServerInformation(String info) {
        logger.info("[SERVER] Client from " + serverClients.get(chatClientId).getServerTcpHandler().getClientTcpSocketAddress() + " " + info + ".");
    }

    public String getClientTcpSocketAddress() {
        return clientTcpSocket.getLocalAddress() + ":" + clientTcpSocket.getPort();
    }
}
