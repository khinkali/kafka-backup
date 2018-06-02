package ch.khinkali.cryptowatch.kafka.backup.consumer.control;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
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
            JsonObject event = Json.createReader(input).readObject();
            return jsonObjectToBuilder(event)
                    .add("topic", topic)
                    .build();
        } catch (Exception e) {
            logger.severe("Could not deserialize event: " + e.getMessage());
            throw new SerializationException("Could not deserialize event", e);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }

    private JsonObjectBuilder jsonObjectToBuilder(JsonObject jo) {
        JsonObjectBuilder job = Json.createObjectBuilder();

        for (Map.Entry<String, JsonValue> entry : jo.entrySet()) {
            job.add(entry.getKey(), entry.getValue());
        }

        return job;
    }

}