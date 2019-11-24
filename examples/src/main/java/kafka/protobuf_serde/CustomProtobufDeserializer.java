/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomProtobufDeserializer<T extends MessageLite> implements Deserializer<T> {

    private final Parser<T> parser;

    /**
     * Returns a new instance of {@link CustomProtobufDeserializer}
     *
     * @param parser The Protobuf {@link Parser}
     */
    public CustomProtobufDeserializer(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return parser.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            throw new SerializationException("Could not parse Protobuf Object");
        }
    }
}
