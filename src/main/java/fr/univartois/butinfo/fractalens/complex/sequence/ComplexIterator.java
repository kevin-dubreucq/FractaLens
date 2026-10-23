package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The ComplexIterator class allows iterating step by step through the terms
 * of a complex number sequence. It implements the standard Java Iterator interface.
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
public class ComplexIterator implements Iterator<IComplex> {

    /** The maximum number of iterations allowed for the sequence. */
    private int maxIterations = 0;

    /** The counter tracking the number of iterations performed. */
    private int compteurIteration;

    /** The current complex number being evaluated. */
    private IComplex currentNumber;

    /** The complex sequence providing the computation rule. */
    private IComplexSequence sequence;

    /**
     * Creates a new ComplexIterator.
     *
     * @param currentNumber The initial complex number ($z_0$) to start the sequence.
     * @param sequence The complex sequence being iterated over.
     * @param maxIterations The maximum number of iterations allowed.
     */
    public ComplexIterator(IComplex currentNumber, IComplexSequence sequence, int maxIterations) {
        setCurrentNumber(currentNumber);
        setSequence(sequence);
        setMaxIterations(maxIterations);
        this.compteurIteration = 0;
    }

    /**
     * Sets the current complex number.
     *
     * @param currentNumber The new current complex number.
     */
    public void setCurrentNumber(IComplex currentNumber) {
        this.currentNumber = currentNumber;
    }

    /**
     * Sets the maximum number of iterations.
     *
     * @param maxIterations The maximum number of iterations.
     */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
     * Sets the complex sequence.
     *
     * @param sequence The complex sequence.
     */
    public void setSequence(IComplexSequence sequence) {
        this.sequence = sequence;
    }

    /**
     * Gives the complex sequence associated with this iterator.
     *
     * @return The complex sequence.
     */
    public IComplexSequence getSequence() {
        return sequence;
    }

    /**
     * Checks if there are more terms to iterate through.
     * The iteration stops if the maximum number of iterations is reached
     * or if the modulus of the current complex number exceeds 2 (divergence).
     *
     * @return true if the iteration can continue, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return (compteurIteration < maxIterations) && (currentNumber.abs() <= 2);
    }

    /**
     * Computes and returns the next complex number in the sequence.
     *
     * @return The next complex number.
     */
    @Override
    public IComplex next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        IComplex temp = this.currentNumber;
        this.currentNumber = this.sequence.computeNext(currentNumber);
        compteurIteration++;
        return temp;
    }
}
