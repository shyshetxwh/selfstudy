package cn.shyshetxwh.ScriptCompileAnnotate.compiler;



import javax.swing.*;
import javax.tools.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class CompilerTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        List<ByteArrayClass> classFileObjects=new ArrayList<>();
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        JavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);
        fileManager=new ForwardingJavaFileManager<JavaFileManager>(fileManager)
        {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                if (kind== JavaFileObject.Kind.CLASS)
                {
                    ByteArrayClass fileObject = new ByteArrayClass(className);
                    classFileObjects.add(fileObject);
                    return fileObject;
                }
                else
                {
                    return super.getJavaFileForOutput(location, className, kind, sibling);
                }
            }
        };

        String frameClassName="cn.shyshetxwh.ScriptCompileAnnotate.buttons2.ButtonFrame";

        StandardJavaFileManager fileManager1 = compiler.getStandardFileManager(null, null, null);
        List<JavaFileObject> sources = new ArrayList<>();
        List<String>list=new ArrayList<>();
        Collections.addAll(list,frameClassName.replace(".","/")+".java");
        for (JavaFileObject object : fileManager1.getJavaFileObjectsFromStrings(list)) {
            sources.add(object);
        }

        JavaFileObject source=buildSource(frameClassName);
        System.out.println(source);
        List<JavaFileObject> sourceList=new ArrayList<>();
        Collections.addAll(sourceList,source);
        System.out.println(sourceList);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, sourceList);
        System.out.println(classFileObjects.size());
        Boolean result = task.call();
        for (Diagnostic<? extends JavaFileObject> d : diagnosticCollector.getDiagnostics()) {
            System.out.println(d.getKind()+": "+d.getMessage(null));
        }
        fileManager.close();
        if (!result)
        {
            System.out.println("Compilation failed.");
            System.exit(1);
        }

        ByteArrayClassLoader loader = new ByteArrayClassLoader(classFileObjects);
        JFrame frame = (JFrame) loader.loadClass("x.Frame").getConstructor().newInstance();
        EventQueue.invokeLater(()->{
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("CompilerTest");
            frame.setVisible(true);
        });

    }

    private static JavaFileObject buildSource(String superclassName) throws IOException, ClassNotFoundException {
        StringBuilder builder = new StringBuilder();
        builder.append("package x;\n\n");
        builder.append("public class Frame extends " + superclassName + " {\n");
        builder.append("protected void addEventHandlers() {\n");builder.append("package x;\n\n");
        builder.append("public class Frame extends " + superclassName + " {\n");
        builder.append("protected void addEventHandlers() {\n");
        Properties props = new Properties();
        Class<?> name = Class.forName(superclassName);
        InputStream in = name.getResourceAsStream("action.properties");

        props.load(in);


        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            String beanName= (String) entry.getKey();
            String eventCode= (String) entry.getValue();
            builder.append(beanName + ".addActionListener(event -> {\n");
            builder.append(eventCode);
            builder.append("\n} );\n");
        }
        builder.append("} }\n");
        return new StringSource("x.Frame",builder.toString());
    }
}
