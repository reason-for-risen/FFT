package com.company;

public class Complex {
    // These are the instance variables.  Each ComplexNumber object holds
    // two double values, known as x and y.  They are private, so they are
    // not accessible from outside this class.  Instead, they are available
    // through the real() and imaginary() methods below.
    private double x, y;

    /** This is the constructor.  It initializes the x and y variables */
    public Complex(double real, double imaginary) {
        this.x = real;
        this.y = imaginary;
    }

    /**
     * An accessor method.  Returns the real part of the complex number.
     * Note that there is no setReal() method to set the real part.  This means
     * that the ComplexNumber class is "immutable".
     **/
    public double real() { return x; }

    /** An accessor method.  Returns the imaginary part of the complex number */
    public double imaginary() { return y; }

    /** Compute the magnitude of a complex number */
    public double magnitude() { return Math.sqrt(x*x + y*y); }

    /**
     * This method converts a ComplexNumber to a string.  This is a method of
     * Object that we override so that complex numbers can be meaningfully
     * converted to strings, and so they can conveniently be printed out with
     * System.out.println() and related methods
     **/
    public String toString() { return "{" + x + "," + y + "}"; }

    /**
     * This is a static class method.  It takes two complex numbers, adds them,
     * and returns the result as a third number.  Because it is static, there is
     * no "current instance" or "this" object.  Use it like this:
     * ComplexNumber c = ComplexNumber.add(a, b);
     **/
    public static Complex add(Complex a, Complex b) {
        return new Complex(a.x + b.x, a.y + b.y);
    }

    /**
     * This is a non-static instance method by the same name.  It adds the
     * specified complex number to the current complex number.  Use it like this:
     * ComplexNumber c = a.add(b);
     **/
    public Complex add(Complex a) {
        return new Complex(this.x + a.x, this.y+a.y);
    }

    /** A static class method to multiply complex numbers */
    public static Complex multiply(Complex a, Complex b) {
        return new Complex(a.x*b.x - a.y*b.y, a.x*b.y + a.y*b.x);
    }

    /** An instance method to multiply complex numbers */
    public Complex multiply(Complex a) {
        return new Complex(x*a.x - y*a.y, x*a.y + y*a.x);
    }

    public Complex divide(double a) {
        return new Complex(x / a, y / a);
    }

    public Complex subtract(Complex a) {
        return new Complex(x - a.x, y - a.y);
    }
}