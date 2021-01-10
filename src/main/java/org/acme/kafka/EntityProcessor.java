package org.acme.kafka;

import javax.enterprise.context.ApplicationScoped;

/**
* A bean consuming data from the "fruit-in" Kafka topic and applying some price conversion.
* The result is pushed to the "fruit-out" stream.
*/
@ApplicationScoped
public class EntityProcessor {

    private static final double CONVERSION_RATE = 0.88;

//    @Incoming("fruit-in")
//    @Outgoing("fruit-out")
//    @Broadcast
    public Entity process(Entity entity) {
      //  fruit.price = fruit.price * CONVERSION_RATE;
        return entity;
    }

}