package com.baeldung.common.web;

import org.springframework.stereotype.Component;

import com.baeldung.common.interfaces.IDto;

@Component
public class UriMapper implements IUriMapper {

    public UriMapper() {
        super();
    }

    // API

    /**
     * - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
     */
    @Override
    public <T extends IDto> String getUriBase(final Class<T> clazz) {
        String simpleName = clazz.getSimpleName()
            .toString()
            .toLowerCase();
        if (simpleName.endsWith("dto")) {
            simpleName = simpleName.substring(0, simpleName.length() - 3);
        }
        return simpleName + "s";
    }

}
