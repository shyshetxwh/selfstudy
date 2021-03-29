package chapter10.namecheckannotation;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;

import static javax.lang.model.element.Modifier.*;

/**
 * FileName: NameChecker
 * Author:   admin+shyshetxwh
 * Date:     2021/03/05 22:39
 * 程序名称规范的编译器插件：
 * 如果程序命名不规范，将会输出一个编译器的WARNING信息
 */
public class NameChecker {
    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    /**
     * 对Java程序命名进行检查，根据《Java语言规范》第三版第6.8节的要求，Java程序命名应当符合下列格式：
     * <p>
     * <p>
     * 类或接口：符合驼式命名法，首字母大写。
     * 方法：符合驼式命名法，首字母小写。
     * 字段：
     * <p>
     * 类、实例变量: 符合驼式命名法，首字母小写。
     * 常量: 要求全部大写。
     *
     * @param element
     */
    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    /**
     * 名称检查器实现类，继承了JDK 6中新提供的ElementScanner6
     * 将会以Visitor模式访问抽象语法树中的元素
     */
    private class NameCheckScanner extends ElementScanner8<Void, Void> {
        /**
         * 此方法用于检查Java类
         *
         * @param e
         * @param unused
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void unused) {
            scan(e.getTypeParameters(), unused);
            checkCamelCase(e, true);
            super.visitType(e, unused);
            return null;
        }

        /**
         * 检查方法命名是否合法
         *
         * @param e
         * @param unused
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void unused) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName()))
                    messager.printMessage(Diagnostic.Kind.WARNING, "一个普通方法 “" + name +
                            "” 不应当与类名重复，避免与构造函数产生混淆", e);
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, unused);
            return null;
        }

        /**
         * 检查变量命名是否合法
         *
         * @param e
         * @param unused
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void unused) {
            //如果这个Variable是枚举或常量，则按大写命名检查，否则按照驼式命名法规则检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                checkAllCaps(e);
            } else {
                checkCamelCase(e, false);
            }
            return null;
        }


        /**
         * 判断一个变量是否为常量
         *
         * @param e
         * @return
         */
        private boolean heuristicallyConstant(VariableElement e) {
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE)
                return true;
            else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(PUBLIC, STATIC, FINAL)))
                return true;
            else
                return false;
        }

        /**
         * 检查传入的Element是否符合驼式命名法，如果不符合，则输出警告信息
         *
         * @param e
         * @param b
         */
        private void checkCamelCase(Element e, boolean b) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if (!b) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称 “" + name + "” 应当以小写字母开头", e);
                    return;
                }
            } else if (Character.isLowerCase(firstCodePoint)) {
                if (b) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称 “" + name + "” 应当以大写字母开头", e);
                    return;
                }
            } else {
                conventional = false;
            }

            if (conventional) {
                int cp = firstCodePoint;
                //判断这个名字是否有两个连续大写的字母
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    } else
                        previousUpper = false;
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称 “" + name + "” 应当符合驼式命名法", e);
            }
        }

        /**
         * 大写命名检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或者大写字母
         *
         * @param e
         */
        private void checkAllCaps(Element e) {
            String name = e.getSimpleName().toString();

            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (!Character.isUpperCase(firstCodePoint))
                conventional = false;
            else {
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int) '_') {
                        if (previousUnderscore) {
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "常量 “" + name + "” 应当全部以大写字母或下划线命名，并以字母开头", e);
            }
        }
    }
}
