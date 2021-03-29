package cn.shyshetxwh.rmi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * FileName: JavaclassExecuter
 * Author:   admin+shyshetxwh
 * Date:     2021/03/04 22:26
 */
public class JavaClassExecuter {
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "cn/shyshetxwh/rmi/HackSystem");
        try {
            FileOutputStream fos = new FileOutputStream("d:/aaa.class");
            fos.write(modiBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);

        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
