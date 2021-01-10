package org.acme.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
 import org.apache.kafka.common.serialization.Serializer;


public class EntitySerializer implements Serializer<Entity> {

   public EntitySerializer(){

   }

    @Override
    public byte[] serialize(String topic, Entity o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(o).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }


}