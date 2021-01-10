package org.acme.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import org.eclipse.microprofile.reactive.messaging.Channel;
 import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.time.Duration;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items to a server sent event.
 */
@Path("/prices")
@ApplicationScoped
public class PriceResource {

    @Inject
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) // denotes that server side events (SSE) will be produced
    @SseElementType("text/plain") // denotes that the contained data, within this SSE, is just regular text/plain data
    public Publisher<Double> stream() {
        return prices;
    }


//
//    @POST
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Path("/stream1234")
//    //@Outgoing("generated-price")
//    public Multi<Fruit> generate() {
//        System.out.println("out ===>>>>>>");
//        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
//                .onOverflow().drop()
//                .map(tick -> new Fruit("a",20));
//        // return Multi.createFrom().item(new Fruit("test",random.nextInt(100))).map();
//    }
}
