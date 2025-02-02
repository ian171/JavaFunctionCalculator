package net.chen;

import net.chen.CalcBuilder.FirstFunctionCalc;
import net.chen.CalcBuilder.SecondFunctionCalc;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class NewCalc extends JDialog {
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton 取消Button;
    private JTextField textField4;
    private JButton 计算Button;
    private JList Results;
    private JButton 图像Button;
    private JComboBox comboBox1;
    private JLabel C;
    private JButton SettingButton;
    private JPanel CalcField;
    private JButton Info;
    private JScrollBar scrollBar1;
    private JSlider xControl;
    private JPanel SliderControl;
    private JSlider CSlider;
    private JLabel CSliderText;
    private JSlider aSlider;
    private JSlider bSlider;
    private JButton Delta;
    private JButton buttonOK;
    static NewCalc dialog = new NewCalc();
    int ClickCount = 0;
    boolean p = false;
    JFrame graphFrame = new JFrame("Linear Function Graph");
    private boolean isTrigonometricUIOpened = false;
    static String os = System.getProperty("os.name").toLowerCase();
    static URL url;
    int Value= 0;
    static {
        try {
            url = new URL("""
                    https://me.chenblog.space""");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void plotGraph(double a, double b, double c) {
        //JFrame graphFrame = new JFrame("Quadratic Function Graph");
        graphFrame.setSize(600, 400);
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int x0 = width / 2;
                int y0 = height / 2;

                g.drawLine(x0, 0, x0, height);
                g.drawLine(0, y0, width, y0);

                int step = 10;
                int prevX = x0 - width / 2 * step;
                int prevY = y0 - (int) (-a * Math.pow(prevX / step, 2) - b * (prevX / step) - c) * step;

                for (int x = -width / 2; x < width / 2; x += step) {
                    int y = (int) (-a * Math.pow(x, 2) - b * x - c);
                    int currentX = x0 + x * step;
                    int currentY = y0 - y * step;
                    g.drawLine(prevX, prevY, currentX, currentY);
                    prevX = currentX;
                    prevY = currentY;
                }
            }
        };
        graphFrame.add(graphPanel);
        graphFrame.setVisible(true);
        p = true;
    }
    private void plotLinearGraph(double k, double b) {
        graphFrame.setSize(600, 400);
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int x0 = width / 2;
                int y0 = height / 2;

                g.drawLine(x0, 0, x0, height);
                g.drawLine(0, y0, width, y0);

                int step = 10;
                int prevX = x0 - width / 2 * step;
                int prevY = y0 - (int) (k * (prevX / step) + b) * step;

                for (int x = -width / 2; x < width / 2; x += step) {
                    int y = (int) (k * x + b);
                    int currentX = x0 + x * step;
                    int currentY = y0 - y * step;
                    g.drawLine(prevX, prevY, currentX, currentY);
                    prevX = currentX;
                    prevY = currentY;
                }
            }
        };
        graphFrame.add(graphPanel);
        graphFrame.setVisible(true);
        p = true;
    }
    public NewCalc() {
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        setVisible(true);
        setContentPane(contentPane);
        setModal(true);
        //setFont(new Font("微软雅黑", Font.BOLD,10));
        getRootPane().setDefaultButton(buttonOK);
        计算Button.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                ClickCount++;
                try {
                    onCalc();
                } catch (NameNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        取消Button.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(comboBox1.getSelectedItem(), "一次函数")) {
                    textField2.setVisible(false);
                    C.setVisible(false);
                    dialog.setTitle("一次函数计算器");
                } else if (Objects.equals(comboBox1.getSelectedItem(), "二次函数")) {
                    textField2.setVisible(true);
                    C.setVisible(true);
                    dialog.setTitle("二次函数计算器");
                }
            }
        });
        图像Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboBox1.getSelectedItem().equals("一次函数")) {
                        if(!p){
                            plotLinearGraph(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()));
                        }else{
                            graphFrame.dispose();
                            plotLinearGraph(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()));
                        }
                    }else if(comboBox1.getSelectedItem().equals("二次函数")){
                        if (!p){
                            plotGraph(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()), Double.parseDouble(textField2.getText()));
                        }else {
                            graphFrame.dispose();
                            plotGraph(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()), Double.parseDouble(textField2.getText()));
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "请输入数字", "错误", JOptionPane.ERROR_MESSAGE);
                    //throw new NumberFormatException("无法获取数据，请检查是否写入了数据");
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedItem() == null){//判断下拉菜单是否为空
                    System.out.println("Loading...");
                }else {
                if (comboBox1.getSelectedItem().equals("三角函数")) {
                    if (!p) {
                        isTrigonometricUIOpened = true;
                        CalcField.setVisible(false);
                        SliderControl.setVisible(false);
                        dialog.setTitle("三角函数计算器");
                        new TrigonometricFunction();
                    }
                    } else if (comboBox1.getSelectedItem().equals("一次函数")) {
                        CSlider.setVisible(false);
                        CSliderText.setVisible(false);
                        CalcField.setVisible(true);
                        textField2.setVisible(false);
                        C.setVisible(false);

                    } else if (comboBox1.getSelectedItem().equals("二次函数")) {
                        CSlider.setVisible(false);
                        CSliderText.setVisible(false);
                        CalcField.setVisible(true);
                }
                }
            }
        });
        SettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingMenu();
            }
        });
        Info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOpenWeb();
            }
        });
        onInitialization();//初始化
        xControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Value = xControl.getValue();
                textField4.setText(String.valueOf(Value));
            }
        });
        aSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField3.setText(String.valueOf(aSlider.getValue()));
            }
        });
        bSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField1.setText(String.valueOf(bSlider.getValue()));
            }
        });
        CSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField2.setText(String.valueOf(CSlider.getValue()));
            }
        });
        textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aSlider.setValue(Integer.parseInt(textField3.getText()));
            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CSlider.setValue(Integer.parseInt(textField2.getText()));
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bSlider.setValue(Integer.parseInt(textField1.getText()));
            }
        });
        Delta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecondFunctionCalc sf = new SecondFunctionCalc(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()), Double.parseDouble(textField2.getText()));
                JOptionPane.showMessageDialog(null, "△=" + sf.Delta(), "△", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
    public void onOpenWeb() throws RuntimeException {
        try {
            Desktop.getDesktop().browse(url.toURI());
        } catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void main() {
        comboBox1.setSelectedItem(null);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void onCalc() throws NameNotFoundException {
        Double calculate = null;
        if(Objects.equals(comboBox1.getSelectedItem(), "一次函数")){
            try{
                FirstFunctionCalc fs = new FirstFunctionCalc(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()));
                calculate = fs.calculate(Double.parseDouble(textField4.getText()));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("请输入数字");
            }
        }else if(Objects.equals(comboBox1.getSelectedItem(), "二次函数")){
            try {
                SecondFunctionCalc sf = new SecondFunctionCalc(Double.parseDouble(textField3.getText()), Double.parseDouble(textField1.getText()), Double.parseDouble(textField2.getText()));
                calculate = sf.calculate(Double.parseDouble(textField4.getText()));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("请输入数字");
            }
        }
        Object[] obj = new Object[1];
        if (calculate != null){
            obj[0] = calculate.toString();
            Results.setListData(obj);
        }else {
            JOptionPane.showMessageDialog(null, "错误的输入", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void onCancel(){
        dispose();
        System.exit(1);
    }
    @SuppressWarnings("value")
    private void onInitialization(){
        textField2.setText(String.valueOf(0));
        textField1.setText(String.valueOf(0));
        textField3.setText(String.valueOf(0));
        textField4.setText(String.valueOf(0));
        xControl.setMaximum(1000);
        xControl.setMinimum(-10);
        xControl.setValue(0);
        aSlider.setMaximum(1000);
        aSlider.setMinimum(-10);
        aSlider.setValue(0);
        bSlider.setMaximum(1000);
        bSlider.setMinimum(-10);
        bSlider.setValue(0);
        CSlider.setMaximum(1000);
        CSlider.setMinimum(-10);
        CSlider.setValue(0);
    }
}