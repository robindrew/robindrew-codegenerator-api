package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;
import java.lang.reflect.Array;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataSerializer;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class ObjectArraySerializer<E> extends ObjectSerializer<E[]> {

	private final IDataSerializer<E> serializer;
	private final Class<E> componentType;

	public ObjectArraySerializer(boolean nullable, IDataSerializer<E> serializer, Class<E> componentType) {
		super(nullable);
		if (serializer == null) {
			throw new NullPointerException("serializer");
		}
		if (componentType == null) {
			throw new NullPointerException("componentType");
		}
		this.serializer = serializer;
		this.componentType = componentType;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected E[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		E[] array = (E[]) Array.newInstance(componentType, length);
		for (int i = 0; i < length; i++) {
			array[i] = reader.readObject(serializer);
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, E[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (E element : array) {
			writer.writeObject(element, serializer);
		}
	}

}
