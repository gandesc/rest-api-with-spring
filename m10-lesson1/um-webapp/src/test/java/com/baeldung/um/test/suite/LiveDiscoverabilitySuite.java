package com.baeldung.um.test.suite;

import com.baeldung.um.common.web.root.RootDiscoverabilityRestLiveTest;
import com.baeldung.um.web.privilege.PrivilegeDiscoverabilityRestLiveTest;
import com.baeldung.um.web.role.RoleDiscoverabilityRestLiveTest;
import com.baeldung.um.web.user.UserDiscoverabilityRestLiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserDiscoverabilityRestLiveTest.class, RoleDiscoverabilityRestLiveTest.class, PrivilegeDiscoverabilityRestLiveTest.class, RootDiscoverabilityRestLiveTest.class })
public final class LiveDiscoverabilitySuite {
    //
}
