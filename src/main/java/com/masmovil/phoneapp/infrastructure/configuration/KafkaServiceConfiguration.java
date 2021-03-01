package com.masmovil.phoneapp.infrastructure.configuration;

import com.masmovil.phoneapp.domain.event.service.DomainEventPublisher;
import com.masmovil.phoneapp.domain.model.CostumerOrder;
import com.masmovil.phoneapp.infrastructure.event.KafkaProducerService;
import io.quarkus.arc.DefaultBean;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import java.util.Properties;


@Dependent
public class KafkaServiceConfiguration {

  @ConfigProperty(name = "kafka.bootstrap.servers")
  public String bootstrapServers;

  @ConfigProperty(name = "kafka.messaging.outgoing.costumerorder.topic")
  public String costumerOrdersTopic;

  @ConfigProperty(name = "kafka.messaging.outgoing.costumerorder.key.serializer")
  public String costumerOrdersTopicKeySerializer;

  @ConfigProperty(name = "kafka.messaging.outgoing.costumerorder.value.serializer")
  public String costumerOrdersTopicValueSerializer;

  @Produces
  @DefaultBean
  KafkaProducer<String, String> kafkaProducer(Vertx vertx) {
    Properties configurationProperties = new Properties();
    configurationProperties.put("bootstrap.servers", bootstrapServers);
    configurationProperties.put("value.serializer", costumerOrdersTopicValueSerializer);
    configurationProperties.put("key.serializer", costumerOrdersTopicKeySerializer);
    configurationProperties.put("acks", "1");
    return KafkaProducer.create(vertx, configurationProperties);

  }

  @Produces
  @ApplicationScoped
  @DefaultBean
  DomainEventPublisher<CostumerOrder> costumerOrderPublisher(
      KafkaProducer<String, String> kafkaProducer) {

    return new KafkaProducerService(kafkaProducer, costumerOrdersTopic);
  }


}
