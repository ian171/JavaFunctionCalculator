package net.chen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField UserName;
    private JPasswordField passwordField1;
    Properties properties = new Properties();
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/resources/config.properties")));

    public Login() throws FileNotFoundException {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // 点击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
//        contentPane.registerKeyboardAction(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() throws IOException {
        // 在此处添加您的代码
        String username = UserName.getText();
        int password;
        try {
            password = Integer.parseInt(passwordField1.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("请输入纯数字");
        }

        //properties.put("username", "root");
        //properties.put("password", "123456");
        properties.load(bufferedReader);
        String Pusername = properties.getProperty("username");
        String Ppassword = properties.getProperty("password");
        if(Objects.equals(username, Pusername)&& Objects.hashCode(password) == Objects.hashCode(Integer.parseInt(Ppassword))) {
            //this.setEnabled(false);
            this.dispose();
            this.setVisible(false);
            new NewCalc().main();
        }else {
            JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        //dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        Login dialog = new Login();
        dialog.setTitle("登录");
        dialog.pack();
        dialog.setVisible(true);

        //System.exit(0);
    }
}
