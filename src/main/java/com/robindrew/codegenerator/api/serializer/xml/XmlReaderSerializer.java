package com.robindrew.codegenerator.api.serializer.xml;

import java.io.Reader;
import java.io.StringReader;

import com.robindrew.codegenerator.api.serializer.xml.stax.StaxXmlReader;

public class XmlReaderSerializer {

	public IXmlReader newReader(Reader reader) {
		// We could default to either DOM or StAX reader, they appear to have similar timings
		// However the StAX reader should (in theory) will have a much smaller memory footprint ...
		// return DomXmlReader.newInstance(reader);
		return StaxXmlReader.newInstance(reader);
	}

	public <T> T fromXml(IXmlSerializer<T> serializer, String xml) {
		IXmlReader reader = newReader(new StringReader(xml));
		return serializer.readObject(reader);
	}
}
