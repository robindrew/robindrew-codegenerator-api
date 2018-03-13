package com.robindrew.codegenerator.api.serializer.xml.serializer.collection;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.api.serializer.xml.serializer.ObjectSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.IntegerSerializer;

public class ListSerializer<E> extends ObjectSerializer<List<E>> {

	private static final IXmlValueSerializer<Integer> SIZE_SERIALIZER = new IntegerSerializer("size");

	private final IXmlSerializer<E> elementSerializer;

	public ListSerializer(String name, IXmlSerializer<E> elementSerializer) {
		super(name);
		this.elementSerializer = elementSerializer;
	}

	protected List<E> newList(int size) {
		// Default to array list as it is efficient for most list operations
		return new ArrayList<E>(size);
	}

	@Override
	public List<E> readObject(IXmlReader reader) {
		reader.startElement(getName());

		// Read list
		List<E> list = null;
		if (reader.hasAttribute("size")) {
			int size = reader.readAttribute("size", SIZE_SERIALIZER);
			list = newList(size);
			for (int i = 0; i < size; i++) {
				E element = elementSerializer.readObject(reader);
				list.add(element);
			}
		}

		reader.endElement(getName());
		return list;
	}

	@Override
	public void writeObject(IXmlWriter writer, List<E> list) {
		if (list == null) {
			writer.emptyElement(getName());
			return;
		}

		writer.startElement(getName());

		// Write list
		writer.writeAttribute("size", list.size(), SIZE_SERIALIZER);
		for (E element : list) {
			elementSerializer.writeObject(writer, element);
		}

		writer.endElement(getName());
	}
}
