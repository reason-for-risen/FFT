package com.company;

import java.util.ArrayList;
import static java.lang.Math.*;


public class Main {

    public static void main(String[] args){
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        // num1
        ArrayList<Complex> num1 = new ArrayList<Complex>();
        for (int i = 1; i < args[0].length() + 1; i++) {
            num1.add(new Complex((int) args[0].charAt(args[0].length() - i) - (int) '0', 0));
        }

        //num2
        ArrayList<Complex> num2 = new ArrayList<Complex>();
        for (int i = 1; i < args[1].length() + 1; i++) {
            num2.add(new Complex((int) args[1].charAt(args[1].length() - i) - (int) '0', 0));
        }


        int n = 1;
        while (n < max(num1.size(), num2.size())) {
            n <<= 1;
        }
        n <<= 1;
        for (int i = num1.size(); i < n; i++) {
            num1.add(new Complex(0,0));
        }
        for (int i = num2.size(); i < n; i++) {
            num2.add(new Complex(0,0));
        }
/*
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(n);
*/
       
        int[] res = vecMult(num1, num2, n);
        int c = 1;
        while (res[res.length - c] == 0) { c++; }
        for (int i = c; i < res.length + 1; i++){
            System.out.print(res[res.length - i]);
        }
    }


    // Подсчет корней из 1
    private static ArrayList<Complex> wn(int n, boolean invert) {
        ArrayList<Complex> w = new ArrayList<Complex>(n); 
        for (int i = 0; i < n; i++) {
            double alpha = 2 * PI * i / n * (invert ? -1 : 1);
            w.add(new Complex(cos(alpha), sin(alpha)));
        }
        return w;
    }

    // 3. БПФ(обратноеБПФ)
    private static ArrayList<Complex> fft(ArrayList<Complex> a, boolean invert) {
        int n = a.size();
        if (n == 1) {
            return a;
        }

        ArrayList<Complex> a0 = new ArrayList<Complex>(n / 2 );
        ArrayList<Complex> a1 = new ArrayList<Complex>(n / 2 );

        for (int i = 0; i < n; i += 2) {
            a0.add(a.get(i));
            a1.add(a.get(i + 1));
        }

        a0 = fft (a0, invert);
        a1 = fft (a1, invert);
        ArrayList<Complex> w = wn(n, invert);

        for (int i = 0; i < n / 2; ++i) {
            a.set(i, a0.get(i).add(w.get(i).multiply(a1.get(i))));
            a.set(i + n/2, a0.get(i).subtract(w.get(i).multiply(a1.get(i))));
            if (invert) {
                a.set(i, a.get(i).divide(2));
                a.set(i + n / 2, a.get(i + n / 2).divide(2));
            }
        }
        return a;
    }

    // 4. Перемножение
    private static int[] vecMult(ArrayList<Complex> a, ArrayList<Complex> b, int n) {
        ArrayList<Complex> fa = fft(a, false);
        ArrayList<Complex> fb = fft(b, false);

        for (int  i = 0; i < n ; i++)
            fa.set(i, fa.get(i).multiply(fb.get(i)));
        fa = fft(fa, true);

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = (int) (fa.get(i).real() + 0.5);
        }

        // перенос разрядов
        int carry = 0;
        for (int i = 0; i < n; ++i) {
            res[i] += carry;
            carry = res[i] / 10;
            res[i] %= 10;
        }
        return res;
    }
}
