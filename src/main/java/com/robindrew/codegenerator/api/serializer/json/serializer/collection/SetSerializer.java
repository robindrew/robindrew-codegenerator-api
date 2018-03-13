package com.robindrew.codegenerator.api.serializer.json.serializer.collection;

import java.util.Set;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class SetSerializer<E> implements IJsonSerializer<Set<E>> {

	private final IJsonSerializer<E> elementSerializer;

	public SetSerializer(IJsonSerializer<E> elementSerializer) {
		if (elementSerializer == null) {
			throw new NullPointerException("elementSerializer");
		}
		this.elementSerializer = elementSerializer;
	}

	@Override
	public Set<E> readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Set<E> list) {
		if (list == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openArray();
		for (E element : list) {
			writer.writeComma(index++);
			writer.writeObject(element, elementSerializer);
		}
		writer.closeArray();
	}

}
