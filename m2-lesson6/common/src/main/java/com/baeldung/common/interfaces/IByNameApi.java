package com.baeldung.common.interfaces;

public interface IByNameApi<T extends IWithName> {

    T findOneByName(final String name);

}
