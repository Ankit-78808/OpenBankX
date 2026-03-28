package com.cts.openbankx.security;

import jakarta.servlet.http.HttpServletRequest;

public class EndpointScopeMapper {

    public static String resolveScope(HttpServletRequest request) {

        String uri = request.getRequestURI();

        if (uri.startsWith("/api/v1/aisp/accounts")) {
            return "accounts:read";
        }

        if (uri.startsWith("/api/v1/pisp/payments")) {
            return "payments:write";
        }

        if (uri.startsWith("/api/v1/cbpii/funds-check")) {
            return "funds:read";
        }

        throw new IllegalArgumentException(
                "No scope mapping for endpoint: " + uri);
    }
}