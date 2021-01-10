package org.acme.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.smallrye.reactive.messaging.annotations.Blocking;

import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * A bean consuming data from the "prices" Kafka topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class PriceListener {

    private static final double CONVERSION_RATE = 0.88;

    // Consume from the `prices` channel and produce to the `my-data-stream` channel
    @Incoming("prices123")
    @Outgoing("my-data-stream")
    // Send to all subscribers
    @Broadcast
    // Acknowledge the messages before calling this method.
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    @Blocking
    @Transactional
    public double process(Entity entity) {
        int priceInUsd = entity.price;
        System.out.println("In ===>>> "+priceInUsd);
        Price price = new Price();
        price.value = priceInUsd;
        price.persist();
        System.out.println(price.isPersistent()+" "+Price.count());
        return priceInUsd * CONVERSION_RATE;
    }


    /*
     @Incoming("prices")
  //  @Blocking
    //@Transactional
    public void store(int priceInUsd) {
        System.out.println("currenty got price "+priceInUsd);
        Price price = new Price();
        price.value = priceInUsd;
      //  price.persist();
       // System.out.println(price.isPersistent());
    }
     */

}
