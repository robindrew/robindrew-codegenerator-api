package com.robindrew.codegenerator.api.serializer.json;

public interface IJsonReader {

	void openObject();

	void closeObject();

	void openArray();

	void closeArray();

	boolean readBoolean(String name);

	char readChar(String name);

	byte readByte(String name);

	short readShort(String name);

	int readInt(String name);

	long readLong(String name);

	float readFloat(String name);

	double readDouble(String name);

	<O> O readObject(IJsonSerializer<O> serializer);
}
