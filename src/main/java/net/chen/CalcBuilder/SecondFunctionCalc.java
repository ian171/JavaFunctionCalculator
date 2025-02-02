package net.chen.CalcBuilder;

import javax.naming.NameNotFoundException;
import javax.swing.*;

public class SecondFunctionCalc {
    double a;
    double b;
    double c;
    public SecondFunctionCalc(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double calculate(double x) throws NameNotFoundException {
        if (a != 0) {
            double Delta = b * b - 4 * a * c;
            if (Delta < 0){
                JOptionPane.showMessageDialog(null,"Not A Normal Function Expression.Delta is: "+Delta+" \n结果可能不准确","Error", JOptionPane.WARNING_MESSAGE);
            }
            return a * x * x + b * x + c;
        }
        throw new NameNotFoundException("a cannot be 0");
    }
    public double Delta(){
        return b * b - 4 * a * c;
    }
}
