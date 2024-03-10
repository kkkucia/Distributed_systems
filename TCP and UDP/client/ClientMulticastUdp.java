package client;

import java.net.*;
import java.util.Objects;
import java.util.logging.Logger;

import static server.ServerUdpClientsHandler.BUFFER_SIZE;

public class ClientMulticastUdp extends Thread {
    private MulticastSocket multicastSocket;
    private InetAddress group;
    private static final String MULTICAST_GROUP_ADDRESS = "230.0.0.1";
    public static final int MULTICAST_GROUP_PORT = 12346;
    private final Logger logger = Logger.getLogger(ClientMulticastUdp.class.getName());

    private final String ASCII_ART_DATA = """
              BAT
                              -.                       .-
                          _..-'(                       )`-.._
                       ./'. '||\\\\.      |\\ _ /|       .//||` .`\\.
                    ./'.|'.'||||\\\\|..   \\'o.O'/  ..|//||||`.`|.`\\.
                 ./'..|'.|| |||||\\``````=(___)=''''''/||||| ||.`|..`\\.
               ./'.||'.|||| ||||||||||||.  U  .|||||||||||| ||||.`||.`\\.
              /'|||'.|||||| |||||||||||||     ||||||||||||| ||||||.`|||`\\
             '.|||'.||||||| |||||||||||||     ||||||||||||| |||||||.`|||.`
            '.||| ||||||||| |/'   ``\\||``     ''||/''   `\\| ||||||||| |||.`
            |/' \\./'     `\\./         \\!|\\   /|!/         \\./'     `\\./ `\\|
            V    V         V          }' `\\ /' `{          V         V    V
            `    `         `               V               '         '    '
            """;

    @Override
    public void run() {
        try {
            multicastSocket = new MulticastSocket(MULTICAST_GROUP_PORT);
            group = InetAddress.getByName(MULTICAST_GROUP_ADDRESS);
            multicastSocket.joinGroup(group);

            receiveMulticastMultimediaMessage();
        } catch (Exception e) {
            logger.severe("[Client] Client Multicast Udp connection error: " + e.getMessage());
        }
    }

    public void sendMulticastMultimediaMessage() {
        try {
            byte[] buffer = ASCII_ART_DATA.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, MULTICAST_GROUP_PORT);
            multicastSocket.send(packet);
        } catch (Exception e) {
            logger.severe("Error while sending multicast multimedia message by client via UDP socket: " + e.getMessage());
        }
    }

    private void receiveMulticastMultimediaMessage() {
        try {
            byte[] receiveBuffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                multicastSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received multimedia udp message from multicast /" + MULTICAST_GROUP_ADDRESS + ":" + MULTICAST_GROUP_PORT
                                   + ".\n" + receivedMessage);

                logger.info("Multimedia udp multicast message received!");
            }

        } catch (Exception e) {
            if (!Objects.equals(e.getMessage(), "Socket closed")) {
                logger.severe("Error while receiving multicast multimedia message by client via UDP socket: " + e.getMessage());
            }
        }
    }

    public void close() {
        try {
            if (multicastSocket != null) {
                logger.info("You left multicast group: " + group + ":" + MULTICAST_GROUP_PORT);
                multicastSocket.leaveGroup(group);
                multicastSocket.close();
            }
        } catch (Exception e) {
            logger.severe("Error while leaving multicast group and closing UDP socket: " + e.getMessage());
        }
    }
}
