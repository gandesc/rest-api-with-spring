package com.baeldung.um.test.suite;

import com.baeldung.common.web.listeners.PaginatedResultsRetrievedDiscoverabilityListenerUnitTest;
import com.baeldung.um.service.impl.PrincipalServiceUnitTest;
import com.baeldung.um.service.impl.PrivilegeServiceUnitTest;
import com.baeldung.um.service.impl.RoleServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ // @formatter:off
    PrincipalServiceUnitTest.class
    ,RoleServiceUnitTest.class
    ,PrivilegeServiceUnitTest.class
    ,PaginatedResultsRetrievedDiscoverabilityListenerUnitTest.class
    // ,Base64UnitTest.class
    // ,ParseQueryStringUnitTest.class
    // ,UriUnitTest.class
})// @formatter:on
public final class UnitSuite {
    //
}
