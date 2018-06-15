package com.baeldung.common.interfaces;

import javax.annotation.Nullable;

public interface IByNameApi<T extends IWithName> {
    @Nullable
    T findByName(final String name);

}
