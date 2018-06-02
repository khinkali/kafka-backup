package ch.khinkali.cryptowatch.kafka.backup.consumer.entity;

import ch.khinkali.cryptowatch.events.entity.BaseEvent;
import lombok.*;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.logging.Logger;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    @Column(columnDefinition = "text")
    private String event;

    @Inject
    Logger log;

    public Event(JsonObject event) {
        log.info(event.toString());
        this.id = event.getString(BaseEvent.JSON_KEYS.ID.getJsonKey());
        this.event = event.toString();
    }

}
