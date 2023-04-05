package com.devnexus.riferrei.session;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class WriteDataToKafka {

	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String TOPIC_NAME = "test";

	public static void main(String[] args) {
		new WriteDataToKafka().run();
	}

	private void run() {

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		try (KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties)) {

			producer.send(new ProducerRecord<Integer, String>(TOPIC_NAME, "Devnexus 2023"),
					(recordMetadata, exception) -> {
						System.out.printf("➡️ Message sent successfully to topic [%s] ✅\n",
								recordMetadata.topic());
					});

		}

	}

}
