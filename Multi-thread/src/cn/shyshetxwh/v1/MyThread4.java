package cn.shyshetxwh.v1;

/**
 * FileName: MyThread4
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:08
 */
public class MyThread4 extends Thread {
    private int count=5;

    public MyThread4(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count>0){
            count--;
            System.out.println(this.currentThread().getName()+": count= "+count);
        }
    }
}
