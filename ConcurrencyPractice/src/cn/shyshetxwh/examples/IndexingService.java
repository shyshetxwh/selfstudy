package cn.shyshetxwh.examples;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FileName: IndexingService
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 22:16
 */
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(FileFilter fileFilter, File root) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || fileFilter.accept(f);
            }
        };
    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawler(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void crawler(File root) throws InterruptedException {
            File[] files = root.listFiles(fileFilter);
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory())
                        crawler(file);
                    else if (!alreadyIndexed(file)) {
                        System.out.println(file);
                        queue.put(file);
                    }
                }
            }
        }
    }

    class IndexerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    File file = queue.take();
                    //System.out.println(file.getName());
                    if (file == POISON)
                        break;
                    else
                        System.out.println(file.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

    public static void main(String[] args) throws InterruptedException {
        IndexingService service = new IndexingService(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".java");
            }
        }, new File("."));
        service.start();

        TimeUnit.SECONDS.sleep(3);
        service.awaitTermination();
    }
}
