package cn.shyshetxwh.ScriptCompileAnnotate.script;

import com.sun.javafx.text.ScriptMapper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ScriptTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            try {
                ScriptEngineManager manager = new ScriptEngineManager();
                System.out.println("Available factories: ");
                for (ScriptEngineFactory factory : manager.getEngineFactories()) {
                    System.out.println(factory.getEngineName());
                }
                String language="nashorn";
                ScriptEngine engine = manager.getEngineByName(language);
                if (engine==null)
                {
                    System.err.println("No engine for "+language);
                    System.exit(1);
                }
                final String frameClassName="cn.shyshetxwh.ScriptCompileAnnotate.buttons1.ButtonFrame";
                JFrame frame= (JFrame) Class.forName(frameClassName).getConstructor().newInstance();
                InputStream in = frame.getClass().getResourceAsStream("init." + language);
                if (in!=null)engine.eval(new InputStreamReader(in));
                Map<String,Component>components=new HashMap<>();
                getComponentBindings(frame,components);
                components.forEach((name,c)->engine.put(name,c));

                Properties events = new Properties();
                in=frame.getClass().getResourceAsStream(language+".properties");
                events.load(in);

                for (Object e : events.keySet()) {
                    String[] s = ((String) e).split("\\.");
                    addListener(s[0],s[1],(String)events.get(e),engine,components);
                }
                frame.setTitle("ScriptTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }

        });
    }

    private static void addListener(String beanName, String eventName, final String scriptCode, ScriptEngine engine, Map<String, Component> components) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Component bean = components.get(beanName);
        EventSetDescriptor descriptor=getEventSetDescriptor(bean,eventName);
        if (descriptor==null)return;
        descriptor.getAddListenerMethod().invoke(bean, Proxy.newProxyInstance(null,new Class[]{descriptor.getListenerType()},
                (proxy, method, args) -> {
                   engine.eval(scriptCode);
                   return null;
                }));

    }

    private static EventSetDescriptor getEventSetDescriptor(Object bean, String eventName) throws IntrospectionException {
        for (EventSetDescriptor descriptor : Introspector.getBeanInfo(bean.getClass()).getEventSetDescriptors()) {
            if (descriptor.getName().equals(eventName))return descriptor;
        }
        return null;
    }

    private static void getComponentBindings(Component c, Map<String, Component> namedComponents) {
        String name = c.getName();
        if (name!=null)namedComponents.put(name,c);
        if (c instanceof Container)
        {
            for (Component child : ((Container) c).getComponents()) {
                getComponentBindings(child,namedComponents);
            }
        }
    }
}
