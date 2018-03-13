package com.robindrew.codegenerator.api.serializer.data;

import java.io.IOException;

public interface IDataSerializer<O> extends IDataReadable<O>, IDataWritable<O> {

	@Override
	O readObject(IDataReader reader) throws IOException;

	@Override
	void writeObject(IDataWriter writer, O instance) throws IOException;

}
