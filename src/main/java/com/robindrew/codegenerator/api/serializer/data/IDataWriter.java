package com.robindrew.codegenerator.api.serializer.data;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.Charset;

public interface IDataWriter extends DataOutput {

	void writeNull(boolean isNull) throws IOException;

	void writePositiveByte(byte value) throws IOException;

	void writePositiveShort(short value) throws IOException;

	void writePositiveInteger(int value) throws IOException;

	void writePositiveLong(long value) throws IOException;

	void writeByteArray(byte[] array) throws IOException;

	void writeString(String text) throws IOException;

	void writeString(String text, Charset charset) throws IOException;

	<O> void writeObject(O object, IDataSerializer<O> serializer) throws IOException;

}
