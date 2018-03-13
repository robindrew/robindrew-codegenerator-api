package com.robindrew.codegenerator.api.sql.builder.h2;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.SqlBuilder;

public class H2SqlBuilder extends SqlBuilder implements IH2SqlBuilder {

	@Override
	public ISqlBuilder value(byte[] value) {
		throw new UnsupportedOperationException();
	}

}
