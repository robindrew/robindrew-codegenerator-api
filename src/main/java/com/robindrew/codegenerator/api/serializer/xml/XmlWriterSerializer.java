package com.robindrew.codegenerator.api.serializer.xml;

import java.io.StringWriter;
import java.io.Writer;

public class XmlWriterSerializer {

	public IXmlWriter newWriter(Writer writer) {
		return new XmlWriter(writer);
	}

	public <T> String toXml(IXmlSerializer<T> serializer, T object) {
		StringWriter writer = new StringWriter();
		serializer.writeObject(newWriter(writer), object);
		return writer.toString();
	}
}
