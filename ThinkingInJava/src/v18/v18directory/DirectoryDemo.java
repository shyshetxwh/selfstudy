package v18.v18directory;

import cn.shyshetxwh.util.Directory;
import cn.shyshetxwh.util.PPrint;

import java.io.File;

public class DirectoryDemo {
    public static void main(String[] args) {
        //PPrint.pprint(Directory.walk("./ThinkingInJava").dirs);

        for (File file : Directory.local(".", "R.*")) {
            System.out.println(file);
        }

        /*for (File file : Directory.walk(".", "T.*\\.java")) {
            System.out.println(file);
        }*/

        for (File file : Directory.walk(".", ".*[zZ].*\\.class")) {
            System.out.println(file);
        }
    }
}
