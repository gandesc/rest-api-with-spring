package com.baeldung.um.service.search;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.baeldung.common.persistence.model.INameableEntity;
import com.baeldung.test.common.service.search.AbstractSearchIntegrationTest;
import com.baeldung.um.spring.UmClientConfig;
import com.baeldung.um.spring.UmContextConfig;
import com.baeldung.um.spring.UmPersistenceJpaConfig;
import com.baeldung.um.spring.UmServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UmPersistenceJpaConfig.class, UmServiceConfig.class, UmContextConfig.class, UmClientConfig.class }, loader = AnnotationConfigContextLoader.class)
public abstract class SecSearchIntegrationTest<T extends INameableEntity> extends AbstractSearchIntegrationTest<T> {

    //

}
