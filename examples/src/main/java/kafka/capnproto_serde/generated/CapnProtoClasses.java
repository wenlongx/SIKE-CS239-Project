// Generated by Cap'n Proto compiler, DO NOT EDIT
// source: capn_classes.capnp

package kafka.capnproto_serde.generated;

public final class CapnProtoClasses {
  public static class PrimitiveMessage {
    public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)2,(short)1);
    public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
      public Factory() {
      }
      public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
        return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
      }
      public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
        return new Builder(segment, data, pointers, dataSize, pointerCount);
      }
      public final org.capnproto.StructSize structSize() {
        return PrimitiveMessage.STRUCT_SIZE;
      }
      public final Reader asReader(Builder builder) {
        return builder.asReader();
      }
    }
    public static final Factory factory = new Factory();
    public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
      new org.capnproto.StructList.Factory<Builder, Reader>(factory);
    public static final class Builder extends org.capnproto.StructBuilder {
      Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
        super(segment, data, pointers, dataSize, pointerCount);
      }
      public final Reader asReader() {
        return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
      }
      public final long getTimestamp() {
        return _getLongField(0);
      }
      public final void setTimestamp(long value) {
        _setLongField(0, value);
      }

      public final boolean hasQuery() {
        return !_pointerFieldIsNull(0);
      }
      public final org.capnproto.Text.Builder getQuery() {
        return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
      }
      public final void setQuery(org.capnproto.Text.Reader value) {
        _setPointerField(org.capnproto.Text.factory, 0, value);
      }
      public final void setQuery(String value) {
        _setPointerField(org.capnproto.Text.factory, 0, new org.capnproto.Text.Reader(value));
      }
      public final org.capnproto.Text.Builder initQuery(int size) {
        return _initPointerField(org.capnproto.Text.factory, 0, size);
      }
      public final int getPageNumber() {
        return _getIntField(2);
      }
      public final void setPageNumber(int value) {
        _setIntField(2, value);
      }

      public final int getResultPerPage() {
        return _getIntField(3);
      }
      public final void setResultPerPage(int value) {
        _setIntField(3, value);
      }

    }

    public static final class Reader extends org.capnproto.StructReader {
      Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
        super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
      }

      public final long getTimestamp() {
        return _getLongField(0);
      }

      public boolean hasQuery() {
        return !_pointerFieldIsNull(0);
      }
      public org.capnproto.Text.Reader getQuery() {
        return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
      }

      public final int getPageNumber() {
        return _getIntField(2);
      }

      public final int getResultPerPage() {
        return _getIntField(3);
      }

    }

  }


  public static class ComplexMessage {
    public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)1,(short)2);
    public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
      public Factory() {
      }
      public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
        return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
      }
      public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
        return new Builder(segment, data, pointers, dataSize, pointerCount);
      }
      public final org.capnproto.StructSize structSize() {
        return ComplexMessage.STRUCT_SIZE;
      }
      public final Reader asReader(Builder builder) {
        return builder.asReader();
      }
    }
    public static final Factory factory = new Factory();
    public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
      new org.capnproto.StructList.Factory<Builder, Reader>(factory);
    public static final class Builder extends org.capnproto.StructBuilder {
      Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
        super(segment, data, pointers, dataSize, pointerCount);
      }
      public final Reader asReader() {
        return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
      }
      public final long getTimestamp() {
        return _getLongField(0);
      }
      public final void setTimestamp(long value) {
        _setLongField(0, value);
      }

      public final boolean hasStorage() {
        return !_pointerFieldIsNull(0);
      }
      public final org.capnproto.StructList.Builder<kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.Builder> getStorage() {
        return _getPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.listFactory, 0, null, 0);
      }
      public final void setStorage(org.capnproto.StructList.Reader<kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.Reader> value) {
        _setPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.listFactory, 0, value);
      }
      public final org.capnproto.StructList.Builder<kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.Builder> initStorage(int size) {
        return _initPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.listFactory, 0, size);
      }
      public final boolean hasArr() {
        return !_pointerFieldIsNull(1);
      }
      public final org.capnproto.PrimitiveList.Int.Builder getArr() {
        return _getPointerField(org.capnproto.PrimitiveList.Int.factory, 1, null, 0);
      }
      public final void setArr(org.capnproto.PrimitiveList.Int.Reader value) {
        _setPointerField(org.capnproto.PrimitiveList.Int.factory, 1, value);
      }
      public final org.capnproto.PrimitiveList.Int.Builder initArr(int size) {
        return _initPointerField(org.capnproto.PrimitiveList.Int.factory, 1, size);
      }
    }

    public static final class Reader extends org.capnproto.StructReader {
      Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
        super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
      }

      public final long getTimestamp() {
        return _getLongField(0);
      }

      public final boolean hasStorage() {
        return !_pointerFieldIsNull(0);
      }
      public final org.capnproto.StructList.Reader<kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.Reader> getStorage() {
        return _getPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.ComplexMessage.Entry.listFactory, 0, null, 0);
      }

      public final boolean hasArr() {
        return !_pointerFieldIsNull(1);
      }
      public final org.capnproto.PrimitiveList.Int.Reader getArr() {
        return _getPointerField(org.capnproto.PrimitiveList.Int.factory, 1, null, 0);
      }

    }

    public static class Entry {
      public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)1,(short)1);
      public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
        public Factory() {
        }
        public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
          return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
        }
        public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
          return new Builder(segment, data, pointers, dataSize, pointerCount);
        }
        public final org.capnproto.StructSize structSize() {
          return ComplexMessage.Entry.STRUCT_SIZE;
        }
        public final Reader asReader(Builder builder) {
          return builder.asReader();
        }
      }
      public static final Factory factory = new Factory();
      public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
        new org.capnproto.StructList.Factory<Builder, Reader>(factory);
      public static final class Builder extends org.capnproto.StructBuilder {
        Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
          super(segment, data, pointers, dataSize, pointerCount);
        }
        public final Reader asReader() {
          return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
        }
        public final boolean hasKey() {
          return !_pointerFieldIsNull(0);
        }
        public final org.capnproto.Text.Builder getKey() {
          return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
        }
        public final void setKey(org.capnproto.Text.Reader value) {
          _setPointerField(org.capnproto.Text.factory, 0, value);
        }
        public final void setKey(String value) {
          _setPointerField(org.capnproto.Text.factory, 0, new org.capnproto.Text.Reader(value));
        }
        public final org.capnproto.Text.Builder initKey(int size) {
          return _initPointerField(org.capnproto.Text.factory, 0, size);
        }
        public final int getValue() {
          return _getIntField(0);
        }
        public final void setValue(int value) {
          _setIntField(0, value);
        }

      }

      public static final class Reader extends org.capnproto.StructReader {
        Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
          super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
        }

        public boolean hasKey() {
          return !_pointerFieldIsNull(0);
        }
        public org.capnproto.Text.Reader getKey() {
          return _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0);
        }

        public final int getValue() {
          return _getIntField(0);
        }

      }

    }


  }


  public static class NestedMessage {
    public static final org.capnproto.StructSize STRUCT_SIZE = new org.capnproto.StructSize((short)2,(short)1);
    public static final class Factory extends org.capnproto.StructFactory<Builder, Reader> {
      public Factory() {
      }
      public final Reader constructReader(org.capnproto.SegmentReader segment, int data,int pointers, int dataSize, short pointerCount, int nestingLimit) {
        return new Reader(segment,data,pointers,dataSize,pointerCount,nestingLimit);
      }
      public final Builder constructBuilder(org.capnproto.SegmentBuilder segment, int data,int pointers, int dataSize, short pointerCount) {
        return new Builder(segment, data, pointers, dataSize, pointerCount);
      }
      public final org.capnproto.StructSize structSize() {
        return NestedMessage.STRUCT_SIZE;
      }
      public final Reader asReader(Builder builder) {
        return builder.asReader();
      }
    }
    public static final Factory factory = new Factory();
    public static final org.capnproto.StructList.Factory<Builder,Reader> listFactory =
      new org.capnproto.StructList.Factory<Builder, Reader>(factory);
    public static final class Builder extends org.capnproto.StructBuilder {
      Builder(org.capnproto.SegmentBuilder segment, int data, int pointers,int dataSize, short pointerCount){
        super(segment, data, pointers, dataSize, pointerCount);
      }
      public final Reader asReader() {
        return new Reader(segment, data, pointers, dataSize, pointerCount, 0x7fffffff);
      }
      public final long getTimestamp() {
        return _getLongField(0);
      }
      public final void setTimestamp(long value) {
        _setLongField(0, value);
      }

      public final int getId() {
        return _getIntField(2);
      }
      public final void setId(int value) {
        _setIntField(2, value);
      }

      public final kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.Builder getPrimitiveMessage() {
        return _getPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.factory, 0, null, 0);
      }
      public final void setPrimitiveMessage(kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.Reader value) {
        _setPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.factory,0, value);
      }
      public final kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.Builder initPrimitiveMessage() {
        return _initPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.factory,0, 0);
      }
    }

    public static final class Reader extends org.capnproto.StructReader {
      Reader(org.capnproto.SegmentReader segment, int data, int pointers,int dataSize, short pointerCount, int nestingLimit){
        super(segment, data, pointers, dataSize, pointerCount, nestingLimit);
      }

      public final long getTimestamp() {
        return _getLongField(0);
      }

      public final int getId() {
        return _getIntField(2);
      }

      public boolean hasPrimitiveMessage() {
        return !_pointerFieldIsNull(0);
      }
      public kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.Reader getPrimitiveMessage() {
        return _getPointerField(kafka.capnproto_serde.generated.CapnProtoClasses.PrimitiveMessage.factory,0,null, 0);
      }

    }

  }



public static final class Schemas {
public static final org.capnproto.SegmentReader b_f34b35f009943395 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u0095\u0033\u0094\u0009\u00f0\u0035\u004b\u00f3" +
   "\u0013\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u00fe\u007f\u00cc\u0062\u00fa\u0023\u00b8\u00ce" +
   "\u0001\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\"\u0001\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0021\u0000\u0000\u0000\u00e7\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u0061\u0070\u006e\u005f\u0063\u006c\u0061" +
   "\u0073\u0073\u0065\u0073\u002e\u0063\u0061\u0070" +
   "\u006e\u0070\u003a\u0050\u0072\u0069\u006d\u0069" +
   "\u0074\u0069\u0076\u0065\u004d\u0065\u0073\u0073" +
   "\u0061\u0067\u0065\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0010\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0061\u0000\u0000\u0000\u0052\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0060\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u006c\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0069\u0000\u0000\u0000\u0032\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0064\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0070\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0002\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006d\u0000\u0000\u0000\u005a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u006c\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0078\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0003\u0000\u0000\u0000\u0003\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0003\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0075\u0000\u0000\u0000\u0072\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0074\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0080\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0074\u0069\u006d\u0065\u0073\u0074\u0061\u006d" +
   "\u0070\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0071\u0075\u0065\u0072\u0079\u0000\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0070\u0061\u0067\u0065\u004e\u0075\u006d\u0062" +
   "\u0065\u0072\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0072\u0065\u0073\u0075\u006c\u0074\u0050\u0065" +
   "\u0072\u0050\u0061\u0067\u0065\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
public static final org.capnproto.SegmentReader b_ada68cc280caeb6f =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u006f\u00eb\u00ca\u0080\u00c2\u008c\u00a6\u00ad" +
   "\u0013\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u00fe\u007f\u00cc\u0062\u00fa\u0023\u00b8\u00ce" +
   "\u0002\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u0012\u0001\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u0017\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u002d\u0000\u0000\u0000\u00af\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u0061\u0070\u006e\u005f\u0063\u006c\u0061" +
   "\u0073\u0073\u0065\u0073\u002e\u0063\u0061\u0070" +
   "\u006e\u0070\u003a\u0043\u006f\u006d\u0070\u006c" +
   "\u0065\u0078\u004d\u0065\u0073\u0073\u0061\u0067" +
   "\u0065\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u00a3\u00e5\u00a8\u009e\u0048\u00ae\u0075\u00a6" +
   "\u0001\u0000\u0000\u0000\u0032\u0000\u0000\u0000" +
   "\u0045\u006e\u0074\u0072\u0079\u0000\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0045\u0000\u0000\u0000\u0052\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0044\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0050\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u004d\u0000\u0000\u0000\u0042\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0048\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0064\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0002\u0000\u0000\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0061\u0000\u0000\u0000\"\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\\\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0078\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0074\u0069\u006d\u0065\u0073\u0074\u0061\u006d" +
   "\u0070\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0073\u0074\u006f\u0072\u0061\u0067\u0065\u0000" +
   "\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u00a3\u00e5\u00a8\u009e\u0048\u00ae\u0075\u00a6" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0061\u0072\u0072\u0000\u0000\u0000\u0000\u0000" +
   "\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
public static final org.capnproto.SegmentReader b_a675ae489ea8e5a3 =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u00a3\u00e5\u00a8\u009e\u0048\u00ae\u0075\u00a6" +
   "\"\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u006f\u00eb\u00ca\u0080\u00c2\u008c\u00a6\u00ad" +
   "\u0001\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\u0042\u0001\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0021\u0000\u0000\u0000\u0077\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u0061\u0070\u006e\u005f\u0063\u006c\u0061" +
   "\u0073\u0073\u0065\u0073\u002e\u0063\u0061\u0070" +
   "\u006e\u0070\u003a\u0043\u006f\u006d\u0070\u006c" +
   "\u0065\u0078\u004d\u0065\u0073\u0073\u0061\u0067" +
   "\u0065\u002e\u0045\u006e\u0074\u0072\u0079\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u0008\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0029\u0000\u0000\u0000\"\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0024\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0030\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u002d\u0000\u0000\u0000\u0032\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0028\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0034\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u006b\u0065\u0079\u0000\u0000\u0000\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u000c\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0076\u0061\u006c\u0075\u0065\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
public static final org.capnproto.SegmentReader b_8c3907983ae7b61a =
   org.capnproto.GeneratedClassSupport.decodeRawBytes(
   "\u0000\u0000\u0000\u0000\u0005\u0000\u0006\u0000" +
   "\u001a\u00b6\u00e7\u003a\u0098\u0007\u0039\u008c" +
   "\u0013\u0000\u0000\u0000\u0001\u0000\u0002\u0000" +
   "\u00fe\u007f\u00cc\u0062\u00fa\u0023\u00b8\u00ce" +
   "\u0001\u0000\u0007\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0015\u0000\u0000\u0000\n\u0001\u0000\u0000" +
   "\u0025\u0000\u0000\u0000\u0007\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0021\u0000\u0000\u0000\u00af\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0063\u0061\u0070\u006e\u005f\u0063\u006c\u0061" +
   "\u0073\u0073\u0065\u0073\u002e\u0063\u0061\u0070" +
   "\u006e\u0070\u003a\u004e\u0065\u0073\u0074\u0065" +
   "\u0064\u004d\u0065\u0073\u0073\u0061\u0067\u0065" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0001\u0000\u0001\u0000" +
   "\u000c\u0000\u0000\u0000\u0003\u0000\u0004\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0045\u0000\u0000\u0000\u0052\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0044\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0050\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0001\u0000\u0000\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0001\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u004d\u0000\u0000\u0000\u001a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0048\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0054\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0001\u0000\u0002\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0051\u0000\u0000\u0000\u008a\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0054\u0000\u0000\u0000\u0003\u0000\u0001\u0000" +
   "\u0060\u0000\u0000\u0000\u0002\u0000\u0001\u0000" +
   "\u0074\u0069\u006d\u0065\u0073\u0074\u0061\u006d" +
   "\u0070\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0005\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0069\u0064\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0070\u0072\u0069\u006d\u0069\u0074\u0069\u0076" +
   "\u0065\u004d\u0065\u0073\u0073\u0061\u0067\u0065" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0095\u0033\u0094\u0009\u00f0\u0035\u004b\u00f3" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
   "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" + "");
}
}

