package cn.shyshetxwh.XML.read;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XMLReadTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String filename="config-schema.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //打开xml文档验证开关
        factory.setValidating(true);
        //判断是否是schema，如果是，对factory进行设置
        if (filename.contains("-schema"))
        {
            //打开命名空间
            factory.setNamespaceAware(true);
            final String JAXP_SCHEMA_LANGUAGE="http://java.sun.com/xml/jaxp/properties/schemaLanguage";
            final String W3C_XML_SCHEMA="http://www.w3.org/2001/XMLSchema";
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);

        }
        //忽略文本节点中的空白字符
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        //为解析报告过程中设置警告和错误处理器
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.err.println("Warning: "+exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.err.println("Error: "+exception.getMessage());
                System.exit(0);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.err.println("Fetal error: "+exception.getMessage());
                System.exit(0);
            }
        });
        //解析文档
        Document doc = builder.parse(filename);
        Map<String,Object>config=parseConfig(doc.getDocumentElement());
        System.out.println(config);

    }

    private static Map<String, Object> parseConfig(Element e) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        HashMap<String, Object> result = new HashMap<>();
        NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Element child = (Element) children.item(i);
            String name = child.getAttribute("id");
            Object value=parseObject((Element)child.getFirstChild());
            result.put(name,value);
        }
        return result;
    }

    private static Object parseObject(Element e) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String tagName = e.getTagName();
        if (tagName.equals("factory"))
        {
            return parseFactory(e);
        }
        else if (tagName.equals("construct"))
        {
            return parseConstruct(e);
        }
        else
        {
            String childData = ((CharacterData) e.getFirstChild()).getData();
            if (tagName.equals("int"))
            {
                return Integer.valueOf(childData);
            }
            else if (tagName.equals("boolean"))
            {
                return Boolean.valueOf(childData);
            }
            else
            {
                return childData;
            }
        }
    }

    private static Object parseConstruct(Element e) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = e.getAttribute("class");
        Object[] args = parseArgs(e.getChildNodes());
        Class<?>[] parameterTypes = getParameterTypes(args);
        Constructor<?> constructor = Class.forName(className).getConstructor(parameterTypes);
        return constructor.newInstance(args);
    }

    private static Object parseFactory(Element e) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String className = e.getAttribute("class");
        String methodName = e.getAttribute("method");
        Object[] args=parseArgs(e.getChildNodes());
        Class<?>[] parameterTypes=getParameterTypes(args);
        Method method = Class.forName(className).getMethod(methodName, parameterTypes);
        return method.invoke(null,args);
    }

    private static Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] result = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            Class<?> cl = args[i].getClass();
            if (cl==Integer.class)
            {
                result[i]=int.class;
            }
            else if (cl==Boolean.class)
            {
                result[i]=boolean.class;
            }
            else
            {
                result[i]=cl;
            }
        }
        return result;
    }

    private static Object[] parseArgs(NodeList elements) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Object[] result = new Object[elements.getLength()];
        for (int i = 0; i < elements.getLength(); i++) {
            result[i]=parseObject((Element)elements.item(i));
        }
        return result;
    }
}
