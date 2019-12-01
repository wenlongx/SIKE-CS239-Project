/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import kafka.Utilities;
import org.apache.kafka.common.serialization.Serializer;

import java.io.*;

public class CustomProtobufSerializer<T extends MessageLite> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        long startTime = System.nanoTime();
        byte[] arr = data.toByteArray();
        long endTime = System.nanoTime();
        Utilities.appendToFile("proto_ser.txt", "" + (endTime - startTime) + "\n");
        return arr;
    }
}
