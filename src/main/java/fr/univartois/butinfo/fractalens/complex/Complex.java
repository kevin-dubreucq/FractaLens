package fr.univartois.butinfo.fractalens.complex;

import java.util.Objects;

public class Complex implements IComplex{

    private final double realPart;
    private final double imaginaryPart;

    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public double getRealPart() {
        return realPart;
    }

    @Override
    public double getImaginaryPart() {
        return imaginaryPart;
    }

    @Override
    public double abs(){
        return Math.sqrt(Math.pow(realPart, 2) + Math.pow(imaginaryPart, 2));
    }

    @Override
    public IComplex negate() {
        return new Complex(-realPart, -imaginaryPart);
    }

    @Override
    public IComplex conjugate() {
        return new Complex(realPart, -imaginaryPart);
    }

    @Override
    public IComplex add(IComplex other) {
        return new Complex(realPart + other.getRealPart(), imaginaryPart + other.getImaginaryPart());
    }

    @Override
    public IComplex subtract(IComplex other) {
        return new Complex(realPart - other.getRealPart(), imaginaryPart - other.getImaginaryPart());
    }

    @Override
    public IComplex multiply(double value) {
        return new Complex(realPart * value, imaginaryPart * value);
    }

    @Override
    public IComplex multiply(IComplex other) {
        double r = (this.realPart * other.getRealPart()) - (this.imaginaryPart * other.getImaginaryPart());
        double i = (this.realPart * other.getImaginaryPart()) + (other.getRealPart() * this.imaginaryPart);
        return new Complex(r, i);
    }

    @Override
    public IComplex divide(IComplex other) {
        double denom = Math.pow(other.getRealPart(), 2) + Math.pow(other.getImaginaryPart(), 2);
        double r = (this.realPart * other.getRealPart() + this.imaginaryPart * other.getImaginaryPart()) / denom;
        double i = (other.getRealPart() * this.imaginaryPart - this.realPart * other.getImaginaryPart()) / denom;
        return new Complex(r, i);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IComplex)) {
            return false;
        }
        IComplex other = (IComplex) obj;
        return this.realPart == other.getRealPart() && this.imaginaryPart == other.getImaginaryPart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(realPart, imaginaryPart);
    }

    @Override
    public String toString() {
        return realPart + " + " + imaginaryPart + "i";
    }
}
