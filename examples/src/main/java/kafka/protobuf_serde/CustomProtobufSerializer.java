/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import kafka.protobuf_serde.generated.PbClasses;
import org.apache.kafka.common.serialization.Serializer;

public class CustomProtobufSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String topic, Object o) {
        return ((PbClasses.SearchRequest) o).toByteArray();
    }
}
