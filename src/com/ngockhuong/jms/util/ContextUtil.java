package com.ngockhuong.jms.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ContextUtil {
    public static Context getInitialContext() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial",
                "org.jnp.interfaces.NamingContextFactory");
        properties.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
        properties.setProperty("java.naming.provider.url", "localhost:1099");

        Context context = new InitialContext(properties);

        return context;
    }
}
