package com.robindrew.codegenerator.api.serializer.xml;

public interface IXmlSerializer<O> {

	O readObject(IXmlReader reader);

	void writeObject(IXmlWriter writer, O object);
}
