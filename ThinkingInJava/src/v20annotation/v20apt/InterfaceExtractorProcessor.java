package v20annotation.v20apt;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InterfaceExtractorProcessor implements AnnotationProcessor {
    private final AnnotationProcessorEnvironment env;
    private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<MethodDeclaration>();

    public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
    }

    @Override
    public void process() {
        for (TypeDeclaration typeDel : env.getSpecifiedTypeDeclarations()) {
            ExtractInterface annot = typeDel
                    .getAnnotation(ExtractInterface.class);
            if (annot == null) {
                break;
            }
            for (MethodDeclaration m : typeDel.getMethods()) {
                if (m.getModifiers().toString().contains("public")
                        && !(m.getModifiers().toString().contains("static"))) {
                    interfaceMethods.add(m);
                }
            }
            if (interfaceMethods.size() > 0) {
                try {
                    PrintWriter writer = env.getFiler().createSourceFile(
                            annot.value());
                    writer.println("package "
                            + typeDel.getPackage().getQualifiedName() + ";");
                    writer.println("public interface " + annot.value() + " {");
                    for (MethodDeclaration m : interfaceMethods) {
                        writer.print("\tpublic ");
                        writer.print(m.getReturnType() + " ");
                        writer.print(m.getSimpleName() + "(");
                        int i = 0;
                        for (ParameterDeclaration param : m.getParameters()) {
                            writer.print(param.getType() + " "
                                    + param.getSimpleName());
                            if (++i < m.getParameters().size()) {
                                writer.print(",");
                            }
                        }
                        writer.println(");");
                    }
                    writer.println("}");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
