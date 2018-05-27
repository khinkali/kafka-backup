package ch.khinkali.cryptowatch.kafka.backup.consumer.boundary;

import ch.khinkali.cryptowatch.kafka.backup.consumer.entity.Event;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Singleton
@Startup
public class DBWriter {

    @Inject
    Logger logger;

    @PersistenceContext
    EntityManager entityManager;

    public void consumeEvents(@Observes JsonObject event) {
        logger.info(event.toString());
        Event saved = entityManager
                .merge(new Event(event));
        logger.info(saved.toString());
    }
}
