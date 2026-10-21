package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * The MandelbrotSequence class implements {@link IComplexSequence}
 * for the Mandelbrot set.
 *
 * @author Beauvais Maxence
 * @version 0.1.0
 */
public class MandelbrotSequence implements IComplexSequence{

    /**
     * The first term of the sequence (corresponds to the point z in the complex plane).
     */
    private final IComplex first;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * Creates a new Mandelbrot sequence.
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
