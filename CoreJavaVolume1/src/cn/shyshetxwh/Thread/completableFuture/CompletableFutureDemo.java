package cn.shyshetxwh.Thread.completableFuture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN=
            Pattern.compile("[<]\\s*[iI][mM][gG]\\s*[^>][sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    private ExecutorService executor= Executors.newCachedThreadPool();
    private URL urlToProcess;

    public CompletableFuture<String> readPage(URL url)
    {
        return CompletableFuture.supplyAsync(()->{
            try {
                InputStream is = url.openStream();
                int len=0;
                byte[] bytes=new byte[1024];
                StringBuilder sb=new StringBuilder();
                while((len=is.read(bytes))!=-1)
                {
                    String s = new String(bytes, StandardCharsets.UTF_8);
                    sb.append(s);
                }
                System.out.println("Read page from "+url);
                return sb.toString();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

        },executor);
    }

    public List<URL> getImageURLs(String webpage)
    {
        try {
            List<URL> result=new ArrayList<>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while(matcher.find())
            {
                URL url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs"+result);
            return result;
        } catch (MalformedURLException e) {
            throw new UncheckedIOException(e);
        }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls)
    {
        return CompletableFuture.supplyAsync(()->{
            try {
                ArrayList<BufferedImage> result = new ArrayList<>();
                for (URL url : urls) {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded "+url);
                }
                return result;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        },executor);
    }

    public void saveImages(List<BufferedImage> images)
    {
        System.out.println("Saving "+images.size()+" images");
        try {
            for (int i = 0; i < images.size(); i++) {
                String fileName="./tmp/image"+(i+1)+".png";
                File file = new File(fileName);
                if(!file.exists())
                {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                ImageIO.write(images.get(i),"PNG",file);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        executor.shutdown();
    }

    public void run(URL url)
    {
        urlToProcess=url;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage,executor)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);
    }

}
