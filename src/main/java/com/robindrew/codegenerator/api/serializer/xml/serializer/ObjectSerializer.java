package com.robindrew.codegenerator.api.serializer.xml.serializer;

import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;

public abstract class ObjectSerializer<O> implements IXmlSerializer<O> {

	private final String name;

	protected ObjectSerializer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
