package org.acme.kafka;

import java.time.Duration;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to a Kafka topic (prices). The Kafka configuration is specified in the application configuration.
 */
@ApplicationScoped
public class PricePublisher {

    private Random random = new Random();

    @Outgoing("generated-price")
    public Multi<Entity> generate() {
        System.out.println("out ===>>>>>>");
        Multi<Entity> multi = Multi.createFrom().empty();
       // multi.subscribe().asIterable().stream().findFirst().
       // multi.onItem().
//        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
//                .onOverflow()
//                .drop()
//                .map(tick -> new Entity("a",random.nextInt(100)));
                return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
                .onOverflow()
                .drop()
                .map(tick -> new Entity("a",random.nextInt(100)));

/*
return Multi.createFrom().items(new Fruit("test",random.nextInt(100)))
                .onOverflow()
                .drop()
                .onCompletion().invoke(()->{
                    System.out.println("hi");
                });

                return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
                .onOverflow()
                .drop()
                .map(tick -> new Fruit("a",random.nextInt(100)));
 */


       // return new Fruit("test",random.nextInt(100));
    }

}
