package ch.khinkali.cryptowatch.kafka.backup;

import ch.khinkali.cryptowatch.events.boundary.BaseKafkaConfigurator;
import ch.khinkali.cryptowatch.kafka.backup.consumer.control.GenericDeserializer;
import ch.khinkali.cryptowatch.kafka.backup.consumer.control.GenericSerializer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.Properties;
import java.util.UUID;

@ApplicationScoped
public class KafkaConfigurator extends BaseKafkaConfigurator {

    @PostConstruct
    private void initProps() {
        initProperties();
    }

    @Produces
    @RequestScoped
    public Properties exposeKafkaProperties() {
        Properties kafkaProperties = getKafkaProperties();
        kafkaProperties.put("group.id", "backup-" + UUID.randomUUID());
        kafkaProperties.put("value.deserializer", GenericDeserializer.class.getCanonicalName());
        kafkaProperties.put("value.serializer", GenericSerializer.class.getCanonicalName());
        return kafkaProperties;
    }

}

