package com.baeldung.um.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.baeldung.um.web.privilege.PrivilegeLogicRestLiveTest;
import com.baeldung.um.web.privilege.PrivilegeReadOnlyLogicRestLiveTest;
import com.baeldung.um.web.role.RoleLogicRestLiveTest;
import com.baeldung.um.web.role.RoleReadOnlyLogicRestLiveTest;
import com.baeldung.um.web.user.UserLogicRestLiveTest;
import com.baeldung.um.web.user.UserReadOnlyLogicRestLiveTest;

@RunWith(Suite.class)
@SuiteClasses({ // @formatter:off
    PrivilegeLogicRestLiveTest.class
    , PrivilegeReadOnlyLogicRestLiveTest.class

    , RoleLogicRestLiveTest.class
    , RoleReadOnlyLogicRestLiveTest.class

    , UserLogicRestLiveTest.class
    , UserReadOnlyLogicRestLiveTest.class
})
// @formatter:off
public final class LiveLogicSuite {
    //
}
