package cn.shyshetxwh.chapter02;

/**
 * FileName: Adult
 * Author:   admin+shyshetxwh
 * Date:     2021/03/14 22:18
 */
public class Adult {
    public static void main(String[] args) throws Exception {
        try (Room room = new Room(7)) {
            System.out.println("Goodbye");
        }
    }
}
