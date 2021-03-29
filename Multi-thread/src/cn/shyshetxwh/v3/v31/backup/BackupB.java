package cn.shyshetxwh.v3.v31.backup;

/**
 * FileName: BackupB
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 17:09
 */
public class BackupB extends Thread {
    private DBTools tools;

    public BackupB(DBTools tools) {
        this.tools = tools;
    }

    @Override
    public void run() {
        tools.backupB();
    }
}
