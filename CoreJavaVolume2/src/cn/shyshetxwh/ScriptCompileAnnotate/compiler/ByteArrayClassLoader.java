package cn.shyshetxwh.ScriptCompileAnnotate.compiler;

import java.util.Iterator;

public class ByteArrayClassLoader extends ClassLoader {
    private Iterable<ByteArrayClass>classes;

    public ByteArrayClassLoader(Iterable<ByteArrayClass> classes) {
        this.classes = classes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        for (ByteArrayClass cl: classes) {
            if (cl.getName().equals("/"+name.replace(".","/")+".class"))
            {
                byte[] bytes = cl.getCode();
                return defineClass(name,bytes,0,bytes.length);
            }
        }
        throw new ClassNotFoundException(name);
    }
}
