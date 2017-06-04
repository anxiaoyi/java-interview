/**
 * Created by zk on 17-6-4.
 *
 * @{link ClassLoader} 的主要方法
 */
public abstract class ClassLoaderMainMethod {

    /**
     * 加载一个类，并返回这个类的实例
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return null;
    }

    /**
     * 根据给定的字节码流定义一个类，只有继承的子类可以使用
     *
     * @param name
     * @param b
     * @param off
     * @param len
     * @return
     * @throws ClassFormatError
     */
    protected final Class<?> defineClass(String name, byte[] b, int off, int len)
            throws ClassFormatError {
        return null;
    }

    /**
     * 自定义类的查找逻辑, {@link #loadClass(String)} 会自动调用 {@link #findClass(String)}
     * 只有继承的子类可以使用
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return null;
    }

    /**
     * 只有继承的子类可以使用，声明了 final 类型，无法被修改
     *
     * @param name
     * @return
     */
    protected final Class<?> findLoadedClass(String name) {
        return null;
    }

}
