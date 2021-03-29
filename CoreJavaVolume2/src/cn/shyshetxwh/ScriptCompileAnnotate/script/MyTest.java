package cn.shyshetxwh.ScriptCompileAnnotate.script;

import cn.shyshetxwh.ScriptCompileAnnotate.compiler.ByteArrayClass;

import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class MyTest {
    @Test
    public void test1() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String language="nashorn";
        Properties events = new Properties();
        final String frameClassName="cn.shyshetxwh.ScriptCompileAnnotate.buttons1.ButtonFrame";
        JFrame frame= (JFrame) Class.forName(frameClassName).getConstructor().newInstance();
        InputStream in=frame.getClass().getResourceAsStream(language+".properties");
        System.out.println(in);
        events.load(in);
        System.out.println(events);
    }

    @Test
    public void test2() throws IOException {
        String frameClassName="cn.shyshetxwh.ScriptCompileAnnotate.buttons2.ButtonFrame";
        Properties props = new Properties();
        Path path = Paths.get("src/"+frameClassName.replace(".", "/"));

        Path parent = path.getParent();
        Path resolve = parent.resolve("action.properties");
        System.out.println(resolve);
        InputStream in = Files.newInputStream(resolve);
        props.load(in);
        System.out.println(props);

    }

    @Test
    public void test3() throws NoSuchFieldException, IOException {
        File file=new File("../aaa.jpg");
        InputStream in=new FileInputStream(file);
        int n=0;
        byte[] bytes = new byte[1024];
        while((n=in.read(bytes))!=-1)
        {
            String s = new String(bytes, 0, n);
            System.out.println(s);
        }


    }
}
