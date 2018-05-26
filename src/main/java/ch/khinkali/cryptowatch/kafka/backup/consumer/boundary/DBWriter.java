package ch.khinkali.cryptowatch.kafka.backup.consumer.boundary;

import ch.khinkali.cryptowatch.events.entity.BaseEvent;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

@Singleton
@Startup
public class DBWriter {

    @Inject
    Logger logger;

    public void consumeEvents(@Observes BaseEvent event) {
        logger.info(event.getJson().toString());
    }
}
