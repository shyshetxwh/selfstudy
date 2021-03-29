package cn.shyshetxwh.rmi;

/**
 * FileName: HotSwapClassLoader
 * Author:   admin+shyshetxwh
 * Date:     2021/03/04 22:19
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
