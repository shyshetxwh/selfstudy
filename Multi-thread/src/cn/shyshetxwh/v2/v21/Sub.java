package cn.shyshetxwh.v2.v21;

/**
 * FileName: Sub
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 18:54
 */
public class Sub extends Main {
    public synchronized void operateSubMethod() {
        try {
            while (i > 0) {
                i--;
                System.out.println("sub i = " + i);
                Thread.sleep(100);
                super.operateMainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
