package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.function.BiFunction;


public enum ComplexSequenceType {

    JULIA((z0, maxIter) -> ComplexSequence.createJuliaGeneric(z0, maxIter, new Complex(-0.4, 0.6))),

    MANDELBROT(ComplexSequence::createMandelbrot),

    BURNING_SHIP(ComplexSequence::createBurningShip),

    MULTIBROT_3((z0, maxIter) -> ComplexSequence.createMandelbrotGeneric(z0, maxIter, 3)),

    TRICORN((z0, maxIter) -> ComplexSequence.createTricorn(z0, maxIter, new Complex(-0.4, 0.6))),

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