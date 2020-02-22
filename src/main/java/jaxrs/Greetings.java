package jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class Greetings {

    @GET
    @Path("/greetings")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetings() {
        return "hello world";
    }
}
