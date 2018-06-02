package ch.khinkali.cryptowatch.kafka.backup.consumer.entity;

import ch.khinkali.cryptowatch.events.entity.BaseEvent;
import lombok.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@NamedQueries(
        @NamedQuery(name = "findAll", query = "SELECT e FROM Event e")
)
public class Event {
    @Id
    private String id;
    @Column(columnDefinition = "text")
    private String event;
    private String topic;

    public Event(JsonObject event) {
        JsonObject data = event.getJsonObject("data");
        this.id = data.getString(BaseEvent.JSON_KEYS.ID.getJsonKey());
        this.topic = event.getString("topic");
        this.event = event.toString();
    }

    public JsonObject getJson() {
        ByteArrayInputStream asStream = new ByteArrayInputStream(event.getBytes(StandardCharsets.UTF_8));
        return Json.createReader(asStream).readObject();
    }

}
