package ch.khinkali.cryptowatch.kafka.backup.consumer.entity;

import lombok.*;

import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    private String event;

    public Event(JsonObject event) {
        this.id = UUID.randomUUID().toString();
        this.event = event.toString();
    }

}
