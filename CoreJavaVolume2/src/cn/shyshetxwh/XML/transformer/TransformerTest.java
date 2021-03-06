package cn.shyshetxwh.XML.transformer;

import org.xml.sax.InputSource;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TransformerTest {
    public static void main(String[] args) throws IOException, TransformerException {
        Path path = Paths.get("makehtml.xsl");
        try(InputStream styleIn= Files.newInputStream(path)){
            StreamSource styleSource = new StreamSource(styleIn);
            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.setOutputProperty(OutputKeys.INDENT,"yes");
            t.setOutputProperty(OutputKeys.METHOD,"xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");

            try(InputStream docIn=Files.newInputStream(Paths.get("employee_trans.dat"))){
                t.transform(new SAXSource(new EmployeeReader(),new InputSource(docIn)),new StreamResult(Paths.get("employee.html").toFile()));
            }

        }
    }
}
