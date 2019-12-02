/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.MessageLite;
import kafka.Utilities;
import org.apache.kafka.common.serialization.Serializer;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomProtobufSerializer<T extends MessageLite> implements Serializer<T> {

    private final long [] serializedTimes = new long[BUFFER_SIZE];
    private int currCount = 0;


    @Override
    public byte[] serialize(String topic, T data) {
        long startTime = System.nanoTime();
        byte[] arr = data.toByteArray();
        long endTime = System.nanoTime()
                ;
        this.serializedTimes[this.currCount] = endTime - startTime;
        this.currCount++;

        if (this.currCount == BUFFER_SIZE){
            Utilities.appendToFile("proto_ser.txt", this.serializedTimes);
            this.currCount = 0;
        }
        return arr;
    }
}
