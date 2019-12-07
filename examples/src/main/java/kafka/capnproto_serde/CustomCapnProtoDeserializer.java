/*
 * Created by Sahil Gandhi
 */

package kafka.capnproto_serde;

import com.google.protobuf.Parser;
import kafka.Utilities;
import kafka.capnproto_serde.generated.CapnProtoClasses;
import kafka.examples.SerializerType;
import kafka.protobuf_serde.CustomProtobufDeserializer;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.capnproto.MessageReader;
import org.capnproto.Serialize;

import java.io.IOException;
import java.nio.ByteBuffer;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomCapnProtoDeserializer implements Deserializer<MessageReader> {
    private final long[] serializedTimes;
    private int currCount;
    private final String filename;

    /**
     * Returns a new instance of {@link CustomCapnProtoDeserializer}
     *
     * @param serializerType The serializer type being used
     * @param iterations     The number of iterations this deserializer will run for
     */

    public CustomCapnProtoDeserializer(SerializerType serializerType, int iterations) {
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = serializerType.toString() + "_" + iterations + "_des.txt";
    }

    @Override
    public MessageReader deserialize(String topic, byte[] data) {
        MessageReader ret;
        try {
            long startTime = System.nanoTime();
            ret = Serialize.read(ByteBuffer.wrap(data));
            long endTime = System.nanoTime();

            this.serializedTimes[this.currCount] = endTime - startTime;
            this.currCount++;

            if (this.currCount == BUFFER_SIZE) {
                Utilities.appendToFile(this.filename, this.serializedTimes);
                this.currCount = 0;
            }

            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException("Could not parse Cap'nProto Object");
        }
    }
}
