package net.chen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.util.Properties;


public class SettingMenu {
    private  JPanel panel1;
    private JTextField UserName_New;
    private JPasswordField passwordNew;
    private JPasswordField passwordVerify;
    private JButton confirmButton;

    public SettingMenu() {
        JFrame frame = new JFrame("SettingMenu");
        frame.setContentPane(panel1);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,800);
        frame.pack();
        frame.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewUserName = UserName_New.getText();
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader("src/main/resources/config.properties"));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Properties properties = new Properties();
                try {
                    properties.load(bufferedReader);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                String OldUserName = properties.getProperty("username");
                String NewPassword = new String(passwordNew.getPassword());
                String OldPassword = properties.getProperty("password");
                String NewPasswordVerify = new String(passwordVerify.getPassword());
                if(!Objects.equals(NewUserName, OldUserName)&&!Objects.equals(NewPassword, OldPassword)&&Objects.equals(NewPasswordVerify, NewPassword)&&!Objects.equals(NewPassword, "")&&!Objects.equals(NewUserName, "")){
                    JOptionPane.showMessageDialog(null, "密码修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    properties.clear();
                    properties.setProperty("username",NewUserName);
                    properties.setProperty("password",NewPassword);
                    try {
                        properties.store(new FileOutputStream("src/main/resources/config.properties"), "password changed");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "密码修改失败", "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }
    private int getListIndex(JList l){
        return l.getSelectedIndex();
    }
}
