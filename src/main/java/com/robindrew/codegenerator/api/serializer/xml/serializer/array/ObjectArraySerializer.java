package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import java.lang.reflect.Array;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.api.serializer.xml.serializer.ObjectSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.IntegerSerializer;

public class ObjectArraySerializer<E> extends ObjectSerializer<E[]> {

	private static final IXmlValueSerializer<Integer> SIZE_SERIALIZER = new IntegerSerializer("size");

	private final IXmlSerializer<E> elementSerializer;
	private final Class<E> elementType;

	public ObjectArraySerializer(String name, IXmlSerializer<E> elementSerializer, Class<E> elementType) {
		super(name);
		this.elementSerializer = elementSerializer;
		this.elementType = elementType;
	}

	@SuppressWarnings("unchecked")
	private E[] newArray(int size) {
		return (E[]) Array.newInstance(elementType, size);
	}

	@Override
	public E[] readObject(IXmlReader reader) {
		reader.startElement(getName());

		// Read list
		E[] array = null;
		if (reader.hasAttribute("size")) {
			int size = reader.readAttribute("size", SIZE_SERIALIZER);
			array = newArray(size);
			for (int i = 0; i < size; i++) {
				E element = elementSerializer.readObject(reader);
				array[i] = element;
			}
		}

		reader.endElement(getName());
		return array;
	}

	@Override
	public void writeObject(IXmlWriter writer, E[] array) {
		if (array == null) {
			writer.emptyElement(getName());
			return;
		}

		writer.startElement(getName());

		// Write list
		writer.writeAttribute("size", array.length, SIZE_SERIALIZER);
		for (E element : array) {
			elementSerializer.writeObject(writer, element);
		}

		writer.endElement(getName());
	}
}
