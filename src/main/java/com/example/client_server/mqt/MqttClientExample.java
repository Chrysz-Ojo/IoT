package com.example.client_server.mqt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.IMqttClient;

public class MqttClientExample {

    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String clientId = "JavaClient";

        try {
            IMqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            client.connect(options);

            client.subscribe("test/topic", (topic, msg) -> {
                System.out.println("Received message: " + new String(msg.getPayload()));
            });

            MqttMessage message = new MqttMessage("Hello MQTT".getBytes());
            client.publish("test/topic", message);

            // Disconnect after some time
            Thread.sleep(10000);
            client.disconnect();
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
