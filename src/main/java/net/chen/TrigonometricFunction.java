package net.chen;

import net.chen.CalcBuilder.TrigonometricFunctionCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.logging.Logger;

public class TrigonometricFunction {
    private JButton CalcTrigonometricFunction;
    private  JPanel jrootP;
    private JList Results;
    private JTextField Asking;
    private JPanel CalcField;
    private JComboBox comboBox1;
    Logger logger = Logger.getLogger("TrigonometricFunction");
    TrigonometricFunctionCalc trigonometricFunctionCalc = new TrigonometricFunctionCalc();
    //JPanel panel = new JPanel();
    public TrigonometricFunction(){
        JFrame frame = new JFrame("TrigonometricFuction");
        frame.setContentPane(jrootP);
        CalcTrigonometricFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCalc();
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
    private void onCalc() {
        Double calculate = null;
        if (Objects.equals(comboBox1.getSelectedItem(), "Sine")) {
            try {
                calculate = (double) trigonometricFunctionCalc.Sine((int) Double.parseDouble(Asking.getText()));
            } catch (NumberFormatException e) {
                logger.warning("无效数字");
                throw new NumberFormatException("请输入数字");
            }
        } else if (Objects.equals(comboBox1.getSelectedItem(), "Cosine")) {
            try {
                calculate = (double) trigonometricFunctionCalc.Cosine((int) Double.parseDouble(Asking.getText()));
            } catch (NumberFormatException e) {
                logger.warning("无效数字");
                throw new NumberFormatException("请输入数字");
            }

        } else if (Objects.equals(comboBox1.getSelectedItem(), "Tan")) {
            try {
                calculate = (double) trigonometricFunctionCalc.Tangent((int) Double.parseDouble(Asking.getText()));

            } catch (NumberFormatException e) {
                logger.warning("无效数字");
                throw new NumberFormatException("请输入数字");
            }

        } else if (Objects.equals(comboBox1.getSelectedItem(),"Cotangent")) {
            try {
                calculate = (double) trigonometricFunctionCalc.Cotangent((int) Double.parseDouble(Asking.getText()));
            } catch (NumberFormatException e) {
                throw new  NumberFormatException("请输入数字");
            }
        }
        else if (Objects.equals(comboBox1.getSelectedItem(),"Cosecant")) {
            try {
                calculate = (double) trigonometricFunctionCalc.Cosecant((int) Double.parseDouble(Asking.getText()));
            } catch (NumberFormatException e) {
                throw new  NumberFormatException("请输入数字");
            }
        }
        else if (Objects.equals(comboBox1.getSelectedItem(),"Secant")) {
            try {
                calculate = (double) trigonometricFunctionCalc.Secant((int) Double.parseDouble(Asking.getText()));
            } catch (NumberFormatException e) {
                throw new  NumberFormatException("请输入数字");
            }
        }

        Object[] obj = new Object[1];
        if (calculate != null){
            obj[0] = calculate.toString();
            Results.setListData(obj);
        }else{
            logger.warning("无效数字");
            Warning("错误的输入");
        }
    }
    private void Warning(String message) throws HeadlessException {
        JOptionPane.showMessageDialog(null, message, "错误", JOptionPane.ERROR_MESSAGE);
    }

}
