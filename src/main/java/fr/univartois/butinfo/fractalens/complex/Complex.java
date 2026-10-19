package fr.univartois.butinfo.fractalens.complex;

import java.util.Objects;

/**
 * The {@code Complex} class provides a concrete implementation of the {@link IComplex}
 * interface, representing a complex number with a real part and an imaginary part.
 *
 * @author Lucas Vanstraceele
 * @version 1.0
 */
public class Complex implements IComplex {

    /** The real part of this complex number. */
    private final double realPart;

    /** The imaginary part of this complex number. */
    private final double imaginaryPart;

    /**
     * Creates a new complex number with the specified real and imaginary parts.
     *
     * @param realPart The real part.
     * @param imaginaryPart The imaginary part.
     */
    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRealPart() {
        return realPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getImaginaryPart() {
        return imaginaryPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double abs() {
        return Math.sqrt(Math.pow(realPart, 2) + Math.pow(imaginaryPart, 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex negate() {
        return new Complex(-realPart, -imaginaryPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex conjugate() {
        return new Complex(realPart, -imaginaryPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex add(IComplex other) {
        return new Complex(realPart + other.getRealPart(), imaginaryPart + other.getImaginaryPart());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex subtract(IComplex other) {
        return new Complex(realPart - other.getRealPart(), imaginaryPart - other.getImaginaryPart());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex multiply(double value) {
        return new Complex(realPart * value, imaginaryPart * value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex multiply(IComplex other) {
        double r = (this.realPart * other.getRealPart()) - (this.imaginaryPart * other.getImaginaryPart());
        double i = (this.realPart * other.getImaginaryPart()) + (other.getRealPart() * this.imaginaryPart);
        return new Complex(r, i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex divide(IComplex other) {
        double denom = Math.pow(other.getRealPart(), 2) + Math.pow(other.getImaginaryPart(), 2);
        if (denom == 0.0) {
            throw new ArithmeticException("Division by zero.");
        }
        double r = (this.realPart * other.getRealPart() + this.imaginaryPart * other.getImaginaryPart()) / denom;
        double i = (other.getRealPart() * this.imaginaryPart - this.realPart * other.getImaginaryPart()) / denom;
        return new Complex(r, i);
    }

    /**
     * Indicates whether some other object is "equal to" this complex number.
     *
     * @option obj The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(realPart, imaginaryPart);
    }

    /**
     * Returns a string representation of the complex number in the format "real + imaginaryi".
     *
     * @return A string representation of this complex number.
     */
    @Override
    public String toString() {
        return realPart + " + " + imaginaryPart + "i";
    }
}
