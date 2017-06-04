package traceclassloading;

/**
 * Created by zk on 17-6-4.
 */
public class Parent {

    static {
        System.out.println("Parent init");
    }

    public static int v = 100;

}
