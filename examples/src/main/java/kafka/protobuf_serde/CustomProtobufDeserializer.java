/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.InvalidProtocolBufferException;
import kafka.protobuf_serde.generated.PbClasses;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomProtobufDeserializer implements Deserializer<Object> {

    public Object deserialize(String topic, byte[] data){
        if (data == null){
            return null;
        }

        try {
            return PbClasses.SearchRequest.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            throw new SerializationException("Could not parse Protobuf Object");
        }
    }
}
