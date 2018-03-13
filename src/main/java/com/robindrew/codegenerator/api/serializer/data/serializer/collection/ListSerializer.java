package com.robindrew.codegenerator.api.serializer.data.serializer.collection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataSerializer;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class ListSerializer<E> extends ObjectSerializer<List<E>> {

	private final IDataSerializer<E> serializer;

	public ListSerializer(IDataSerializer<E> serializer, boolean nullable) {
		super(nullable);
		this.serializer = serializer;
	}

	protected List<E> newList(int size) {
		return new ArrayList<E>(size);
	}

	@Override
	protected List<E> readValue(IDataReader reader) throws IOException {
		int size = reader.readPositiveInteger();
		List<E> list = newList(size);
		for (int i = 0; i < size; i++) {
			E element = reader.readObject(serializer);
			list.add(element);
		}
		return list;
	}

	@Override
	protected void writeValue(IDataWriter writer, List<E> list) throws IOException {
		writer.writePositiveInteger(list.size());
		for (E value : list) {
			writer.writeObject(value, serializer);
		}
	}

}
