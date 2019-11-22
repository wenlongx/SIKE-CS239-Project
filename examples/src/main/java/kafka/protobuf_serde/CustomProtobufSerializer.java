/*
 * Created by Sahil Gandhi
 */

package kafka.protobuf_serde;

import kafka.protobuf_serde.generated.Init;

public class CustomProtobufSerializer {

    public static void main(String[] args) {
        System.out.println("hello there");
        Init.SearchRequest sq = Init.SearchRequest.newBuilder().setQuery("hihi").setPageNumber(12312).build();

        System.out.println(sq.getPageNumber());
    }
}
