/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.pelion.kafka.connect;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class BodyData extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5900333998107544777L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BodyData\",\"namespace\":\"com.pelion.kafka.connect\",\"fields\":[{\"name\":\"method\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"uri\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"accept\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"content_type\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"payload_b64\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<BodyData> ENCODER =
      new BinaryMessageEncoder<BodyData>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<BodyData> DECODER =
      new BinaryMessageDecoder<BodyData>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<BodyData> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<BodyData> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<BodyData> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<BodyData>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this BodyData to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a BodyData from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a BodyData instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static BodyData fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.String method;
   private java.lang.String uri;
   private java.lang.String accept;
   private java.lang.String content_type;
   private java.lang.String payload_b64;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BodyData() {}

  /**
   * All-args constructor.
   * @param method The new value for method
   * @param uri The new value for uri
   * @param accept The new value for accept
   * @param content_type The new value for content_type
   * @param payload_b64 The new value for payload_b64
   */
  public BodyData(java.lang.String method, java.lang.String uri, java.lang.String accept, java.lang.String content_type, java.lang.String payload_b64) {
    this.method = method;
    this.uri = uri;
    this.accept = accept;
    this.content_type = content_type;
    this.payload_b64 = payload_b64;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return method;
    case 1: return uri;
    case 2: return accept;
    case 3: return content_type;
    case 4: return payload_b64;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: method = value$ != null ? value$.toString() : null; break;
    case 1: uri = value$ != null ? value$.toString() : null; break;
    case 2: accept = value$ != null ? value$.toString() : null; break;
    case 3: content_type = value$ != null ? value$.toString() : null; break;
    case 4: payload_b64 = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'method' field.
   * @return The value of the 'method' field.
   */
  public java.lang.String getMethod() {
    return method;
  }


  /**
   * Sets the value of the 'method' field.
   * @param value the value to set.
   */
  public void setMethod(java.lang.String value) {
    this.method = value;
  }

  /**
   * Gets the value of the 'uri' field.
   * @return The value of the 'uri' field.
   */
  public java.lang.String getUri() {
    return uri;
  }


  /**
   * Sets the value of the 'uri' field.
   * @param value the value to set.
   */
  public void setUri(java.lang.String value) {
    this.uri = value;
  }

  /**
   * Gets the value of the 'accept' field.
   * @return The value of the 'accept' field.
   */
  public java.lang.String getAccept() {
    return accept;
  }


  /**
   * Sets the value of the 'accept' field.
   * @param value the value to set.
   */
  public void setAccept(java.lang.String value) {
    this.accept = value;
  }

  /**
   * Gets the value of the 'content_type' field.
   * @return The value of the 'content_type' field.
   */
  public java.lang.String getContentType() {
    return content_type;
  }


  /**
   * Sets the value of the 'content_type' field.
   * @param value the value to set.
   */
  public void setContentType(java.lang.String value) {
    this.content_type = value;
  }

  /**
   * Gets the value of the 'payload_b64' field.
   * @return The value of the 'payload_b64' field.
   */
  public java.lang.String getPayloadB64() {
    return payload_b64;
  }


  /**
   * Sets the value of the 'payload_b64' field.
   * @param value the value to set.
   */
  public void setPayloadB64(java.lang.String value) {
    this.payload_b64 = value;
  }

  /**
   * Creates a new BodyData RecordBuilder.
   * @return A new BodyData RecordBuilder
   */
  public static com.pelion.kafka.connect.BodyData.Builder newBuilder() {
    return new com.pelion.kafka.connect.BodyData.Builder();
  }

  /**
   * Creates a new BodyData RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new BodyData RecordBuilder
   */
  public static com.pelion.kafka.connect.BodyData.Builder newBuilder(com.pelion.kafka.connect.BodyData.Builder other) {
    if (other == null) {
      return new com.pelion.kafka.connect.BodyData.Builder();
    } else {
      return new com.pelion.kafka.connect.BodyData.Builder(other);
    }
  }

  /**
   * Creates a new BodyData RecordBuilder by copying an existing BodyData instance.
   * @param other The existing instance to copy.
   * @return A new BodyData RecordBuilder
   */
  public static com.pelion.kafka.connect.BodyData.Builder newBuilder(com.pelion.kafka.connect.BodyData other) {
    if (other == null) {
      return new com.pelion.kafka.connect.BodyData.Builder();
    } else {
      return new com.pelion.kafka.connect.BodyData.Builder(other);
    }
  }

  /**
   * RecordBuilder for BodyData instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BodyData>
    implements org.apache.avro.data.RecordBuilder<BodyData> {

    private java.lang.String method;
    private java.lang.String uri;
    private java.lang.String accept;
    private java.lang.String content_type;
    private java.lang.String payload_b64;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.pelion.kafka.connect.BodyData.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.method)) {
        this.method = data().deepCopy(fields()[0].schema(), other.method);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.uri)) {
        this.uri = data().deepCopy(fields()[1].schema(), other.uri);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.accept)) {
        this.accept = data().deepCopy(fields()[2].schema(), other.accept);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.content_type)) {
        this.content_type = data().deepCopy(fields()[3].schema(), other.content_type);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.payload_b64)) {
        this.payload_b64 = data().deepCopy(fields()[4].schema(), other.payload_b64);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing BodyData instance
     * @param other The existing instance to copy.
     */
    private Builder(com.pelion.kafka.connect.BodyData other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.method)) {
        this.method = data().deepCopy(fields()[0].schema(), other.method);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.uri)) {
        this.uri = data().deepCopy(fields()[1].schema(), other.uri);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.accept)) {
        this.accept = data().deepCopy(fields()[2].schema(), other.accept);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.content_type)) {
        this.content_type = data().deepCopy(fields()[3].schema(), other.content_type);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.payload_b64)) {
        this.payload_b64 = data().deepCopy(fields()[4].schema(), other.payload_b64);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'method' field.
      * @return The value.
      */
    public java.lang.String getMethod() {
      return method;
    }


    /**
      * Sets the value of the 'method' field.
      * @param value The value of 'method'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder setMethod(java.lang.String value) {
      validate(fields()[0], value);
      this.method = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'method' field has been set.
      * @return True if the 'method' field has been set, false otherwise.
      */
    public boolean hasMethod() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'method' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder clearMethod() {
      method = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'uri' field.
      * @return The value.
      */
    public java.lang.String getUri() {
      return uri;
    }


    /**
      * Sets the value of the 'uri' field.
      * @param value The value of 'uri'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder setUri(java.lang.String value) {
      validate(fields()[1], value);
      this.uri = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'uri' field has been set.
      * @return True if the 'uri' field has been set, false otherwise.
      */
    public boolean hasUri() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'uri' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder clearUri() {
      uri = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'accept' field.
      * @return The value.
      */
    public java.lang.String getAccept() {
      return accept;
    }


    /**
      * Sets the value of the 'accept' field.
      * @param value The value of 'accept'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder setAccept(java.lang.String value) {
      validate(fields()[2], value);
      this.accept = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'accept' field has been set.
      * @return True if the 'accept' field has been set, false otherwise.
      */
    public boolean hasAccept() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'accept' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder clearAccept() {
      accept = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'content_type' field.
      * @return The value.
      */
    public java.lang.String getContentType() {
      return content_type;
    }


    /**
      * Sets the value of the 'content_type' field.
      * @param value The value of 'content_type'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder setContentType(java.lang.String value) {
      validate(fields()[3], value);
      this.content_type = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'content_type' field has been set.
      * @return True if the 'content_type' field has been set, false otherwise.
      */
    public boolean hasContentType() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'content_type' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder clearContentType() {
      content_type = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'payload_b64' field.
      * @return The value.
      */
    public java.lang.String getPayloadB64() {
      return payload_b64;
    }


    /**
      * Sets the value of the 'payload_b64' field.
      * @param value The value of 'payload_b64'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder setPayloadB64(java.lang.String value) {
      validate(fields()[4], value);
      this.payload_b64 = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'payload_b64' field has been set.
      * @return True if the 'payload_b64' field has been set, false otherwise.
      */
    public boolean hasPayloadB64() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'payload_b64' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.BodyData.Builder clearPayloadB64() {
      payload_b64 = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BodyData build() {
      try {
        BodyData record = new BodyData();
        record.method = fieldSetFlags()[0] ? this.method : (java.lang.String) defaultValue(fields()[0]);
        record.uri = fieldSetFlags()[1] ? this.uri : (java.lang.String) defaultValue(fields()[1]);
        record.accept = fieldSetFlags()[2] ? this.accept : (java.lang.String) defaultValue(fields()[2]);
        record.content_type = fieldSetFlags()[3] ? this.content_type : (java.lang.String) defaultValue(fields()[3]);
        record.payload_b64 = fieldSetFlags()[4] ? this.payload_b64 : (java.lang.String) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<BodyData>
    WRITER$ = (org.apache.avro.io.DatumWriter<BodyData>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BodyData>
    READER$ = (org.apache.avro.io.DatumReader<BodyData>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.method);

    out.writeString(this.uri);

    if (this.accept == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.accept);
    }

    if (this.content_type == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.content_type);
    }

    if (this.payload_b64 == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.payload_b64);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.method = in.readString();

      this.uri = in.readString();

      if (in.readIndex() != 1) {
        in.readNull();
        this.accept = null;
      } else {
        this.accept = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.content_type = null;
      } else {
        this.content_type = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.payload_b64 = null;
      } else {
        this.payload_b64 = in.readString();
      }

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.method = in.readString();
          break;

        case 1:
          this.uri = in.readString();
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.accept = null;
          } else {
            this.accept = in.readString();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.content_type = null;
          } else {
            this.content_type = in.readString();
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.payload_b64 = null;
          } else {
            this.payload_b64 = in.readString();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










