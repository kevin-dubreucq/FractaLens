package fr.univartois.butinfo.fractalens.complex.sequence;
import fr.univartois.butinfo.fractalens.complex.IComplex;


import java.util.Iterator;

/**
 * A {@code MultiBrotSequence} is a sequence of complex numbers defined by the formula:
 * <pre>
 *     z<sub>n+1</sub> = z<sub>n</sub><sup>p</sup> + c
 * </pre>
 * where <code>c</code> is the first term of the sequence, <code>p</code> is the power applied to the complex number at each iteration,
 * and <code>n</code> is the index of the term in the sequence.
 */
public class MultiBrotSequence implements IComplexSequence {

    /**
     * The first term of the sequence.
     */
    private final IComplex first;


    /**
     * The power applied to the complex number at each iteration.
     */
    private final int power;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * Creates a new {@code MultiBrotSequence} with the given first term, power, and maximum number of terms.
     *
     * @param first The first term of the sequence.
     * @param power The power applied to the complex number at each iteration.
     * @param nbTerms The maximum number of terms.
     */
    public MultiBrotSequence(IComplex first, int power,
            int nbTerms) {

        this.first = first;
        this.power = power;
        this.nbTerms = nbTerms;
    }

    @Override
    public IComplex computeNext(IComplex previous) {

        return previous.pow(power).add(first);
    }

    @Override
    public Iterator<IComplex> iterator() {

        return new ComplexIterator(first, this, nbTerms);
    }

}
