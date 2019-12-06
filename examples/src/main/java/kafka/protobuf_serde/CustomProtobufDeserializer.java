/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import kafka.Utilities;
import kafka.examples.SerializerType;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomProtobufDeserializer<T extends MessageLite> implements Deserializer<T> {

    private final Parser<T> parser;
    private final long[] serializedTimes;
    private int currCount;
    private final String filename;

    /**
     * Returns a new instance of {@link CustomProtobufDeserializer}
     *
     * @param parser         The Protobuf {@link Parser}
     * @param serializerType The serializer type being used
     * @param iterations     The number of iterations this deserializer will run for
     */
    public CustomProtobufDeserializer(Parser<T> parser, SerializerType serializerType, int iterations) {
        this.parser = parser;
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = serializerType.toString() + "_" + iterations + "_des.txt";
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            long startTime = System.nanoTime();
            T ret = parser.parseFrom(data);
            long endTime = System.nanoTime();

            this.serializedTimes[this.currCount] = endTime - startTime;
            this.currCount++;

            if (this.currCount == BUFFER_SIZE) {
                Utilities.appendToFile(this.filename, this.serializedTimes);
                this.currCount = 0;
            }

            return ret;
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            throw new SerializationException("Could not parse Protobuf Object");
        }
    }
}
