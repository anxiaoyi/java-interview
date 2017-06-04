package traceclassloading;

/**
 * Created by zk on 17-6-4.
 */
public class UseParent {

    // VM Arguments:
    // -XX:+TraceClassLoading
    public static void main(String[] args) {
        System.out.println(Child.v);
    }

}
