package com.baeldung.um.web.event;

import com.baeldung.common.web.listeners.ResourceCreatedDiscoverabilityListener;
import org.springframework.stereotype.Component;

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
