package cn.shyshetxwh.Net.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoreBodyPublishers {
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
    public static String ofFormData(Map<Object,Object> data) throws UnsupportedEncodingException {
        boolean first=true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (first)
            {
                first=true;
            }
            else
            {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(),"UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(),"UTF-8"));
        }
        return builder.toString();
    }

    public static String ofSimpleJSON(Map<Object,Object> data)
    {
        boolean first=true;
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (first)
            {
                first=true;
            }
            else
            {
                builder.append(",");
            }
            builder.append(jsonEscape(entry.getKey().toString())).append(": ").append(jsonEscape(entry.getValue().toString()));
        }
        builder.append("}");
        return builder.toString();
    }

    public static ArrayList<byte[]> ofMimeMultipartData(Map<Object,Object> data,String boundary) throws IOException {
        ArrayList<byte[]> result=new ArrayList<>();
        byte[] separator=bytes("--"+boundary+"\nContent-Disposition: form-data; name=");
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            result.add(separator);
            if (entry.getValue() instanceof Path)
            {
                Path path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                result.add(bytes("\""+entry.getKey()+"\"; filename=\""+path.getFileName()
                +"\"\nContent-Type: "+mimeType+"\n\n"));
            }
            else
            {
                result.add(bytes("\""+entry.getKey()+"\"\n\n"+entry.getValue()+"\n"));
            }
        }
        result.add(bytes("--"+boundary+"--"));
        return result;
    }

    private static byte[] bytes(String s)
    {
        return s.getBytes(StandardCharsets.UTF_8);
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
}
