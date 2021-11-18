package org.baeldung.client.util;

import org.springframework.http.HttpHeaders;

import org.baeldung.client.marshall.IMarshaller;

public final class HeaderUtil {

    private HeaderUtil() {
        throw new AssertionError();
    }

    // API

    public static HttpHeaders createContentTypeHeaders(final IMarshaller marshaller) {
        final HttpHeaders headers = new HttpHeaders() {
            {
                set(com.google.common.net.HttpHeaders.CONTENT_TYPE, marshaller.getMime());
            }
        };
        return headers;
    }

    public static HttpHeaders createAcceptHeaders(final IMarshaller marshaller) {
        final HttpHeaders headers = new HttpHeaders() {
            {
                set(com.google.common.net.HttpHeaders.ACCEPT, marshaller.getMime());
            }
        };
        return headers;
    }

    public static HttpHeaders createContentTypeAndBasicAuthHeaders(final IMarshaller marshaller, final String basicAuthorizationHeader) {
        final HttpHeaders headers = HeaderUtil.createContentTypeHeaders(marshaller);
        headers.set(com.google.common.net.HttpHeaders.AUTHORIZATION, basicAuthorizationHeader);
        return headers;
    }

}
