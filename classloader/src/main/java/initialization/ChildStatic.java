package initialization;

/**
 * Created by zk on 17-6-4.
 */
public class ChildStatic extends SimpleStatic {

    static {
        System.out.println("ChildStatic Executed");
        number = 2;
    }

    public static void main(String[] args) {
        System.out.println(number);
    }

}
