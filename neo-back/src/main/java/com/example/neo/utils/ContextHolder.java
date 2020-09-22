package com.example.neo.utils;

import lombok.Data;

public class ContextHolder {
    public static final String USER_ID = "USER_ID";
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static void setContext(UserContext context) {
        userContext.set(context);
    }

    public static String getCurrentUserId() {
        UserContext context = userContext.get();
        if (context.userId.length() > 0) {
            return context.userId;
        }
        return null;
    }

    public static void remove() {
        userContext.remove();
    }

    @Data
    public static class UserContext {
        String userId;

        public UserContext(String userId) {
            this.userId = userId;
        }
    }
}
