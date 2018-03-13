package com.robindrew.codegenerator.api.serializer.xml.serializer.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.api.serializer.xml.serializer.ObjectSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.IntegerSerializer;

public class MapSerializer<K, V> extends ObjectSerializer<Map<K, V>> {

	private static final IXmlValueSerializer<Integer> SIZE_SERIALIZER = new IntegerSerializer("size");

	private final IXmlSerializer<K> keySerializer;
	private final IXmlSerializer<V> valueSerializer;

	public MapSerializer(String name, IXmlSerializer<K> keySerializer, IXmlSerializer<V> valueSerializer) {
		super(name);
		this.keySerializer = keySerializer;
		this.valueSerializer = valueSerializer;
	}

	protected Map<K, V> newMap(int size) {
		// Default to linked hash map so visible order is maintained during serialization
		return new LinkedHashMap<K, V>(size);
	}

	@Override
	public Map<K, V> readObject(IXmlReader reader) {
		reader.startElement(getName());

		// Read map
		Map<K, V> map = null;
		if (reader.hasAttribute("size")) {
			int size = reader.readAttribute("size", SIZE_SERIALIZER);
			map = newMap(size);
			for (int i = 0; i < size; i++) {
				K key = keySerializer.readObject(reader);
				V value = valueSerializer.readObject(reader);
				map.put(key, value);
			}
		}

		reader.endElement(getName());
		return map;
	}

	@Override
	public void writeObject(IXmlWriter writer, Map<K, V> map) {
		if (map == null) {
			writer.emptyElement(getName());
			return;
		}

		writer.startElement(getName());

		// Write map
		writer.writeAttribute("size", map.size(), SIZE_SERIALIZER);
		for (Entry<K, V> entry : map.entrySet()) {
			keySerializer.writeObject(writer, entry.getKey());
			valueSerializer.writeObject(writer, entry.getValue());
		}

		writer.endElement(getName());
	}
}
