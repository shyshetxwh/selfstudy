package cn.shyshetxwh.security.jaas;



import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;


public class JAASFrame extends JFrame {
    private JTextField username;
    private JPasswordField password;
    private JTextField propertyName;
    private JTextField propertyValue;

    public JAASFrame() {
        username = new JTextField(20);
        password = new JPasswordField(20);
        propertyName = new JTextField("user.home");
        propertyValue = new JTextField(20);
        propertyValue.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("username:"));
        panel.add(username);
        panel.add(new JLabel("password:"));
        panel.add(password);
        panel.add(propertyName);
        panel.add(propertyValue);
        add(panel, BorderLayout.CENTER);

        JButton getValueButton = new JButton("Get Value");
        getValueButton.addActionListener(event -> getValue());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(getValueButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void getValue() {
        try {
            LoginContext context = new LoginContext("Login1", new SimpleCallbackHandle(username.getText(), password.getPassword()));
            System.out.println("Trying to log in with " + username.getText() + " and " + new String(password.getPassword()));
            context.login();
            Subject subject = context.getSubject();
            propertyValue.setText(""+Subject.doAsPrivileged(subject,new SysPropAction(propertyName.getText()),null));
            context.logout();
        } catch (LoginException e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause != null) cause.printStackTrace();
        }
    }
}
