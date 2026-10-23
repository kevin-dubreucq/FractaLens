package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.function.BiFunction;

/**
 * Represents the different types of complex sequences.
 */
public enum ComplexSequenceType {

    /**
     * Creates a new generic Julia sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @return The generated generic Julia sequence.
     */
    JULIA((z0, maxIter) -> ComplexSequence.createJuliaGeneric(z0, maxIter, new Complex(-0.4, 0.6))),

    /**
     * Creates a new generic Mandelbrot sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @return The generated generic Mandelbrot sequence.
     */
    MANDELBROT(ComplexSequence::createMandelbrot),

    /**
     * Creates a new generic Burning Ship sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @return The generated generic Burning Ship sequence.
     */
    BURNING_SHIP(ComplexSequence::createBurningShip),

    /**
     * Creates a new generic Multibrot sequence with exponent 3.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @return The generated generic Multibrot sequence with exponent 3.
     */
    MULTIBROT_3((z0, maxIter) -> ComplexSequence.createMandelbrotGeneric(z0, maxIter, 3)),

    /**
     * Creates a new generic Tricorn sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @return The generated generic Tricorn sequence.
     */
    TRICORN((z0, maxIter) -> ComplexSequence.createTricorn(z0, maxIter, new Complex(-0.4, 0.6))),

    /**
     * Creates a new generic custom sequence.
     *
     * @param z0 The initial complex number.
     * @param maxIter The maximum number of terms.
     * @return The generated generic custom sequence.
     */
    CUSTOM_SEQUENCE((z0, maxIter) -> new ComplexSequence(z0, maxIter,
            (prev, initial) -> prev.pow(3).subtract(prev).add(new Complex(-0.2, 0.4))
    ));

    /**
     * Interface used to create a complex sequence.
     */
    private final BiFunction<IComplex, Integer, IComplexSequence> factory;

    /**
     *
     * @param factory Lambda used to create a complex sequence.
     */
    ComplexSequenceType(BiFunction<IComplex, Integer, IComplexSequence> factory) {
        this.factory = factory;
    }

    /**
     * Returns the factory (BiFunction) associated with the complex sequence.
     *
     * @return The BiFunction allowing to create the sequence.
     */
    public BiFunction<IComplex, Integer, IComplexSequence> getFactory() {
        return factory;
    }
}