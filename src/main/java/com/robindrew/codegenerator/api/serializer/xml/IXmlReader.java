package com.robindrew.codegenerator.api.serializer.xml;

public interface IXmlReader {

	boolean emptyElement();

	void startElement(String name);

	void endElement(String name);

	String readElement(String name);

	<V> V readElement(String name, IXmlValueSerializer<V> serializer);

	String readAttribute(String name);

	<V> V readAttribute(String name, IXmlValueSerializer<V> serializer);

	boolean hasAttribute(String name);

	boolean readBoolean(String name);

	char readChar(String name);

	byte readByte(String name);

	short readShort(String name);

	int readInt(String name);

	long readLong(String name);

	float readFloat(String name);

	double readDouble(String name);

	char readCharAttribute(String name);

	byte readByteAttribute(String name);

	short readShortAttribute(String name);

	int readIntAttribute(String name);

	long readLongAttribute(String name);

	boolean readBooleanAttribute(String name);

	float readFloatAttribute(String name);

	double readDoubleAttribute(String name);

	<O> O readObject(IXmlSerializer<O> serializer);
}
