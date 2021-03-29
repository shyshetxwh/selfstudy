package cn.shyshetxwh.examples;

/**
 * FileName: ThisEscape
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 10:39
 */


public class ThisEscape {
    public ThisEscape(EventSource source) {
        System.out.println(this);
        source.registerListener(new MyEventListener() {
            @Override
            public void OnEvent(MyEvent e) {
                System.out.println(this);
                doSomething(e);
            }
        });
    }

    interface EventSource {
        void registerListener(MyEventListener e);
    }

    interface MyEventListener {
        void OnEvent(MyEvent e);
    }

    interface MyEvent {
    }

    void doSomething(MyEvent e) {

    }

    public static void main(String[] args) {

    }
}
