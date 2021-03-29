package cn.shyshetxwh.examples;


/**
 * FileName: SafeListener
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 10:56
 */
public class SafeListener {
    private final ThisEscape.MyEventListener listener;

    private SafeListener() {
        listener = new ThisEscape.MyEventListener() {
            @Override
            public void OnEvent(ThisEscape.MyEvent e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(ThisEscape.EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    private void doSomething(ThisEscape.MyEvent e) {
    }
}
