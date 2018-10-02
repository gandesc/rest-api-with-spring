package com.baeldung.common.interfaces;

import java.io.Serializable;

public interface IWithName extends Serializable {

    String getName();

    void setName(final String name);

}
