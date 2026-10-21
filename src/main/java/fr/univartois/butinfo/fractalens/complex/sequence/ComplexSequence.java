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
     * Creates a new generic Burning Ship sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @param constant The constant complex number.
     * @param p The power to which the absolute values are raised.
     */
    public static IComplexSequence genericBurningShip (IComplex z0, int maxIter, IComplex constant, int p){
        return new ComplexSequence(z0, maxIter, (previous) -> (new Complex(Math.abs(previous.getRealPart()), 0).add(new Complex(0, Math.abs(previous.getImaginaryPart())))).pow(p).add(constant));
    }

    /**
     * Creates a new Burning Ship sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @param constant The constant complex number.
     */
    public static IComplexSequence burningShip (IComplex z0, int maxIter, IComplex constant){
        return genericBurningShip(z0, maxIter, constant, 2);
    }
}