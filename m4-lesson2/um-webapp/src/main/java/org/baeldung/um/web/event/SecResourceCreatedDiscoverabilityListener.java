package org.baeldung.um.web.event;

import org.springframework.stereotype.Component;

import org.baeldung.common.web.listeners.ResourceCreatedDiscoverabilityListener;

@Component
class SecResourceCreatedDiscoverabilityListener extends ResourceCreatedDiscoverabilityListener {

    public SecResourceCreatedDiscoverabilityListener() {
        super();
    }

    //

    @Override
    protected final String getBase() {
        return "/";
    }

}
