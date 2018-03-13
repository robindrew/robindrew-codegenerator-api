package com.robindrew.codegenerator.api.serializer.json;

public interface IJsonWriter {

	void openObject();

	void closeObject();

	void openArray();

	void closeArray();

	void writeNull();

	void writeComma();

	void writeComma(int index);

	void writeName(String name);

	void writeBoolean(boolean value);

	void writeChar(char value);

	void writeByte(byte value);

	void writeShort(short value);

	void writeInt(int value);

	void writeLong(long value);

	void writeFloat(float value);

	void writeDouble(double value);

	void writeString(String value);

	void writeNull(String name, boolean comma);

	void writeBoolean(String name, boolean value, boolean comma);

	void writeChar(String name, char value, boolean comma);

	void writeByte(String name, byte value, boolean comma);

	void writeShort(String name, short value, boolean comma);

	void writeInt(String name, int value, boolean comma);

	void writeLong(String name, long value, boolean comma);

	void writeFloat(String name, float value, boolean comma);

	void writeDouble(String name, double value, boolean comma);

	void writeString(String name, String value, boolean comma);

	<O> void writeObject(O value, IJsonSerializer<O> serializer);

	<O> void writeObject(String name, O value, IJsonSerializer<O> serializer, boolean comma);
}
