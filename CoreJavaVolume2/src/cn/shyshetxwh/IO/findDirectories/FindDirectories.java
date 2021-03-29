package cn.shyshetxwh.IO.findDirectories;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FindDirectories {
    public static void main(String[] args) throws IOException {
//        Path dir = Paths.get(args.length == 0 ? System.getProperty("user.home") : args[0]);
        Path dir=Paths.get("F:\\0114");
        System.out.println(dir);
        Files.walkFileTree(dir,new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }
        });
    }
}
