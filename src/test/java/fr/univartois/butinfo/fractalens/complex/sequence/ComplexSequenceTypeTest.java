package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The ComplexSequenceTypeTest class tests the implementation of {@link ComplexSequenceType}.
 *
 * @author Thomas Barloy
 * @version 1.0
 */
class ComplexSequenceTypeTest {

    /**
     * Test method for {@link ComplexSequenceType#getFactory()}.
     * Verifies that each sequence type correctly returns a non-null factory
     * capable of building a non-null {@link IComplexSequence} with a valid iterator.
     */
    @Test
    void testGetFactory() {
        IComplex z0 = new Complex(0.5, 0.5);
        int maxIterations = 50;

        for (ComplexSequenceType type : ComplexSequenceType.values()) {
            assertNotNull(type.getFactory(), "The factory for " + type.name() + " should not be null.");

            IComplexSequence sequence = type.getFactory().apply(z0, maxIterations);
            assertNotNull(sequence, "The sequence generated for " + type.name() + " should not be null.");
            assertNotNull(sequence.iterator(), "The iterator for " + type.name() + " should not be null.");
        }
    }
}