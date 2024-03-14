package com.tpo.first_task;

public class Sec {

    private double fact(double x) {
        if (x == 0) return 1;
        return fact(x - 1) * x;
    }

    public double calc(double x, int n){
        double PI2 = Math.PI * 2;

        if (x >= 0) {
            while (x > PI2) {
                x -= PI2;
            }
        } else if (x < 0){
            while (x < PI2) {
                x += PI2;
            }
        }

        double result = 0;

        double xx = x * x;
        double pow = 1;
        int sign = 1;

        for (int i = 0; i < n; i += 2){
            result += sign * pow / fact(i);    // (-1)^(n) * x^(2n) / (2n)!
            sign = -sign;
            pow *= xx;
        }

        return 1.0 / result;
    }
}