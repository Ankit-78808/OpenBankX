package com.cts.openbankx.security;

import com.cts.openbankx.model.Token;
import com.cts.openbankx.model.TPPApp;

public class RequestSecurityContext {

    private static final ThreadLocal<Token> TOKEN_CTX = new ThreadLocal<>();
    private static final ThreadLocal<TPPApp> TPP_CTX = new ThreadLocal<>();

    public static void set(Token token, TPPApp app) {
        TOKEN_CTX.set(token);
        TPP_CTX.set(app);
    }

    public static Token getToken() {
        return TOKEN_CTX.get();
    }

    public static TPPApp getTppApp() {
        return TPP_CTX.get();
    }

    public static void clear() {
        TOKEN_CTX.remove();
        TPP_CTX.remove();
    }
}