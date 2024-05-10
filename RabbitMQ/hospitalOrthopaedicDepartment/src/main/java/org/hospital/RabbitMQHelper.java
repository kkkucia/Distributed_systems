package org.hospital;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class RabbitMQHelper {

    private static final Logger logger = Logger.getLogger(RabbitMQHelper.class.getName());

    public static final String EXCHANGE_FOR_HOSPITAL_TOPIC = "exchange_for_hospital_topic";
    public static final String EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT = "exchange_for_all_hospital_employees_fanout";
    private static final String[] VALID_SPECIALISATIONS = {"knee", "hip", "elbow"};

    public static Connection createConnection() throws IOException, TimeoutException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            logger.severe("Error while creating RabbitMQ connection: " + e.getMessage());
            throw e;
        }
    }

    public static Channel createChannel(Connection connection) throws IOException {
        return connection.createChannel();
    }

    public static String makeHospitalMessageKey(String key) {
        return String.format("hospital.%s", key);
    }

    public static boolean isValidSpecialisation(String specialisation) {
        for (String valid : VALID_SPECIALISATIONS) {
            if (valid.equalsIgnoreCase(specialisation)) {
                return true;
            }
        }
        return false;
    }
}
