package com.masmovil.phoneapp.infrastructure.event;

import com.masmovil.phoneapp.domain.event.DomainEvent;
import com.masmovil.phoneapp.domain.event.service.DomainEventPublisher;
import com.masmovil.phoneapp.domain.model.CostumerOrder;
import io.vertx.core.json.Json;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.kafka.client.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerService implements DomainEventPublisher<CostumerOrder> {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

  private KafkaProducer<String, String> kafkaProducer;

  private String topic;

  public KafkaProducerService(KafkaProducer<String, String> kafkaProducer, String costumerOrdersTopic) {
    this.kafkaProducer = kafkaProducer;
    this.topic = costumerOrdersTopic;
  }

  @Override
  public void publish(DomainEvent<CostumerOrder> domainEvent) {
    try {
      LOGGER.info("Sending [" + domainEvent.getClass().getName() + "] to Kafka on topic [" + topic + "]");
      KafkaProducerRecord<String, String> messageRecord = KafkaProducerRecord.create(
          topic, Json.encodePrettily(domainEvent));

      kafkaProducer.send(messageRecord, done -> {
        if (done.succeeded()) {
          RecordMetadata recordMetadata = done.result();
          LOGGER.info("Message " + messageRecord + " written on topic=" + recordMetadata.getTopic() +
              ", partition=" + recordMetadata.getPartition() +
              ", offset=" + recordMetadata.getOffset());
        } else {
          LOGGER.error("Error sending to kafka: ", done.cause());
          throw new RuntimeException(done.cause());
        }
      });

    } catch (Exception e) {
      LOGGER.error("Error when sending to Kafka", e);
      throw e;
    }
  }
}
