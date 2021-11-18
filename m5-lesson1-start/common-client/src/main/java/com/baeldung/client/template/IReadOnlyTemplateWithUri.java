package com.baeldung.client.template;

import com.baeldung.common.interfaces.IDto;

import java.util.List;

public interface IReadOnlyTemplateWithUri<T extends IDto> {

    // find - one

    T findOneByUri(final String uri);

    // find - all

    List<T> findAllByUri(final String uri);

}
