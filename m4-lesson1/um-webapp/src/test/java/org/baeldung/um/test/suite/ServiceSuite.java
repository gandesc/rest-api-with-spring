package org.baeldung.um.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.baeldung.um.service.main.UserServiceIntegrationTest;
import org.baeldung.um.service.main.PrivilegeServiceIntegrationTest;
import org.baeldung.um.service.main.RoleServiceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ // @formatter:off
    UserServiceIntegrationTest.class,
    PrivilegeServiceIntegrationTest.class,
    RoleServiceIntegrationTest.class
})
// @formatter:on
public final class ServiceSuite {
    //
}
