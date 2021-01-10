package org.acme.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class EntityDeserializer implements Deserializer<Entity> {


    public EntityDeserializer() {
    }

    @Override
    public Entity deserialize(String topic, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Entity entity = null;
        try {
            entity = mapper.readValue(bytes, Entity.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return entity;
    }



}