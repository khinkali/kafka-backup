package ch.khinkali.cryptowatch.kafka.backup.consumer.control;

import ch.khinkali.cryptowatch.events.boundary.EventConsumer;
import ch.khinkali.cryptowatch.events.entity.BaseEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

@Startup
@Singleton
public class CryptowatchEventConsumer {

    private EventConsumer eventConsumer;

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<BaseEvent> events;

    @PostConstruct
    private void init() {
        kafkaProperties.put("group.id", "backup-" + UUID.randomUUID());

        eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            events.fire(ev);
        }, Pattern.compile(".*"));

        mes.execute(eventConsumer);
    }

    @PreDestroy
    public void close() {
        eventConsumer.stop();
    }

}

