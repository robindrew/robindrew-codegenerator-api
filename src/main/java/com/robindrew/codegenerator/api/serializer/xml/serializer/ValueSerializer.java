package com.robindrew.codegenerator.api.serializer.xml.serializer;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;

public abstract class ValueSerializer<V> extends ObjectSerializer<V> implements IXmlValueSerializer<V> {

	protected ValueSerializer(String name) {
		super(name);
	}

	@Override
	public V readObject(IXmlReader reader) {
		String value = reader.readElement(getName());
		if (value == null) {
			return null;
		}
		return readValue(value);
	}

	@Override
	public void writeObject(IXmlWriter writer, V object) {
		String value = object == null ? null : writeValue(object);
		writer.writeElement(getName(), value);
	}

	@Override
	public String writeValue(V object) {
		return object.toString();
	}
}
