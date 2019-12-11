/*
 * Created by Sahil Gandhi
 */

package kafka.capnproto_serde;

import kafka.Utilities;
import kafka.examples.SerializerType;
import org.apache.kafka.common.serialization.Serializer;
import org.capnproto.ArrayOutputStream;
import org.capnproto.MessageBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static kafka.Utilities.BUFFER_SIZE;
import static org.capnproto.Serialize.write;

public class CustomCapnProtoSerializer implements Serializer<MessageBuilder> {

    private final long[] serializedTimes;
    private int currCount;
    private final String filename;

    /**
     * Returns a new instance of {@link CustomCapnProtoSerializer}
     *
     * @param serializerType The serializer type being used
     * @param iterations     The number of iterations this serializer will run for
     */
    public CustomCapnProtoSerializer(SerializerType serializerType, int iterations) {
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = serializerType.toString() + "_" + iterations + "_ser.txt";
    }

    @Override
    public byte[] serialize(String topic, MessageBuilder data) {

        // 2^17 Buffer should be enough, but change this if needed!
        ArrayOutputStream os = new ArrayOutputStream(ByteBuffer.allocate(131072));
        try {
            long startTime = System.nanoTime();
            write(os, data);
            byte[] arr = Arrays.copyOf(os.buf.array(), os.buf.position());
//            System.out.println(arr.length);
            long endTime = System.nanoTime();

            this.serializedTimes[this.currCount] = endTime - startTime;
            this.currCount++;

            if (this.currCount == BUFFER_SIZE) {
                Utilities.appendToFile(this.filename, this.serializedTimes);
                this.currCount = 0;
            }

            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
