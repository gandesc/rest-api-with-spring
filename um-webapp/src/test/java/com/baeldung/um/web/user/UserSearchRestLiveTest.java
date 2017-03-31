package com.baeldung.um.web.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.client.IDtoOperations;
import com.baeldung.um.client.template.UserRestClient;
import com.baeldung.um.model.UserDtoOpsImpl;
import com.baeldung.um.persistence.model.User;
import com.baeldung.um.test.live.UmSearchRestLiveTest;

public class UserSearchRestLiveTest extends UmSearchRestLiveTest<User> {

    @Autowired
    private UserRestClient restTemplate;
    @Autowired
    private UserDtoOpsImpl entityOps;

    public UserSearchRestLiveTest() {
        super();
    }

    // tests

    // template

    @Override
    protected final UserRestClient getApi() {
        return restTemplate;
    }

    @Override
    protected final IDtoOperations<User> getEntityOps() {
        return entityOps;
    }

}
