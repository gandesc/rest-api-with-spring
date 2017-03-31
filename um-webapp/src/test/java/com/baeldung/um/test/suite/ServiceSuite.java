package com.baeldung.um.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.baeldung.um.persistence.PersistenceSpringIntegrationTest;
import com.baeldung.um.service.ServiceSpringIntegrationTest;
import com.baeldung.um.service.main.UserServiceIntegrationTest;
import com.baeldung.um.service.main.PrivilegeServiceIntegrationTest;
import com.baeldung.um.service.main.RoleServiceIntegrationTest;
import com.baeldung.um.service.search.UserSearchIntegrationTest;
import com.baeldung.um.service.search.PrivilegeSearchIntegrationTest;
import com.baeldung.um.service.search.RoleSearchIntegrationTest;
import com.baeldung.um.web.WebSpringIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ // @formatter:off
    UserSearchIntegrationTest.class
    ,UserServiceIntegrationTest.class

    ,PrivilegeSearchIntegrationTest.class
    ,PrivilegeServiceIntegrationTest.class

    ,RoleSearchIntegrationTest.class
    ,RoleServiceIntegrationTest.class

    ,WebSpringIntegrationTest.class
    ,ServiceSpringIntegrationTest.class
    ,PersistenceSpringIntegrationTest.class
})
// @formatter:on
public final class ServiceSuite {
    //
}
