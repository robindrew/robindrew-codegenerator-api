package com.robindrew.codegenerator.api.serializer.xml.serializer.collection;

import java.util.LinkedHashSet;
import java.util.Set;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.api.serializer.xml.serializer.ObjectSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.IntegerSerializer;

public class SetSerializer<E> extends ObjectSerializer<Set<E>> {

	private static final IXmlValueSerializer<Integer> SIZE_SERIALIZER = new IntegerSerializer("size");

	private final IXmlSerializer<E> elementSerializer;

	public SetSerializer(String name, IXmlSerializer<E> elementSerializer) {
		super(name);
		this.elementSerializer = elementSerializer;
	}

	protected Set<E> newSet(int size) {
		// Default to linked hash set so visible order is maintained during serialization
		return new LinkedHashSet<E>(size);
	}

	@Override
	public Set<E> readObject(IXmlReader reader) {
		reader.startElement(getName());

		// Read set
		Set<E> set = null;
		if (reader.hasAttribute("size")) {
			int size = reader.readAttribute("size", SIZE_SERIALIZER);
			set = newSet(size);
			for (int i = 0; i < size; i++) {
				E element = elementSerializer.readObject(reader);
				set.add(element);
			}
		}

		reader.endElement(getName());
		return set;
	}

	@Override
	public void writeObject(IXmlWriter writer, Set<E> set) {
		if (set == null) {
			writer.emptyElement(getName());
			return;
		}

		writer.startElement(getName());

		// Write set
		writer.writeAttribute("size", set.size(), SIZE_SERIALIZER);
		for (E element : set) {
			elementSerializer.writeObject(writer, element);
		}

		writer.endElement(getName());
	}
}
