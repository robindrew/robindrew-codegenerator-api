package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class EnumSerializer<E extends Enum<E>> implements IJsonSerializer<E> {

	private final Class<E> enumClass;

	public EnumSerializer(Class<E> enumClass) {
		if (enumClass == null) {
			throw new NullPointerException("enumClass");
		}
		this.enumClass = enumClass;
	}

	public Class<E> getEnumClass() {
		return enumClass;
	}

	@Override
	public E readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, E value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeString(value.name());
	}

}
