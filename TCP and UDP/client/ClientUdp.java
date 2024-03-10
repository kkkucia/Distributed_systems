package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;
import java.util.logging.Logger;

import static server.ServerConfig.SERVER_ADDRESS;
import static server.ServerConfig.SERVER_PORT_NUMBER;
import static server.ServerUdpClientsHandler.BUFFER_SIZE;

public class ClientUdp extends Thread {
    private static DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;
    private static final Logger logger = Logger.getLogger(ClientUdp.class.getName());

    private final String ASCII_ART_DATA = """
              MERMAID
                                       .-""-.
                                      (___/\\ \\
                    ,                 (|^ ^ ) )
                   /(                _)_\\=_/  (
             ,..__/ `\\          ____(_/_ ` \\   )
              `\\    _/        _/---._/(_)_  `\\ (
                '--\\ `-.__..-'    /.    (_), |  )
                    `._        ___\\_____.'_| |__/
                       `~----"`   `-.........'
            """;

    public ClientUdp(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            udpSocket = new DatagramSocket(port);
            serverAddress = InetAddress.getByName(SERVER_ADDRESS);

            receiveMultimediaMessage();

        } catch (IOException e) {
            logger.severe("[Client] Client Udp connection error: " + e.getMessage());
        } finally {
            if (udpSocket != null) {
                udpSocket.close();
            }
        }
    }

    void sendMultimediaMessage() {
        try {
            byte[] sendBuffer = ASCII_ART_DATA.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, SERVER_PORT_NUMBER);
            udpSocket.send(sendPacket);

            logger.info("Multimedia udp message sent!");
        } catch (Exception e) {
            logger.severe("Error while sending multimedia message by client via UDP socket: " + e.getMessage());
        }
    }

    private void receiveMultimediaMessage() {
        try {
            byte[] receiveBuffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(receivedMessage);

                logger.info("Multimedia udp message received!");
            }
        } catch (Exception e) {
            if (!Objects.equals(e.getMessage(), "Socket closed")) {
                logger.severe("Error while receiving multimedia message by client via UDP socket: " + e.getMessage());
            }
        }
    }

    public void close() {
        try {
            logger.info("Your UDP connection is closed.");
            if (udpSocket != null) {
                udpSocket.close();
            }
        } catch (Exception e) {
            logger.severe("Error while closing UDP client socket: " + e.getMessage());
        }
    }
}
