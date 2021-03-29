package cn.shyshetxwh.Locale.dateFormat;

import cn.shyshetxwh.Swing.GridBagLayout.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class DateTimeFormatterFrame extends JFrame {
    private Locale[] locales;
    private LocalDate currentDate;
    private LocalTime currentTime;
    private ZonedDateTime currentDateTime;
    private DateTimeFormatter currentDateFormat;
    private DateTimeFormatter currentTimeFormat;
    private DateTimeFormatter currentDateTimeFormat;
    private JComboBox<String> localeCombo=new JComboBox<>();
    private JButton dateParseButton=new JButton("Parse");
    private JButton timeParseButton=new JButton("Parse");
    private JButton dateTimeParseButton=new JButton("Parse");
    private JTextField dateText=new JTextField(30);
    private JTextField timeText=new JTextField(30);
    private JTextField dateTimeText=new JTextField(30);
    private EnumCombo<FormatStyle> dateStyleCombo=new EnumCombo<>(FormatStyle.class,"Short","Medium","Long","Full");
    private EnumCombo<FormatStyle> timeStyleCombo=new EnumCombo<>(FormatStyle.class,"Short","Medium");
    private EnumCombo<FormatStyle> dateTimeStyleCombo=new EnumCombo<>(FormatStyle.class,"Short","Medium","Long","Full");

    public DateTimeFormatterFrame()
    {
        setLayout(new GridBagLayout());
        add(new JLabel("Locale"),new GBC(0,0).setAnchor(GBC.EAST));
        add(localeCombo,new GBC(1,0,2,1).setAnchor(GBC.WEST));

        add(new JLabel("Date"),new GBC(0,1).setAnchor(GBC.EAST));
        add(dateStyleCombo,new GBC(1,1).setAnchor(GBC.WEST));
        add(dateText,new GBC(2,1,2,1).setFill(GBC.HORIZONTAL));
        add(dateParseButton,new GBC(4,1).setAnchor(GBC.WEST));

        add(new JLabel("Time"),new GBC(0,2).setAnchor(GBC.EAST));
        add(timeStyleCombo,new GBC(1,2).setAnchor(GBC.WEST));
        add(timeText,new GBC(2,2,2,1).setFill(GBC.HORIZONTAL));
        add(timeParseButton,new GBC(4,2).setAnchor(GBC.WEST));

        add(new JLabel("Date and time"),new GBC(0,3).setAnchor(GBC.EAST));
        add(dateTimeStyleCombo,new GBC(1,3).setAnchor(GBC.WEST));
        add(dateTimeText,new GBC(2,3,2,1).setFill(GBC.HORIZONTAL));
        add(dateTimeParseButton,new GBC(4,3).setAnchor(GBC.WEST));

        locales=(Locale[])Locale.getAvailableLocales().clone();
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        for (Locale locale : locales) {
            localeCombo.addItem(locale.getDisplayName());
        }
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentDate=LocalDate.now();
        currentTime=LocalTime.now();
        currentDateTime=ZonedDateTime.now();
        updateDisplay();

        ActionListener listener=event->updateDisplay();
        localeCombo.addActionListener(listener);
        dateStyleCombo.addActionListener(listener);
        timeStyleCombo.addActionListener(listener);
        dateTimeStyleCombo.addActionListener(listener);

        addAction(dateParseButton,()->{
            currentDate=LocalDate.parse(dateText.getText().trim(),currentDateFormat);
        });
        addAction(timeParseButton,()->{
            currentTime=LocalTime.parse(timeText.getText().trim(),currentTimeFormat);
        });
        addAction(dateTimeParseButton,()->{
            currentDateTime=ZonedDateTime.parse(dateTimeText.getText().trim(),currentDateTimeFormat);
        });

        pack();
    }

    private void addAction(JButton button, Runnable action) {
        button.addActionListener(event->{
            try {
                action.run();
                updateDisplay();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        });
    }

    private void updateDisplay() {
        Locale currentLocale = locales[localeCombo.getSelectedIndex()];
        FormatStyle dateStyle = dateStyleCombo.getValue();
        currentDateFormat = DateTimeFormatter.ofLocalizedDate(dateStyle).withLocale(currentLocale);
        dateText.setText(currentDateFormat.format(currentDate));

        FormatStyle timeStyle = timeStyleCombo.getValue();
        currentTimeFormat = DateTimeFormatter.ofLocalizedTime(timeStyle).withLocale(currentLocale);
        timeText.setText(currentTimeFormat.format(currentTime));

        FormatStyle dateTimeStyle = dateTimeStyleCombo.getValue();
        currentDateTimeFormat = DateTimeFormatter.ofLocalizedDateTime(dateTimeStyle).withLocale(currentLocale);
        dateTimeText.setText(currentDateTimeFormat.format(currentDateTime));
    }

}
