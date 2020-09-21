package com.example.neo.utils;

public class ContextHolder {
    public static final String USER_ID = "USER_ID";
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public void setContext(UserContext context) {
        userContext.set(context);
    }

    public static String getCurrentUserId() {
        UserContext context = userContext.get();
        if (context.userId.length() > 0) {
            return context.userId;
        }
        return null;
    }

    static void remove() {
        userContext.remove();
    }

    static class UserContext {
        String userId;
    }
}
