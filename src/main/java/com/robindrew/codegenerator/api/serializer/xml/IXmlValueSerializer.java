package com.robindrew.codegenerator.api.serializer.xml;

public interface IXmlValueSerializer<O> {

	O readValue(String text);

	String writeValue(O object);
}
