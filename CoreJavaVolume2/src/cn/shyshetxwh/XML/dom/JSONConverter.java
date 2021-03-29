package cn.shyshetxwh.XML.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JSONConverter {
    private static Map<Character,String>replacements=new HashMap<>();
    static {
        replacements.put('\b',"\\b");
        replacements.put('\f',"\\f");
        replacements.put('\n',"\\n");
        replacements.put('\r',"\\r");
        replacements.put('"',"\\\"");
        replacements.put('\t',"\\t");
        replacements.put('\\',"\\\\");
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String filename="server.xml";
        //1.创建一个DocumentBuilder对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //2.现在可以读入某个文档
        Document doc = builder.parse(filename);
        //3.获取xml的根元素
        Element root = doc.getDocumentElement();

        StringBuilder sb=convert(root,0);
//        System.out.println(sb.toString().length());
        PrintWriter pw = new PrintWriter(new FileOutputStream("server.txt"));
        pw.write(sb.toString());
        pw.close();

    }

    private static StringBuilder convert(Node node, int level) {
        //判断节点是元素，文本还是其它
        if (node instanceof Element)
        {
            return elementObject((Element)node,level);
        }
        else if (node instanceof CharacterData)
        {
            return characterString((CharacterData)node,level);
        }
        else
        {
            return pad(new StringBuilder(),level).append(jsonEscape(node.getClass().getName()));
        }

    }

    private static StringBuilder elementObject(Element node, int level) {
        StringBuilder result = new StringBuilder();
        pad(result,level).append("{\n");
        pad(result,level+1).append("\"name\": ");
        result.append(jsonEscape(node.getTagName()));
        NamedNodeMap attrs = node.getAttributes();
        if (attrs.getLength()>0)
        {
            pad(result.append(",\n"),level+1).append("\"attributes\": ");
            result.append(attributeObject(attrs));
        }
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength()>0)
        {
            pad(result.append(",\n"),level+1).append("\"children\":[\n");
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (i>0)
                {
                    result.append(",\n");
                }
                result.append(convert(childNodes.item(i),level+2));
            }
            result.append("\n");
            pad(result,level+1).append("]\n");
        }
        pad(result,level).append("}");
        return result;

    }

    private static StringBuilder attributeObject(NamedNodeMap attrs) {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < attrs.getLength(); i++) {
            if (i>0)
            {
                result.append(",");
            }
            result.append(jsonEscape(attrs.item(i).getNodeName()));
            result.append(":");
            result.append(jsonEscape(attrs.item(i).getNodeValue()));
        }
        result.append("}");
        return result;
    }

    private static StringBuilder characterString(CharacterData node, int level) {
        StringBuilder result = new StringBuilder();
        StringBuilder data = jsonEscape(node.getData());
        if (node instanceof Comment)
        {
            data.insert(1,"Comment: ");
        }
        pad(result,level).append(result);
        return result;
    }

    private static StringBuilder jsonEscape(String str) {

        StringBuilder result = new StringBuilder("\"");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String replace = replacements.get(ch);
            if (replace==null)
            {
                result.append(ch);
            }
            else
            {
                result.append(replace);
            }
        }
        result.append("\"");
        return result;
    }

    private static StringBuilder pad(StringBuilder builder, int level) {
        for (int i = 0; i < level; i++) {
            builder.append("    ");
        }
        return builder;
    }
}
