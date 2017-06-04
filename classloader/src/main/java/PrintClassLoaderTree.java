/**
 * Created by zk on 17-6-4.
 */
public class PrintClassLoaderTree {

    public static void main(String[] args) {
        ClassLoader cl = PrintClassLoaderTree.class.getClassLoader();
        while (cl != null) {
            System.out.println(cl);
            cl = cl.getParent();
        }
    }

}
