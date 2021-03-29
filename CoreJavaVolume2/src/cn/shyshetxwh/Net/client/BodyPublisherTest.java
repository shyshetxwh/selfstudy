package cn.shyshetxwh.Net.client;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

public class BodyPublisherTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String filename="fileupload.properties";
//        String filename="post.properties";
//        String filename="json.properties";
        InputStream in = BodyPublisherTest.class.getResourceAsStream(filename);
        URI uri = BodyPublisherTest.class.getResource(filename).toURI();
        Path path = Paths.get(uri);


        Properties props = new Properties();
        props.load(in);

        String urlString=""+props.remove("url");
        String contentType=""+props.remove("Content-Type");
        if (contentType.equals("multipart/form-data"))
        {
            Random generator = new Random();
            String boundary = new BigInteger(256, generator).toString();
            contentType+=";boundary="+boundary;
            props.replaceAll((k,v)->v.toString().startsWith("file://")?
                                path.getParent().resolve(Paths.get(v.toString().substring(7))):v);
        }

        String result="";
        if (contentType.equals("application/x-www-form-urlencoded"))
        {
           result=MoreBodyPublishers.ofFormData(props);
        }
        else if (contentType.startsWith("multipart/form-data"))
        {
            String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);
            ArrayList<byte[]> list = MoreBodyPublishers.ofMimeMultipartData(props, boundary);
            StringBuilder sb=new StringBuilder();
            for (byte[] bytes : list) {
                String s = new String(bytes);
                sb.append(s);
//                sb.append("&&&&");
            }
            result=sb.toString();
        }
        else
        {
            contentType="application/json";
            result=MoreBodyPublishers.ofSimpleJSON(props);

        }

        System.out.println(result);



    }
}
