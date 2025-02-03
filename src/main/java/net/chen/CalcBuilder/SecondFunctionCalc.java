package net.chen.CalcBuilder;

import javax.naming.NameNotFoundException;
import javax.swing.*;

public class SecondFunctionCalc {
    static double a;
    static double b;
    static double c;
    double r;
    double r2;
    public static double RealPart;
    public static double imagPart;
    static boolean hasUnRoot = false;
    public SecondFunctionCalc(double a, double b, double c) {
        SecondFunctionCalc.a = a;
        SecondFunctionCalc.b = b;
        SecondFunctionCalc.c = c;
    }

    public  void Acalculate() {
        if(Delta() > 0){
            r = (-b + Math.sqrt(Delta()))/(2 * a );
            r2 = (-b - Math.sqrt(Delta()))/(2 * a );
        }
    }
    public void Bcalculate() {
        if (Delta() < 0 ){
            hasUnRoot = true;
            RealPart = -b / (2 * a);
            imagPart = Math.sqrt(Math.abs(Delta())) / (2 * a);
        }
    }
    public double calculate(int result){
        if (hasUnRoot) {
            return result == 1 ? RealPart + imagPart : RealPart - imagPart;
        } else {
            return result == 1 ? r : r2;
        }
    }
    public static double Delta(){
        return b * b - 4 * a * c;
    }
    public static boolean isHasUnRoot() {
        return hasUnRoot;
    }
    public static void setHasUnRoot(boolean hasUnRoot) {
        SecondFunctionCalc.hasUnRoot = hasUnRoot;
    }
    public double calction(double x){
        return a * x * x + b * x + c;
    }
    public void close(){

    }
}
