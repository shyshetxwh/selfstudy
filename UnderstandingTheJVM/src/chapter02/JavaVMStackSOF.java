package chapter02;

/**
 * FileName: JavaVMStackSOF
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/23 0023 16:07
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable t) {
            System.out.println("stack length:" + sof.stackLength);
            throw t;
        }
    }

}
