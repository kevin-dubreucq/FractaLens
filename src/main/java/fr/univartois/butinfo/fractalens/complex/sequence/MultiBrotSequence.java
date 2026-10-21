package fr.univartois.butinfo.fractalens.complex.sequence;
import fr.univartois.butinfo.fractalens.complex.IComplex;


import java.util.Iterator;

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
