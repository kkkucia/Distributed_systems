package client;

import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.System.exit;


public class ClientChat {
    private static ClientTcp clientTcp;
    private static ClientUdp clientUdp;
    private static ClientMulticastUdp clientMulticastUdp;
    private static boolean isRunning = true;
    private static String SPLITTER = " ";
    private static String CHAT_RULES = """
                CHAT RULES:
            1. [Send TCP message] - type "T" & one white symbol & your message then press enter
            2. [Send UDP message (ASCII ART)] - type "U" & press enter
            3. [Send Multicast UDP message (ASCII ART)] - type "M" & press enter
            4. [Quit chat] - type "Q" & press enter""";
    private static final Logger logger = Logger.getLogger(ClientChat.class.getName());


    public static void main(String[] args) {
        clientTcp = new ClientTcp();
        clientTcp.start();
        logger.info("[Client] TCP Connection established");

        clientUdp = new ClientUdp(clientTcp.getPort());
        clientUdp.start();
        logger.info("[Client] UDP Connection established");

        clientMulticastUdp = new ClientMulticastUdp();
        clientMulticastUdp.start();
        logger.info("[Client] Client added to multicast group.");

        runChat();
    }

    private static void runChat() {
        logger.info(CHAT_RULES);

        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            if (scanner.hasNextLine()) {
                String[] input = scanner.nextLine().split(SPLITTER, 2);
                switch (input[0].isEmpty() ? ' ' : input[0].charAt(0)) {
                    case 'T','t':
                        if(input.length < 2){
                            System.out.println("[TCP] You need to specify the context of the message! Check rules of the chat.");
                            logger.info(CHAT_RULES);
                            break;
                        }
                        clientTcp.sendMessages(input[1]);
                        break;
                    case 'U', 'u':
                        clientUdp.sendMultimediaMessage();
                        break;
                    case 'M', 'm':
                        clientMulticastUdp.sendMulticastMultimediaMessage();
                        break;
                    case 'Q', 'q':
                        quitChat();
                    default:
                        System.out.println("Wrong command! Check rules of the chat.");
                        logger.info(CHAT_RULES);
                }
            }
        }
    }

    private static void quitChat() {
        try {
            clientMulticastUdp.close();
            clientUdp.close();
            clientTcp.close();
            logger.warning("You left the chat :(");
            isRunning = false;
            clientMulticastUdp.join();
            clientUdp.join();
            clientTcp.join();
            exit(0);
        } catch (InterruptedException e){
            logger.severe("[Client] Client quit chat error: " + e.getMessage());
        }
    }
}
