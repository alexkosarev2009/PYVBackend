package org.pyv.response;

public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {}
