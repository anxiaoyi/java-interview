package traceclassloading;

/**
 * Created by zk on 17-6-4.
 */
public class Child extends Parent {

    static {
        System.out.println("Child init");
    }

}
