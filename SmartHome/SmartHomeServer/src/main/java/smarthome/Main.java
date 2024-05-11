package smarthome;

import java.util.logging.Logger;
import smarthome.server.SmartHomeServer;

import java.util.Arrays;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.severe("IllegalArgument: Expected at least one argument - ServerID");
        }

        try {
            int serverID = Integer.parseInt(args[0]);
            if (serverID < 0 || serverID > 99) {
                logger.severe("IllegalArgument: ServerID must be in range (0, 255)");
            }
            String[] iceArgs = Arrays.copyOfRange(args, 1, args.length);
            startServer(serverID, iceArgs);

        } catch (NumberFormatException e) {
            logger.severe("IllegalArgument: ServerID must be a number.");
        }
    }

    private static void startServer(int serverID, String[] iceArgs) {
        logger.info("Server starting... ");
        SmartHomeServer server = new SmartHomeServer(serverID, iceArgs);
        server.runServerLoop();
    }
}
