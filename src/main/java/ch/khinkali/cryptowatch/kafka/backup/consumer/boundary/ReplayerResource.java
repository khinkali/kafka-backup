package ch.khinkali.cryptowatch.kafka.backup.consumer.boundary;

import ch.khinkali.cryptowatch.kafka.backup.EventProducer;
import ch.khinkali.cryptowatch.kafka.backup.consumer.entity.Event;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.stream.Stream;

@Stateless
@Path("replay")
public class ReplayerResource {

    @PersistenceContext
    EntityManager em;

    @Inject
    EventProducer producer;

    @GET
    public void replayAllEventsFromDB() {
        Stream<Event> events = em.createNamedQuery("findAll")
                .getResultList()
                .stream()
                .map(Event.class::cast);
        events
                .forEach(this::sendEvent);
    }

    private void sendEvent(Event event) {
        producer.publish(event.getTopic(), event.getJson());
    }

}
