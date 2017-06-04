package spi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zk on 17-6-4.
 *
 * 此段代码并不可以编译执行，意在说明系统类访问应用类的整体逻辑
 */
public class XMLParser {

    private static final String PREFIX = "META-INF/services/";

    public static DocumentBuilderFactory newInstance() {
        return FactoryFinder.find(
                /* The default property name according to the JAXP spec */
                DocumentBuilderFactory.class, // "javax.xml.parsers.DocumentBuilderFactory"
                /* The fallback implementation class name */
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
    }

    static class FactoryFinder {
        public static <T> T find(Class<T> type, String fallbackClassName) {
            final String factoryId = type.getName();

            // 1. 先检查系统属性 system property
            String systemProp = ss.getSystemProperty(factoryId);
            if (systemProp != null) {
                // return newInstance(type, systemProp, null, true);
            }

            // 2. try to read from $java.home/lib/jaxp.properties
            // ...

            // 3. Try Jar Service Provider Mechanism
            T provider = findServiceProvider(type);
            if (provider != null) {
                return provider;
            }

            return null;
        }

        private static <T> T findServiceProvider(final Class<T> type) throws Exception {
            String fullName = PREFIX + type.getName();

            // 注意这个地方:
            // 1. 不是采用 FactoryFinder.class.getClassLoader() 来获取的 ClassLoader
            // 2. 采用的是线程上下文 ClassLoader
            // 3. 默认情况下，上下文 ClassLoader 就是应用 ClassLoader
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream is = ss.getResourceAsStream(cl, fullName);
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String factoryClassName = rd.readLine();
            return newInstance(factoryClassName, cl, false, useBSClsLoader);
        }
    }
    class DocumentBuilderFactory {}

}
