package net.chen.CalcBuilder;

import net.chen.Login;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Scanner s1 = new Scanner(System.in);
    private static JFrame windows = new JFrame("Calculator");

    public  void main(String[] args) throws FileNotFoundException {

    }
    public static void F2(){
        try {
            System.out.println("请输入二次函数的系数a,b,c");
            double a = s1.nextDouble();
            double b = s1.nextDouble();
            double c = s1.nextDouble();
            System.out.println("请输入x的值");
            double x = s1.nextDouble();
            SecondFunctionCalc secondFunctionCalc = new SecondFunctionCalc(a,b,c);
            System.out.println("Result："+secondFunctionCalc.calculate(x));
        } catch (ArithmeticException | NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void F1(){
        System.out.println("请输入一次函数的系数k,b");
        double k = s1.nextDouble();
        double b = s1.nextDouble();
        System.out.println("请输入x的值");
        double x = s1.nextDouble();
        FirstFunctionCalc firstFunctionCalc = new FirstFunctionCalc(k,b);
        System.out.println("Result?"+firstFunctionCalc.calculate(x));
    }
    public static void Panel() throws FileNotFoundException {
        Login login = new Login();
        login.setVisible(true);
    }
//    private void plotGraph(double a, double b, double c) {
//        JFrame graphFrame = new JFrame("Quadratic Function Graph");
//        graphFrame.setSize(600, 400);
//        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JPanel graphPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                int width = getWidth();
//                int height = getHeight();
//                int x0 = width / 2;
//                int y0 = height / 2;
//
//                g.drawLine(x0, 0, x0, height);
//                g.drawLine(0, y0, width, y0);
//
//                int step = 10;
//                for (int x = -width / 2; x < width / 2; x += step) {
//                    int y = (int) (-a * Math.pow(x, 2) - b * x - c);
//                    g.fillOval(x0 + x * step, y0 - y * step, 4, 4);
//                }
//            }
//        };}
}