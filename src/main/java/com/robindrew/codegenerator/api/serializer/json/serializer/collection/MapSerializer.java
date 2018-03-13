package com.robindrew.codegenerator.api.serializer.json.serializer.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;
import com.robindrew.codegenerator.api.serializer.json.JsonWriter;

public class MapSerializer<K, V> implements IJsonSerializer<Map<K, V>> {

	private final IJsonSerializer<K> keySerializer;
	private final IJsonSerializer<V> valueSerializer;

	public MapSerializer(IJsonSerializer<K> keySerializer, IJsonSerializer<V> valueSerializer) {
		if (keySerializer == null) {
			throw new NullPointerException("keySerializer");
		}
		if (valueSerializer == null) {
			throw new NullPointerException("valueSerializer");
		}
		this.keySerializer = keySerializer;
		this.valueSerializer = valueSerializer;
	}

	protected Map<K, V> newMap(int size) {
		return new LinkedHashMap<K, V>();
	}

	@Override
	public Map<K, V> readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Map<K, V> map) {
		if (map == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openObject();
		for (Entry<K, V> entry : map.entrySet()) {
			writer.writeComma(index++);
			writer.writeName(getName(entry.getKey()));
			writer.writeObject(entry.getValue(), valueSerializer);
		}
		writer.closeArray();
	}

	protected String getName(K key) {
		if (key instanceof CharSequence) {
			return key.toString();
		}

		// Name MUST be a string
		JsonWriter writer = new JsonWriter();
		keySerializer.writeObject(writer, key);
		return writer.getWriter().toString();
	}

}
