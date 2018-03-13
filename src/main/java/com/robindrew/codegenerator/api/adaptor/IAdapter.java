package com.robindrew.codegenerator.api.adaptor;

public interface IAdapter<F, T> {

	T adapt(F from);

}
