package com.robindrew.codegenerator.api.sql.builder.derby;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.SqlBuilder;

public class DerbySqlBuilder extends SqlBuilder implements IDerbySqlBuilder {

	@Override
	public ISqlBuilder value(byte[] value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ISqlBuilder autoIncrement() {
		return sql(" GENERATED ALWAYS AS IDENTITY (START WITH 1)");
	}

}
