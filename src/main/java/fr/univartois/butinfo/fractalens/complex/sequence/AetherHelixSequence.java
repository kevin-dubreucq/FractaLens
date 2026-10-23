package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import java.util.Iterator;

/**
 * The {@code AetherHelixSequence} class represents a custom complex sequence
 * for generating crystal-like fractal trajectories ($z_{n+1} = z_n^3 - z_n \cdot c + c$).
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
public class AetherHelixSequence implements IComplexSequence {

    /**
     * The first term of the sequence (constant c).
     */
    private final IComplex first;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * Creates a new Aether-Helix sequence.
     *
     * @param first The initial complex number (constant c).
     * @param nbTerms The maximum number of terms.
     */
    public AetherHelixSequence(IComplex first, int nbTerms) {
        this.first = first;
        this.nbTerms = nbTerms;
    }

    @Override
    public IComplex computeNext(IComplex previous) {

        return previous.pow(3).subtract(previous.multiply(first)).add(first);
    }

    @Override
    public Iterator<IComplex> iterator() {

        return new ComplexIterator(first, this, nbTerms);
    }
}
