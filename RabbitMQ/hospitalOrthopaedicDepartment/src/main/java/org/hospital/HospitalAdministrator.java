package org.hospital;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import static org.hospital.RabbitMQHelper.*;

public class HospitalAdministrator {

    private static final Logger logger = Logger.getLogger(HospitalAdministrator.class.getName());

    public static void main(String[] argv) throws Exception {
        logger.info("Starting the hospital administrator panel...");

        Connection connection = createConnection();
        Channel channel = createChannel(connection);

        declareExchanges(channel);

        String administratorQueue = declareQueue(channel);

        bindQueues(channel, administratorQueue);

        Consumer consumer = createConsumer(channel);
        startConsuming(channel, administratorQueue, consumer);

        panelLoop(channel, connection);
    }

    private static void declareExchanges(Channel channel) throws IOException {
        channel.exchangeDeclare(EXCHANGE_FOR_HOSPITAL_TOPIC, BuiltinExchangeType.TOPIC);
        channel.exchangeDeclare(EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, BuiltinExchangeType.FANOUT);
    }

    private static String declareQueue(Channel channel) throws IOException {
        return channel.queueDeclare().getQueue();
    }

    private static void bindQueues(Channel channel, String queueName) throws IOException {
        channel.queueBind(queueName, EXCHANGE_FOR_HOSPITAL_TOPIC, "hospital.#");
        channel.queueBind(queueName, EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, "");
    }

    private static Consumer createConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
            }
        };
    }

    private static void startConsuming(Channel channel, String queueName, Consumer consumer) throws IOException {
        logger.info("Waiting for messages...");
        channel.basicConsume(queueName, true, consumer);
    }

    private static void panelLoop(Channel channel, Connection connection) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("OPTIONS [Q as quit - exit the hospital administrator panel, S as send - to send a message to all hospital employees]\n ");
            String operation = br.readLine();
            if (Objects.equals("q", operation.toLowerCase()) || Objects.equals("quit", operation.toLowerCase())) {
                logger.info("Closing the hospital administrator panel.");
                channel.close();
                connection.close();
                break;
            } else {
                System.out.print("Enter message to all hospital employees: ");
                String message = br.readLine();

                String messageToSend = String.format("Hospital Admin Pannel: %s", message);

                channel.basicPublish(EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, "", null, messageToSend.getBytes("UTF-8"));
                logger.info("Message sent to all users: " + message);
            }
        }
    }
}
