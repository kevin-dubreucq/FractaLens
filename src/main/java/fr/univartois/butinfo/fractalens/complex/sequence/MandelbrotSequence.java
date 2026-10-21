package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class MandelbrotSequence implements IComplexSequence{

    /**
     * The first term of the sequence.
     */
    private final IComplex first;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * Creates a new Julia sequence.
     *
     * @param first The initial complex number.
     * @param nbTerms The maximum number of terms.
     */
    public MandelbrotSequence(IComplex first, int nbTerms) {
        this.first = first;
        this.nbTerms = nbTerms;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return previous.multiply(previous).add(first);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Iterator<IComplex> iterator() {
        return new ComplexIterator(first,this,nbTerms);
    }
}
