package server;

import client.ClientChatData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

public class ServerUdpClientsHandler extends Thread {
    private static DatagramSocket serverUdpSocket;
    public static final int BUFFER_SIZE = 1024;
    private final byte[] buffer = new byte[BUFFER_SIZE];
    private final Map<UUID, ClientChatData> serverClients;
    private final Lock lock;
    private static final Logger logger = Logger.getLogger(ServerUdpClientsHandler.class.getName());

    public ServerUdpClientsHandler(DatagramSocket serverUdpSocket, Map<UUID, ClientChatData> serverClients, Lock lock) {
        this.serverUdpSocket = serverUdpSocket;
        this.serverClients = serverClients;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                serverUdpSocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress address = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                logger.info("[SERVER] Client from " + address + ":" + clientPort + " has sent multimedia udp message to all clients.");
                sendMultimediaMessageToAllClients(receivedMessage, clientPort, receivePacket.getAddress());
            }
        } catch (Exception e) {
            logger.severe("[SERVER] Client udp error: " + e.getMessage());
        } finally {
            if (serverUdpSocket != null) {
                serverUdpSocket.close();
            }
        }
    }

    private void sendMultimediaMessageToAllClients(String message, int clientPort, InetAddress clientAddress) {
        lock.lock();
        try {
            UUID clientID = getClientId(clientPort);
            if (clientID != null) {
                message = clientID + ":" + message;
            }
            byte[] sendBuffer = message.getBytes();
            serverClients.values().forEach(clientChatData -> {
                if (clientChatData.getPort() != clientPort) {
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientChatData.getPort());
                    try {
                        serverUdpSocket.send(sendPacket);
                    } catch (IOException e) {
                        logger.warning("Error while sending multimedia data via server UDP socket: " + e.getMessage());
                    }
                }
            });
        } finally {
            lock.unlock();
        }
    }

    private UUID getClientId(int port) {
        return serverClients.entrySet().stream()
                .filter(clientChatDataEntry -> clientChatDataEntry.getValue().getPort() == port)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
