package net.chen.CalcBuilder;

public class TrigonometricFunctionCalc {
    public TrigonometricFunctionCalc() {
    }
    public float Sine(int x) {
        return (float) Math.sin(x);
    }
    public float Cosine(int x) {
        return (float) Math.cos(x);
    }
    public float Tangent(int x) {
        return (float) Math.tan(x);
    }
    public float Cosecant(int x) {
        return (float) Math.sin(x) / (float) Math.cos(x);
    }
    public float Secant(int x) {
        return (float) Math.cos(x) / (float) Math.sin(x);
    }
    public float Cotangent(int x) {
        return (float) Math.cos(x) / (float) Math.sin(x);
    }
}
