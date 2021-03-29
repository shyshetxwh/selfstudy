package cn.shyshetxwh.XML.xpath;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XPathTest {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        Path path = Paths.get("config.xml");
        Document doc = Jsoup.parse(path.toFile(), "utf-8");

        //创建JXDocument
        JXDocument jx = new JXDocument(doc);


        //结合XPath语法查询
        //1.查询所有student标签
        List<JXNode> configs = jx.selN("//int");
//        for (JXNode config : configs) {
//            System.out.println(config);
//        }
        //2.查询student下的name标签
        List<JXNode> names = jx.selN("//entry/construct");
//        for (JXNode name : names) {
//            System.out.println(name);
//        }
        //3.查询student下带有id属性的name标签
        List<JXNode> names2 = jx.selN("//student/name[@id]");
//        for (JXNode name2 : names2) {
//            System.out.println(name2);
//        }
        //3.查询student下带有id属性且属性值为zhangsan的name标签
        List<JXNode> names3 = jx.selN("//student/name[@id='zhangsan']");
        for (JXNode name3 : names3) {
            System.out.println(name3);

        }

    }
}
