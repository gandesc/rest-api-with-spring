package com.baeldung.um.web.user;

import com.baeldung.um.client.template.UserRestClient;
import com.baeldung.um.test.live.UmReadOnlyLogicRestLiveTest;
import com.baeldung.um.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public class UserReadOnlyLogicRestLiveTest extends UmReadOnlyLogicRestLiveTest<UserDto> {

    @Autowired
    private UserRestClient api;

    public UserReadOnlyLogicRestLiveTest() {
        super(UserDto.class);
    }

    // tests

    // template method

    @Override
    protected final UserRestClient getApi() {
        return api;
    }

}
