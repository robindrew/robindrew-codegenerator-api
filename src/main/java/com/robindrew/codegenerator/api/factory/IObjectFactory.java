package com.robindrew.codegenerator.api.factory;

import java.util.Set;

public interface IObjectFactory<O> {

	Set<Class<?>> getTypes();

	boolean containsType(Object key);

	Class<? extends O> getType(Object key);

	O newInstance(Object key);

}
