package cn.shyshetxwh.v3.v31.backup;

/**
 * FileName: BackupA
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 17:08
 */
public class BackupA extends Thread {
    private DBTools tools;

    public BackupA(DBTools tools) {
        this.tools = tools;
    }

    @Override
    public void run() {
        tools.backupA();
    }
}
