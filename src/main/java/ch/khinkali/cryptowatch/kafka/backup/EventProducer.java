package ch.khinkali.cryptowatch.kafka.backup;

import ch.khinkali.cryptowatch.events.boundary.BaseEventProducer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.Properties;

@ApplicationScoped
public class EventProducer extends BaseEventProducer<String, JsonObject> {

    @Inject
    Properties kafkaProperties;

    @PostConstruct
    private void initProducer() {
        init(kafkaProperties);
    }

    @PreDestroy
    public void closeProducer() {
        close();
    }

}
