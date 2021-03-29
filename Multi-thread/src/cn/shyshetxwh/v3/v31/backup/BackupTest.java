package cn.shyshetxwh.v3.v31.backup;

/**
 * FileName: BackupTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 17:10
 */
public class BackupTest {
    public static void main(String[] args) {
        DBTools tools = new DBTools();
        for (int i = 0; i < 10; i++) {
            new BackupA(tools).start();
            new BackupB(tools).start();
        }
    }
}
