package com.baeldung.test.common.client.template;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;

import com.baeldung.client.marshall.IMarshaller;
import com.baeldung.client.template.IRestClientWithUri;
import com.baeldung.common.interfaces.IDto;
import com.baeldung.common.interfaces.IOperations;
import com.baeldung.common.search.ClientOperation;
import com.jayway.restassured.specification.RequestSpecification;

public interface IRestClient<T extends IDto> extends IOperations<T>, IRestClientAsResponse<T>, IRestClientWithUri<T> {

    // search

    List<T> searchPaginated(final Triple<String, ClientOperation, String> idOp, final Triple<String, ClientOperation, String> nameOp, final int page, final int size);

    // template

    RequestSpecification givenReadAuthenticated();

    RequestSpecification givenDeleteAuthenticated();

    IMarshaller getMarshaller();

    String getUri();

}
