package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class ComplexSequence implements IComplexSequence{

    /**
     * The first term of the sequence.
     */
    private final IComplex first;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * The lambda expression processing the generic sequence.
     */
    private final BinaryOperator<IComplex> function;

    /**
     * Creates a new sequence.
     *
     * @param first The initial complex number.
     * @param nbTerms The maximum number of terms.
     * @param function The lambda expression processing the generic sequence.
     */
    public ComplexSequence(IComplex first, int nbTerms, BinaryOperator<IComplex> function) {
        this.first = first;
        this.nbTerms = nbTerms;
        this.function = function;
    }

    /**
     * Computes the next term of the sequence, based on the previous one.
     *
     * @param previous The previous value in the sequence.
     * @return The next value in the sequence.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return function.apply(previous, first);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexIterator(first, this, nbTerms);
    }

    /**
     * Creates a new Mandelbrot sequence.
     * This sequence is defined by the recurrence relation m_n(z) = m_{n-1}(z)^2 + z.
     *
     * @param first The initial complex number (z).
     * @param nbTerms The maximum number of terms to compute.
     * @return The created Mandelbrot sequence.
     */
    public static IComplexSequence createMandelbrot(Complex first, int nbTerms) {
        return createMandelbrotGeneric(first,nbTerms,2);
    }

    /**
     * Creates a new generalized Mandelbrot (Multibrot) sequence.
     * This sequence is defined by the recurrence relation m_n(z) = m_{n-1}(z)^p + z.
     *
     * @param first The initial complex number (z).
     * @param nbTerms The maximum number of terms to compute.
     * @param p The power applied to the previous term.
     * @return The created generalized Mandelbrot sequence.
     */
    public static IComplexSequence createMandelbrotGeneric(Complex first, int nbTerms, int p) {
        return new ComplexSequence(first, nbTerms, (previous, initial) -> previous.pow(p).add(initial));
    }

    /**
     * Creates a new Tricorn (or Mandelbar) sequence.
     * This sequence is defined by the recurrence relation t_n(z) = conjugate(t_{n-1}(z))^2 + c.
     *
     * @param first The initial complex number.
     * @param nbTerms The maximum number of terms to compute.
     * @param constant The complex constant (c) added at each iteration.
     * @return The created Tricorn sequence.
     */
    public static IComplexSequence createTricorn(Complex first, int nbTerms, IComplex constant) {
        return createTricornGeneric(first,nbTerms,constant,2);
    }

    /**
     * Creates a new generalized Tricorn sequence.
     * This sequence is defined by the recurrence relation t_n(z) = conjugate(t_{n-1}(z))^p + c.
     *
     * @param first The initial complex number.
     * @param nbTerms The maximum number of terms to compute.
     * @param constant The complex constant (c) added at each iteration.
     * @param p The power applied to the conjugated previous term.
     * @return The created generalized Tricorn sequence.
     */
    public static IComplexSequence createTricornGeneric(Complex first, int nbTerms, IComplex constant, int p) {
        return new ComplexSequence(first, nbTerms, (previous, initial) -> previous.conjugate().pow(p).add(constant));
    }
}