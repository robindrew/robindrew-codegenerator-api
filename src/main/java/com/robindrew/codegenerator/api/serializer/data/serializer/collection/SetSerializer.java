package com.robindrew.codegenerator.api.serializer.data.serializer.collection;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataSerializer;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class SetSerializer<E> extends ObjectSerializer<Set<E>> {

	private final IDataSerializer<E> serializer;

	public SetSerializer(IDataSerializer<E> serializer, boolean nullable) {
		super(nullable);
		this.serializer = serializer;
	}

	protected Set<E> newSet(int size) {
		return new LinkedHashSet<E>(size);
	}

	@Override
	protected Set<E> readValue(IDataReader reader) throws IOException {
		int size = reader.readPositiveInteger();
		Set<E> set = newSet(size);
		for (int i = 0; i < size; i++) {
			E element = reader.readObject(serializer);
			set.add(element);
		}
		return set;
	}

	@Override
	protected void writeValue(IDataWriter writer, Set<E> list) throws IOException {
		writer.writePositiveInteger(list.size());
		for (E value : list) {
			writer.writeObject(value, serializer);
		}
	}

}
