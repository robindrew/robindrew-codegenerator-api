package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class EnumSerializer<E extends Enum<E>> extends ValueSerializer<E> {

	private final Class<E> enumClass;

	public EnumSerializer(Class<E> enumClass, String name) {
		super(name);
		if (enumClass == null) {
			throw new NullPointerException("enumClass");
		}
		this.enumClass = enumClass;
	}

	@Override
	public E readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Enum.valueOf(enumClass, value);
	}
}
