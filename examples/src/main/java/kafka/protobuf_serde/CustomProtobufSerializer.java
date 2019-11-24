/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.MessageLite;
import org.apache.kafka.common.serialization.Serializer;

public class CustomProtobufSerializer<T extends MessageLite> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        return data.toByteArray();
    }
}
