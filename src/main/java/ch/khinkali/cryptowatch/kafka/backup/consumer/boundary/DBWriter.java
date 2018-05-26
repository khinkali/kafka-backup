package ch.khinkali.cryptowatch.kafka.backup.consumer.boundary;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.logging.Logger;

@Singleton
@Startup
public class DBWriter {

    @Inject
    Logger logger;

    public void consumeEvents(@Observes JsonObject event) {
        logger.info(event.toString());
    }
}
