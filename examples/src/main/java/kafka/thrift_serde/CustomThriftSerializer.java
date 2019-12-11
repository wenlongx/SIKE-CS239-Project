/*
 * Created by Sahil Gandhi
 */

package kafka.thrift_serde;

import kafka.Utilities;
import kafka.examples.SerializerType;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomThriftSerializer<T extends TBase> implements Serializer<T> {
    private final long[] serializedTimes;
    private int currCount;
    private final String filename;
    private final TSerializer serializer;

    /**
     * Returns a new instance of {@link CustomThriftSerializer}
     *
     * @param serializerType The serializer type being used
     * @param iterations     The number of iterations this serializer will run for
     */
    public CustomThriftSerializer(SerializerType serializerType, int iterations) {
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = serializerType.toString() + "_" + iterations + "_ser.txt";
        serializer = new TSerializer(new TCompactProtocol.Factory());
    }

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            long startTime = System.nanoTime();
            byte [] arr = this.serializer.serialize(data);
//            System.out.println(arr.length);
            long endTime = System.nanoTime();
            this.serializedTimes[this.currCount] = endTime - startTime;
            this.currCount++;

            if (this.currCount == BUFFER_SIZE) {
                Utilities.appendToFile(this.filename, this.serializedTimes);
                this.currCount = 0;
            }
            return arr;
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }
}
