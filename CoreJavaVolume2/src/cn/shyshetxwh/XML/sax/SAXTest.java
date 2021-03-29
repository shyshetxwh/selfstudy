package cn.shyshetxwh.XML.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

public class SAXTest {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String url="https://www.w3.org/standards/";

        DefaultHandler handler=new DefaultHandler()
        {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (localName.equals("a")&&attributes!=null)
                {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        String name = attributes.getLocalName(i);
                        if (name.equalsIgnoreCase("href"))
                        {
                            System.out.println(attributes.getValue(i));
                        }
                    }
                }
            }
        };
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
        SAXParser saxParser = factory.newSAXParser();
        InputStream in = new URL(url).openStream();
        saxParser.parse(in,handler);
    }




}
