package org.hospital;

import com.rabbitmq.client.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import static org.hospital.RabbitMQHelper.*;

public class Doctor {
    private static final Logger logger = Logger.getLogger(Doctor.class.getName());


    public static void main(String[] argv) throws Exception {
        logger.info("Starting the doctor panel...");

        Connection connection = createConnection();
        Channel channel = createChannel(connection);

        declareExchanges(channel);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String identifier = readDoctorIdentifier(br);

        declareQueueAndBindings(channel, identifier);

        Consumer consumer = createConsumer(channel);
        startConsuming(channel, identifier, consumer);

        panelLoop(channel, connection, br, identifier);
    }

    private static void declareExchanges(Channel channel) throws IOException {
        channel.exchangeDeclare(EXCHANGE_FOR_HOSPITAL_TOPIC, BuiltinExchangeType.TOPIC);
        channel.exchangeDeclare(EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, BuiltinExchangeType.FANOUT);
    }

    private static String readDoctorIdentifier(BufferedReader br) throws IOException {
        System.out.print("Enter doctor identifier [L1/L2/L3/...]: ");
        return br.readLine();
    }

    private static void declareQueueAndBindings(Channel channel, String identifier) throws IOException {
        channel.queueDeclare(identifier, false, false, false, null);
        channel.queueBind(identifier, EXCHANGE_FOR_HOSPITAL_TOPIC, String.format("hospital.%s.#", identifier));
        channel.queueBind(identifier, EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, "");
        channel.basicQos(1);
    }

    private static Consumer createConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("\nReceived message: " + message);
            }
        };
    }

    private static void startConsuming(Channel channel, String identifier, Consumer consumer) throws IOException {
        channel.basicConsume(identifier, true, consumer);
    }

    private static void panelLoop(Channel channel, Connection connection, BufferedReader br, String identifier) throws IOException, TimeoutException {
        while (true) {
            System.out.print("OPTIONS [Q as quit - exit doctor panel, P as patient - to start treating the patient]\n ");
            String operation = br.readLine();
            if (Objects.equals("q", operation.toLowerCase()) || Objects.equals("quit", operation.toLowerCase())) {
                logger.info("Closing the doctor panel.");
                channel.close();
                connection.close();
                break;
            } else {
                treatPatient(channel, br, identifier);
            }
        }
    }

    private static void treatPatient(Channel channel, BufferedReader br, String identifier) throws IOException {
        System.out.print("Enter patient first name: ");
        String patientFirstName = br.readLine();

        System.out.print("Enter patient last name: ");
        String patientLastName = br.readLine();

        System.out.print("Enter patient's injury [hip/knee/elbow]: ");
        String injury = br.readLine();
        if (!isValidSpecialisation(injury)) {
            System.out.println("Invalid injury. Please enter hip, knee, or elbow: ");
            injury = br.readLine();
        }

        String message = String.format("%s;%s;%s %s", identifier, injury, patientFirstName, patientLastName);

        channel.basicPublish(EXCHANGE_FOR_HOSPITAL_TOPIC,  makeHospitalMessageKey(injury), null, message.getBytes("UTF-8"));
        logger.info("Message sent: " + message);
    }
}