package com.robindrew.codegenerator.api.serializer.xml;

public interface IXmlWriter {

	void emptyElement(String name);

	void startElement(String name);

	void endElement(String name);

	void writeAttribute(String name, Object value);

	<V> void writeAttribute(String name, V value, IXmlValueSerializer<V> serializer);

	void writeElement(String name, Object value);

	<V> void writeElement(String name, V value, IXmlValueSerializer<V> serializer);

	void writeBoolean(String name, boolean value);

	void writeChar(String name, char value);

	void writeByte(String name, byte value);

	void writeShort(String name, short value);

	void writeInt(String name, int value);

	void writeLong(String name, long value);

	void writeFloat(String name, float value);

	<O> void writeObject(O object, IXmlSerializer<O> serializer);

	void writeDouble(String name, double value);

}
