package net.chen.CalcBuilder;

public class FirstFunctionCalc {
    double k;
    double b;
    public FirstFunctionCalc(double k, double b){
        this.k = k;
        this.b = b;
    }
    public double calculate(double x)throws ArithmeticException {
        if (k == 0) {
            throw new ArithmeticException("除数不能为0");
        }else {
            return k * x + b;
        }
    }
    public double calction(double x){
        return k * x + b;
    }
}
