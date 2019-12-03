/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import kafka.Utilities;
import kafka.examples.SerializerType;
import org.apache.kafka.common.serialization.Serializer;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomProtobufSerializer<T extends MessageLite> implements Serializer<T> {

    private final long[] serializedTimes;
    private int currCount;
    private final String filename;

    /**
     * Returns a new instance of {@link CustomProtobufSerializer}
     *
     * @param iterations The number of iterations this serializer will run for
     */
    public CustomProtobufSerializer(SerializerType serializerType, int iterations) {
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = "proto_ser_" + serializerType.toString() + "_" + iterations + ".txt";
    }

    @Override
    public byte[] serialize(String topic, T data) {
        long startTime = System.nanoTime();
        byte[] arr = data.toByteArray();
        long endTime = System.nanoTime();
        this.serializedTimes[this.currCount] = endTime - startTime;
        this.currCount++;

        if (this.currCount == BUFFER_SIZE) {
            Utilities.appendToFile(this.filename, this.serializedTimes);
            this.currCount = 0;
        }
        return arr;
    }
}
