package cn.shyshetxwh.Locale.retire;

import cn.shyshetxwh.Swing.GridBagLayout.GBC;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RetireFrame extends JFrame{
    private JTextField savingsField = new JTextField(10);
    private JTextField contribField = new JTextField(10);
    private JTextField incomeField = new JTextField(10);
    private JTextField currentAgeField = new JTextField(4);
    private JTextField retireAgeField = new JTextField(4);
    private JTextField deathAgeField = new JTextField(4);
    private JTextField inflationPercentField = new JTextField(6);
    private JTextField investPercentField = new JTextField(6);
    private JTextArea retireText = new JTextArea(10, 25);
    private RetireComponent retireCanvas=new RetireComponent();
    private JButton computeButton = new JButton();
    private JLabel languageLabel = new JLabel();
    private JLabel savingsLabel = new JLabel();
    private JLabel contribLabel = new JLabel();
    private JLabel incomeLabel = new JLabel();
    private JLabel currentAgeLabel = new JLabel();
    private JLabel retireAgeLabel = new JLabel();
    private JLabel deathAgeLabel = new JLabel();
    private JLabel inflationPercentLabel = new JLabel();
    private JLabel investPercentLabel = new JLabel();
    private RetireInfo info = new RetireInfo();
    private Locale[] locales = { Locale.US, Locale.CHINA, Locale.GERMANY };
    private Locale currentLocale;
    private JComboBox<Locale> localeCombo = new LocaleCombo(locales);
    private ResourceBundle res;
    private ResourceBundle resStrings;
    private NumberFormat currencyFmt;
    private NumberFormat numberFmt;
    private NumberFormat percentFmt;

    public RetireFrame()
    {
        setLayout(new GridBagLayout());
        add(languageLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        add(savingsLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        add(contribLabel, new GBC(2, 1).setAnchor(GBC.EAST));
        add(incomeLabel, new GBC(4, 1).setAnchor(GBC.EAST));
        add(currentAgeLabel, new GBC(0, 2).setAnchor(GBC.EAST));
        add(retireAgeLabel, new GBC(2, 2).setAnchor(GBC.EAST));
        add(deathAgeLabel, new GBC(4, 2).setAnchor(GBC.EAST));
        add(inflationPercentLabel, new GBC(0, 3).setAnchor(GBC.EAST));
        add(investPercentLabel, new GBC(2, 3).setAnchor(GBC.EAST));
        add(localeCombo, new GBC(1, 0, 3, 1));
        add(savingsField, new GBC(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(contribField, new GBC(3, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(incomeField, new GBC(5, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(currentAgeField, new GBC(1, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(retireAgeField, new GBC(3, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(deathAgeField, new GBC(5, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(inflationPercentField, new GBC(1, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(investPercentField, new GBC(3, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(retireCanvas, new GBC(0, 4, 4, 1).setWeight(100, 100).setFill(GBC.BOTH));
        add(new JScrollPane(retireText),
                new GBC(4, 4, 2, 1).setWeight(0, 100).setFill(GBC.BOTH));

        computeButton.setName("computeButton");
        computeButton.addActionListener(event->{
            getInfo();
            updateData();
            updateGraph();
        });
        add(computeButton, new GBC(5, 3));

        retireText.setEditable(false);
        retireText.setFont(new Font("Monospaced",Font.PLAIN,10));

        info.setSavings(0);
        info.setContrib(9000);
        info.setIncome(60000);
        info.setCurrentAge(35);
        info.setRetireAge(65);
        info.setDeathAge(85);
        info.setInvestPercent(0.1);
        info.setInflationPercent(0.05);

        int localeIndex=0;
        for (int i = 0; i < locales.length; i++) {
            if (getLocale().equals(locales[i]))localeIndex=i;
        }
        setCurrentLocale(locales[localeIndex]);

        localeCombo.addActionListener(event->{
            setCurrentLocale((Locale) localeCombo.getSelectedItem());
            validate();
        });

        pack();
    }

    private void setCurrentLocale(Locale locale) {
        currentLocale=locale;
        localeCombo.setLocale(currentLocale);
        localeCombo.setSelectedItem(currentLocale);

        res=ResourceBundle.getBundle("cn.shyshetxwh.Locale.retire.RetireResources",currentLocale);
        resStrings=ResourceBundle.getBundle("cn.shyshetxwh.Locale.retire.RetireStrings",currentLocale);
        currencyFmt=NumberFormat.getCurrencyInstance(currentLocale);
        numberFmt=NumberFormat.getNumberInstance(currentLocale);
        percentFmt=NumberFormat.getPercentInstance(currentLocale);

        updateDisplay();
        updateInfo();
        updateData();
        updateGraph();
    }

    private void updateInfo() {
        savingsField.setText(currencyFmt.format(info.getSavings()));
        contribField.setText(currencyFmt.format(info.getContrib()));
        incomeField.setText(currencyFmt.format(info.getIncome()));
        currentAgeField.setText(numberFmt.format(info.getCurrentAge()));
        retireAgeField.setText(numberFmt.format(info.getRetireAge()));
        deathAgeField.setText(numberFmt.format(info.getDeathAge()));
        investPercentField.setText(percentFmt.format(info.getInvestPercent()));
        inflationPercentField.setText(percentFmt.format(info.getInflationPercent()));
    }

    private void updateDisplay() {
        try {
            languageLabel.setText(new String(resStrings.getString("language").getBytes("ISO-8859-1"),"utf-8"));
            savingsLabel.setText(new String(resStrings.getString("savings").getBytes("ISO-8859-1"),"utf-8"));
            contribLabel.setText(new String(resStrings.getString("contrib").getBytes("ISO-8859-1"),"utf-8"));
            incomeLabel.setText(new String(resStrings.getString("income").getBytes("ISO-8859-1"),"utf-8"));
            currentAgeLabel.setText(new String(resStrings.getString("currentAge").getBytes("ISO-8859-1"),"utf-8"));
            retireAgeLabel.setText(new String(resStrings.getString("retireAge").getBytes("ISO-8859-1"),"utf-8"));
            deathAgeLabel.setText(new String(resStrings.getString("deathAge").getBytes("ISO-8859-1"),"utf-8"));
            inflationPercentLabel.setText(new String(resStrings.getString("inflationPercent").getBytes("ISO-8859-1"),"utf-8"));
            investPercentLabel.setText(new String(resStrings.getString("investPercent").getBytes("ISO-8859-1"),"utf-8"));
            computeButton.setText(new String(resStrings.getString("computeButton").getBytes("ISO-8859-1"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void updateGraph() {
        retireCanvas.setColorPre((Color) res.getObject("colorPre"));
        retireCanvas.setColorGain((Color) res.getObject("colorGain"));
        retireCanvas.setColorLoss((Color) res.getObject("colorLoss"));
        retireCanvas.setInfo(info);
        repaint();
    }

    private void updateData() {
        MessageFormat retireMsg = null;
        try {
            retireText.setText("");
            retireMsg = new MessageFormat("");
            retireMsg.setLocale(currentLocale);
            retireMsg.applyPattern(new String(resStrings.getString("retire").getBytes("ISO-8859-1"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (int i=info.getCurrentAge();i<=info.getDeathAge();i++)
        {
            Object[] args={i,info.getBalance(i)};
            retireText.append(retireMsg.format(args)+"\n");
        }
    }

    private void getInfo() {
        try {
            info.setSavings(currencyFmt.parse(savingsField.getText()).doubleValue());
            info.setContrib(currencyFmt.parse(contribField.getText()).doubleValue());
            info.setIncome(currencyFmt.parse(incomeField.getText()).doubleValue());
            info.setCurrentAge(numberFmt.parse(currentAgeField.getText()).intValue());
            info.setRetireAge(numberFmt.parse(retireAgeField.getText()).intValue());
            info.setDeathAge(numberFmt.parse(deathAgeField.getText()).intValue());
            info.setInvestPercent(percentFmt.parse(investPercentField.getText()).doubleValue());
            info.setInflationPercent(
                    percentFmt.parse(inflationPercentField.getText()).doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
