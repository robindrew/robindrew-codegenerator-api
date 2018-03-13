package com.robindrew.codegenerator.api.serializer.json.serializer.collection;

import java.util.List;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class ListSerializer<E> implements IJsonSerializer<List<E>> {

	private final IJsonSerializer<E> elementSerializer;

	public ListSerializer(IJsonSerializer<E> elementSerializer) {
		if (elementSerializer == null) {
			throw new NullPointerException("elementSerializer");
		}
		this.elementSerializer = elementSerializer;
	}

	@Override
	public List<E> readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, List<E> list) {
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
