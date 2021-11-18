package com.baeldung.test.common.web.util;

import com.baeldung.common.search.ClientOperation;
import com.baeldung.common.util.SearchField;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

public class ClientConstraintsUtil {

    private ClientConstraintsUtil() {
        throw new AssertionError();
    }

    //

    public static Triple<String, ClientOperation, String> createNameConstraint(final ClientOperation operation, final String nameValue) {
        return createConstraint(operation, SearchField.name.toString(), nameValue);
    }

    public static Triple<String, ClientOperation, String> createIdConstraint(final ClientOperation operation, final Long idValue) {
        return createConstraint(operation, SearchField.id.toString(), idValue.toString());
    }

    public static Triple<String, ClientOperation, String> createConstraint(final ClientOperation operation, final String key, final String value) {
        return new ImmutableTriple<>(key, operation, value);
    }

}
