package org.hospital;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

import static org.hospital.RabbitMQHelper.*;

public class Technician {
    private static final Logger logger = Logger.getLogger(Technician.class.getName());

    public static void main(String[] argv) throws Exception {
        logger.info("Starting the technician panel...");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String identifier = readTechnicianIdentifier(br);

        String[] specialisations = readSpecialisations(br);

        Connection connection = createConnection();
        Channel channel = createChannel(connection);

        declareExchanges(channel);

        String technicianQueue = declareQueuesAndBindings(channel, specialisations);

        Consumer consumer = createConsumer(channel, identifier);
        startConsuming(channel, specialisations, technicianQueue, consumer);
    }

    private static String readTechnicianIdentifier(BufferedReader br) throws IOException {
        System.out.print("Enter technician identifier [T1/T2/T3/...]: ");
        return br.readLine();
    }

    private static String[] readSpecialisations(BufferedReader br) throws IOException {
        while (true) {
            System.out.print("Enter your two specialisations [hip/knee/elbow]\n first: ");
            String specialisation_1 = br.readLine();
            if (!isValidSpecialisation(specialisation_1)) {
                System.out.println("Invalid 1 specialisation. Please enter hip, knee, or elbow: ");
                continue;
            }
            System.out.print(String.format("Your first specialisation is %s. Choose another one.\n second: ", specialisation_1));
            String specialisation_2 = br.readLine();
            if (!isValidSpecialisation(specialisation_2)) {
                System.out.println("Invalid 2 specialisation. Please enter hip, knee, or elbow: ");
                continue;
            }
            if (Objects.equals(specialisation_1, specialisation_2)) {
                System.out.println("Your second specialisation must be different than the first one. Choose another one.");
                continue;
            }
            return new String[]{specialisation_1, specialisation_2};
        }
    }

    private static void declareExchanges(Channel channel) throws IOException {
        channel.basicQos(3);
        channel.exchangeDeclare(EXCHANGE_FOR_HOSPITAL_TOPIC, BuiltinExchangeType.TOPIC);
        channel.exchangeDeclare(EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, BuiltinExchangeType.FANOUT);
}

    private static String declareQueuesAndBindings(Channel channel, String[] specialisations) throws IOException {
        String technicianQueue = channel.queueDeclare().getQueue();

        channel.queueDeclare(specialisations[0], false, false, false, null);
        channel.queueDeclare(specialisations[1], false, false, false, null);

        channel.queueBind(technicianQueue, EXCHANGE_FOR_ALL_HOSPITAL_EMPLOYEES_FANOUT, "");
        channel.queueBind(specialisations[0], EXCHANGE_FOR_HOSPITAL_TOPIC, String.format("hospital.%s.#", specialisations[0]));
        channel.queueBind(specialisations[1], EXCHANGE_FOR_HOSPITAL_TOPIC, String.format("hospital.%s.#", specialisations[1]));
        return technicianQueue;
    }

    private static Consumer createConsumer(Channel channel, String identifier) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received message: " + message);

                if (!Objects.equals(message.split(" ")[0], "Hospital")){
                    String[] parts = message.split(";");
                    String doctorIdentifier = parts[0];
                    String injury = parts[1];
                    String patientName = parts[2];
                    processPatientData();
                    String responseMessage = String.format("%s: %s - %s - done", identifier, patientName, injury);
                    logger.info("Message sent: " + responseMessage);
                    channel.basicPublish(EXCHANGE_FOR_HOSPITAL_TOPIC,  makeHospitalMessageKey(doctorIdentifier), null, responseMessage.getBytes("UTF-8"));

                }
            }
        };
    }

    private static void startConsuming(Channel channel, String[] specialisations, String technicianQueue, Consumer consumer) throws IOException {
        logger.info("Waiting for messages from Doctors...");
        channel.basicConsume(specialisations[0], true, consumer);
        channel.basicConsume(specialisations[1], true, consumer);
        channel.basicConsume(technicianQueue, true, consumer);
    }

    private static void processPatientData() {
            System.out.println("Patient data processing...");
        try {
            int timeToProcess = new Random().nextInt(6) * 1000;
            Thread.sleep(timeToProcess);
        } catch (InterruptedException e) {
            logger.severe("Error during process patient data: " + e.getMessage());
        }
    }
}