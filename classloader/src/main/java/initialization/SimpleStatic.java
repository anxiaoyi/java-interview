package initialization;

/**
 * Created by zk on 17-6-4.
 */
public class SimpleStatic {

    public static int id = 1;
    public static int number;

    static {
        System.out.println("SimpleStatic Executed");
        number = 4;
    }

}
