namespace java kafka.thrift_serde.generated

struct PrimitiveMessage{
    1: i64 timestamp;
    2: string query;
    3: i32 pageNumber;
    4: i32 resultPerPage;
}

struct ComplexMessage{
    1: i64 timestamp;
    2: map<string, i32> storage;
    3: list<i32> arr;
}

struct NestedMessage{
    1: i64 timestamp;
    2: i32 id;
    3: PrimitiveMessage primitiveMsg;
}