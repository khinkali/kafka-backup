package ch.khinkali.cryptowatch.kafka.backup.consumer.control;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.logging.Logger;

public class GenericDeserializer implements Deserializer<JsonObject> {
    private static final Logger logger = Logger.getLogger(ch.khinkali.cryptowatch.events.boundary.EventDeserializer.class.getName());

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public JsonObject deserialize(final String topic, final byte[] data) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
            return Json.createReader(input).readObject();
        } catch (Exception e) {
            logger.severe("Could not deserialize event: " + e.getMessage());
            e.printStackTrace();
            throw new SerializationException("Could not deserialize event", e);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }

}