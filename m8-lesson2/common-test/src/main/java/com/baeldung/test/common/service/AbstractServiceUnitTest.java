package com.baeldung.test.common.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertSame;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.util.ReflectionTestUtils;

import com.baeldung.common.persistence.event.AfterEntityCreateEvent;
import com.baeldung.common.persistence.event.AfterEntityUpdateEvent;
import com.baeldung.common.persistence.event.BeforeEntityCreateEvent;
import com.baeldung.common.persistence.model.IEntity;
import com.baeldung.common.persistence.service.IRawService;
import com.baeldung.test.common.util.IDUtil;
import com.google.common.collect.Lists;

/**
 * A base class for service layer unit tests.
 */
public abstract class AbstractServiceUnitTest<T extends IEntity> {

    protected ApplicationEventPublisher eventPublisher;

    // tests

    public void before() {
        when(getDAO().findAll()).thenReturn(Lists.<T> newArrayList());
        eventPublisher = mock(ApplicationEventPublisher.class);
        ReflectionTestUtils.setField(getApi(), "eventPublisher", eventPublisher);
    }

    @Test
    public final void whenServiceIsInitialized_thenNoException() {
        // When
        // Then
    }

    // create

    @Test(expected = NullPointerException.class)
    public void whenCreateIsTriggeredForNullEntity_thenException() {
        // When
        getApi().create(null);

        // Then
    }

    @Test
    public void whenCreateIsTriggered_thenNoException() {
        // When
        getApi().create(stubDaoSave(createNewEntity()));

        // Then
    }

    @Test
    public void whenCreatingANewEntity_thenEntityIsSaved() {
        // Given
        final T entity = stubDaoSave(createNewEntity());

        // When
        getApi().create(entity);

        // Then
        verify(getDAO()).save(entity);
    }

    @Test
    public void whenCreatingANewEntity_thenBeforeCreateEventIsPublished() {
        // Given
        final T entity = createNewEntity();
        stubDaoSave(entity);

        // When
        getApi().create(entity);

        // Then
        verify(getEventPublisher()).publishEvent(isA(BeforeEntityCreateEvent.class));
    }

    @Test
    public void whenCreatingANewEntity_thenEventIsPublished() {
        // Given
        final T entity = createNewEntity();
        stubDaoSave(entity);

        // When
        getApi().create(entity);

        // Then
        verify(getEventPublisher()).publishEvent(isA(AfterEntityCreateEvent.class));
    }

    // update

    @Test
    public void whenUpdateIsTriggered_thenNoException() {
        // When
        getApi().update(givenEntityExists(stubDaoSave(createSimulatedExistingEntity())));

        // Then
    }

    @Test(expected = NullPointerException.class)
    public void givenNullEntity_whenUpdate_thenException() {
        getApi().update(null);
    }

    @Test
    public void whenUpdateIsTriggered_thenEntityIsUpdated() {
        // When
        final T entity = createSimulatedExistingEntity();
        getApi().update(entity);

        // Then
        verify(getDAO()).save(entity);
    }

    @Test
    public void givenEntity_whenUpdate_thenEventIsPublished() {
        // Given
        final T entity = createSimulatedExistingEntity();
        stubDaoSave(entity);

        // When
        getApi().update(entity);

        // Then
        verify(getEventPublisher()).publishEvent(isA(AfterEntityUpdateEvent.class));
    }

    // find - paged

    @Test
    public void whenPageOfEntitiesIsRetrieved_thenResultIsCorrect() {
        // Given
        final PageRequest pageRequest = PageRequest.of(1, 10);
        final Page<T> page = new PageImpl<T>(Lists.<T> newArrayList(), pageRequest, 10L);
        when(getDAO().findAll(eq(pageRequest))).thenReturn(page);

        // When
        final Page<T> found = getApi().findAllPaginatedRaw(1, 10);

        // Then
        assertSame(page, found);
    }

    // find - all

    @Test
    public void whenGetAllIsTriggered_thenNoException() {
        // When
        getApi().findAll();

        // Then
    }

    @Test
    public void whenGetAllIsTriggered_thenAllEntitiesAreRetrieved() {
        // When
        getApi().findAll();

        // Then
        verify(getDAO()).findAll();
    }

    // find - one

    @Test
    public final void whenGetIsTriggered_thenNoException() {
        configureGet(1l);

        // When
        getApi().findOne(1l);

        // Then
    }

    @Test
    public final void whenGetIsTriggered_thenEntityIsRetrieved() {
        configureGet(1l);

        // When
        getApi().findOne(1l);

        // Then
        verify(getDAO()).findById(1l);
    }

    @Test
    public void whenEntityByIdIsFound_thenItIsReturned() {
        // Given
        final T entity = createSimulatedExistingEntity();
        givenEntityExists(entity);

        // When
        final T found = getApi().findOne(entity.getId());

        // Then
        assertThat(found, is(equalTo(entity)));
    }

    // delete

    /**
     * - note: the responsibility of ensuring data integrity belongs to the database; because this is an unit test, then no exception is thrown
     */
    @Test
    public void givenResourceDoesNotExist_whenDeleteIsTriggered_thenNoExceptions() {
        final long randomId = IDUtil.randomPositiveLong();
        givenEntityExists(randomId);

        // When
        getApi().delete(randomId);

        // Then
    }

    @Test
    public void givenResourceExists_whenDeleteIsTriggered_thenNoExceptions() {
        final long id = IDUtil.randomPositiveLong();

        // Given
        givenEntityExists(id);

        // When
        getApi().delete(id);

        // Then
    }

    @Test
    public void givenResourceExists_whenDeleteIsTriggered_thenEntityIsDeleted() {
        // Given
        final long id = IDUtil.randomPositiveLong();
        final T entityToBeDeleted = givenEntityExists(id);

        // When
        getApi().delete(id);

        // Then
        verify(getDAO()).delete(entityToBeDeleted);
    }

    // delete - all

    @Test
    public void whenDeleteAllEntities_thenEntitiesAreDeleted() {
        // When
        getApi().deleteAll();

        // Then
        verify(getDAO()).deleteAll();
    }

    // utils

    protected final T givenEntityExists(final long id) {
        final T entity = createNewEntity();
        entity.setId(id);
        when(getDAO().findById(id)).thenReturn(Optional.of(entity));
        return entity;
    }

    protected final T givenEntityExists(final T entity) {
        when(getDAO().findById(entity.getId())).thenReturn(Optional.of(entity));
        return entity;
    }

    protected final T stubDaoSave(final T entity) {
        when(getDAO().save(entity)).thenReturn(entity);
        return entity;
    }

    /**
     * Creates and returns the instance of entity that is existing (ie ID is not null).
     * 
     * @return the created entity
     */
    protected T createSimulatedExistingEntity() {
        final T entity = createNewEntity();
        entity.setId(IDUtil.randomPositiveLong());

        when(getDAO().findById(entity.getId())).thenReturn(Optional.of(entity));
        return entity;
    }

    /**
     * Gets the application event publisher mock.
     * 
     * @return the event publisher mock.
     */
    protected final ApplicationEventPublisher getEventPublisher() {
        return eventPublisher;
    }

    // template

    protected abstract T createNewEntity();

    protected abstract void changeEntity(final T entity);

    protected abstract T configureGet(final long id);

    /**
     * Gets the service that is need to be tested.
     * 
     * @return the service.
     */
    protected abstract IRawService<T> getApi();

    /**
     * Gets the DAO mock.
     * 
     * @return the DAO mock.
     */
    protected abstract PagingAndSortingRepository<T, Long> getDAO();

}
