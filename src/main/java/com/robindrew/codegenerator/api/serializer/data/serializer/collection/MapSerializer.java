package com.robindrew.codegenerator.api.serializer.data.serializer.collection;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataSerializer;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class MapSerializer<K, V> extends ObjectSerializer<Map<K, V>> {

	private final IDataSerializer<K> keySerializer;
	private final IDataSerializer<V> valueSerializer;

	public MapSerializer(IDataSerializer<K> keySerializer, IDataSerializer<V> valueSerializer, boolean nullable) {
		super(nullable);
		this.keySerializer = keySerializer;
		this.valueSerializer = valueSerializer;
	}

	protected Map<K, V> newMap(int size) {
		return new LinkedHashMap<K, V>(size);
	}

	@Override
	protected Map<K, V> readValue(IDataReader reader) throws IOException {
		int size = reader.readPositiveInteger();
		Map<K, V> map = newMap(size);
		for (int i = 0; i < size; i++) {
			K key = reader.readObject(keySerializer);
			V value = reader.readObject(valueSerializer);
			map.put(key, value);
		}
		return map;
	}

	@Override
	protected void writeValue(IDataWriter writer, Map<K, V> map) throws IOException {
		writer.writePositiveInteger(map.size());
		for (Entry<K, V> entry : map.entrySet()) {
			writer.writeObject(entry.getKey(), keySerializer);
			writer.writeObject(entry.getValue(), valueSerializer);
		}
	}

}
