package ch.khinkali.cryptowatch.kafka.backup.consumer.control;

import org.apache.kafka.common.serialization.Serializer;

import javax.json.JsonObject;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

public class GenericSerializer implements Serializer<JsonObject> {

    private static final Logger logger = Logger.getLogger(ch.khinkali.cryptowatch.events.boundary.EventSerializer.class.getName());

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(final String topic, final JsonObject event) {
        return event
                .toString()
                .getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void close() {
        // nothing to do
    }

}
