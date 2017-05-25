package com.ght.learn.ffs.tool.context;

public abstract class SessionUserHolder {

    private static ThreadLocal<SessionUser> SESSION_USER_CONTEXT = new ThreadLocal<SessionUser>();

    public static SessionUser getSessionUser() {
	return SESSION_USER_CONTEXT.get();
    }

    public static void setSessionUser(SessionUser sessionUser) {
	if (sessionUser != null) {
	    SESSION_USER_CONTEXT.set(sessionUser);
	}
    }

    public static void removeSessionUser() {
	SESSION_USER_CONTEXT.remove();
    }
}