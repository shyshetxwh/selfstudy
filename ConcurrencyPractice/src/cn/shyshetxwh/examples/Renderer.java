package cn.shyshetxwh.examples;

import java.util.List;
import java.util.concurrent.*;

import static cn.shyshetxwh.examples.LaunderThrowable.launderThrowable;

/**
 * FileName: Renderer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 19:14
 */
public abstract class Renderer {
    private final ExecutorService exec;

    public Renderer(ExecutorService exec) {
        this.exec = exec;
    }

    void renderPage(CharSequence source) {
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(exec);
        for (ImageInfo imageInfo : info) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        renderText(source);

        try {
            for (int i = 0; i < info.size(); i++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);
}
