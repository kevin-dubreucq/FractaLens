
package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

/**
 * Represents a generalized Julia sequence (Multi-Julia) of complex numbers,
 * where the terms are computed using a higher power than 2 ($z^p + c$).
 *
 * @author Lucas Vanstraceele
 * @version 1.0
 */
public class MultiJuliaSequence implements IComplexSequence {

    /**
     * The first term of the sequence.
     */
    private final IComplex first;

    /**
     * The constant complex number.
     */
    private final IComplex constant;

    /**
     * The power applied to the complex number at each iteration.
     */
    private final int power;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * Creates a new Multi-Julia sequence.
     *
     * @param first The initial complex number.
     * @param constant The constant complex number.
     * @param power The power to raise the complex number to.
     * @param nbTerms The maximum number of terms.
     */
    public MultiJuliaSequence(IComplex first, IComplex constant, int power, int nbTerms) {
        this.first = first;
        this.constant = constant;
        this.power = power;
        this.nbTerms = nbTerms;
    }

    /**
     * Computes the next term of the sequence from the previous one using the formula $z^p + c$.
     *
     * @param previous The previous complex number.
     * @return The next complex number.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return previous.pow(power).add(constant);
    }

    /**
     * Returns an iterator over the sequence terms.
     *
     * @return An iterator for this sequence.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexIterator(first, this, nbTerms);
    }
}
