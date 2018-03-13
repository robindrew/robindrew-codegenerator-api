package com.robindrew.codegenerator.api.serializer.data;

import java.io.DataInput;
import java.io.IOException;
import java.nio.charset.Charset;

public interface IDataReader extends DataInput {

	boolean readNull() throws IOException;

	byte readPositiveByte() throws IOException;

	short readPositiveShort() throws IOException;

	int readPositiveInteger() throws IOException;

	long readPositiveLong() throws IOException;

	byte[] readByteArray() throws IOException;

	String readString(Charset charset) throws IOException;

	String readString() throws IOException;

	<O> O readObject(IDataSerializer<O> serializer) throws IOException;

}
