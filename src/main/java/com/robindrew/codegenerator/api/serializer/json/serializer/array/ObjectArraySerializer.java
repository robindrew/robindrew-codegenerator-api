package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import java.lang.reflect.Array;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class ObjectArraySerializer<E> implements IJsonSerializer<E[]> {

	private final IJsonSerializer<E> elementSerializer;
	private final Class<E> elementType;

	public ObjectArraySerializer(IJsonSerializer<E> elementSerializer, Class<E> elementType) {
		this.elementSerializer = elementSerializer;
		this.elementType = elementType;
	}

	@SuppressWarnings({ "unchecked" })
	protected E[] newArray(int size) {
		return (E[]) Array.newInstance(elementType, size);
	}

	@Override
	public E[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, E[] list) {
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
