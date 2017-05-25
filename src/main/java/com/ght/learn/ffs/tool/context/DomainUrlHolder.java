package com.ght.learn.ffs.tool.context;

public abstract class DomainUrlHolder {

    private static ThreadLocal<String> DOMAIN_URL_CONTEXT = new ThreadLocal<String>();

    public static String getDomainUrl() {
	return DOMAIN_URL_CONTEXT.get();
    }

    public static void setDomainUrl(String domainUrl) {
	if (domainUrl != null) {
	    DOMAIN_URL_CONTEXT.set(domainUrl);
	}
    }

    public static void removeDomainUrl() {
	DOMAIN_URL_CONTEXT.remove();
    }
}