package ch.khinkali.cryptowatch.kafka.backup.health.boundary;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("health")
public class HealthResource {

    @GET
    public String getVersion() {
        return System.getenv("VERSION");
    }

}
