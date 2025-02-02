package net.chen;

import javax.swing.*;

public class Calc extends JFrame {
    private JPanel contentPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton 取消Button;
    private JButton 计算Button;
    private JTextField textField4;
    public static void Main() {

        Calc calc = new Calc();
        calc.pack();
        calc.setVisible(true);
    }
}
