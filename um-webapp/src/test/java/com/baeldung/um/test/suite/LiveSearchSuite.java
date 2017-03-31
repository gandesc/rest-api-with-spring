package com.baeldung.um.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.baeldung.um.web.privilege.PrivilegeSearchRestLiveTest;
import com.baeldung.um.web.role.RoleSearchRestLiveTest;
import com.baeldung.um.web.user.UserSearchRestLiveTest;

@RunWith(Suite.class)
@SuiteClasses({ // @formatter:off
    UserSearchRestLiveTest.class,
    RoleSearchRestLiveTest.class,
    PrivilegeSearchRestLiveTest.class
})
// @formatter:off
public final class LiveSearchSuite {
    //
}
