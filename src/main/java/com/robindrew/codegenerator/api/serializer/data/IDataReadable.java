package com.robindrew.codegenerator.api.serializer.data;

import java.io.IOException;

public interface IDataReadable<O> {

	O readObject(IDataReader reader) throws IOException;

}
