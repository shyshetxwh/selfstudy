package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * FileName: Progress
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 20:02
 */
public class Progress extends JFrame {
    private JProgressBar pb=new JProgressBar();
    private ProgressMonitor pm=new ProgressMonitor(this,"Monitoring Progress","Test",0,100);
    private JSlider sb=new JSlider(JSlider.HORIZONTAL,0,100,60);

    public Progress() throws HeadlessException {
        setLayout(new GridLayout(2,1));
        add(pb);
        pm.setProgress(0);
        pm.setMillisToPopup(1000);

        sb.setValue(0);
        sb.setPaintTrack(true);
        sb.setMajorTickSpacing(20);
        sb.setMinorTickSpacing(5);
        sb.setBorder(new TitledBorder("Slide Me"));

        pb.setModel(sb.getModel());
        add(sb);

        sb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pm.setProgress(sb.getValue());
            }
        });
    }

    public static void main(String[] args) {
        SwingConsole.run(new Progress(),300,200);
    }
}
