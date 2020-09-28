package com.baeldung.um;

import com.baeldung.um.dto.UserDtoIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ // @formatter:off
    PersistenceSpringIntegrationTest.class,
    ServiceSpringIntegrationTest.class,
    WebSpringIntegrationTest.class,
    UserDtoIntegrationTest.class
}) // @formatter:on
public class IntegrationTestSuite {
    //
}