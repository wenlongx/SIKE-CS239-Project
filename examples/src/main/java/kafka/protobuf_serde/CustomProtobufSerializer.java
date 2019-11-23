/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import kafka.protobuf_serde.generated.PbClasses;
import org.apache.kafka.common.serialization.Serializer;

public class CustomProtobufSerializer implements Serializer<PbClasses.SearchRequest> {

    @Override
    public byte[] serialize(String topic, PbClasses.SearchRequest pbc) {
//        if (o instanceof PbClasses.SearchRequest){
//
//        }
        return pbc.toByteArray();
    }
}
