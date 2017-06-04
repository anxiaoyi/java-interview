import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by zk on 17-6-2.
 */
public abstract class WebappClassLoaderBase extends URLClassLoader {

    public WebappClassLoaderBase(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

}
