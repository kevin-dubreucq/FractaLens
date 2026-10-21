package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The {@code MultiBrotSequenceTest} class tests the proper functionality
 * and mathematical accuracy of the MultiBrot sequence implementation.
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
class MultiBrotSequenceTest {

    /**
     * Tests that the {@code computeNext} method correctly computes the next term
     * using the MultiBrot formula ($\text{previous}^{\text{power}} + \text{first}$).
     * <p>
     * This test initializes a complex number {@code 1.0 + 2.0i}, sets the power to 2,
     * and evaluates the next term from a previous value of {@code 2.0 + 0.0i}.
     * It checks that the resulting real and imaginary parts match the expected mathematical calculation
     * within an acceptable floating-point tolerance ($\pm 10^{-6}$).
     * </p>
     */
    @Test
    void testComputeNext() {
        IComplex first = new Complex(1.0, 2.0);
        // Power 2, nbTerms 10
        MultiBrotSequence sequence = new MultiBrotSequence(first, 2, 10);

        IComplex previous = new Complex(2.0, 0.0);
        // Calculation: (2.0)^2 + (1.0 + 2.0i) = 4.0 + 1.0 + 2.0i = 5.0 + 2.0i
        IComplex next = sequence.computeNext(previous);

        assertEquals(5.0, next.getRealPart(), 1e-6);
        assertEquals(2.0, next.getImaginaryPart(), 1e-6);
    }

    /**
     * Tests that the iterator for the MultiBrot sequence is correctly instantiated
     * and returns a valid, non-null {@link Iterator} instance to traverse the sequence terms.
     */
    @Test
    void testIterator() {
        IComplex first = new Complex(0.0, 0.0);
        MultiBrotSequence sequence = new MultiBrotSequence(first, 2, 5);

        Iterator<IComplex> iterator = sequence.iterator();
        assertNotNull(iterator);
    }
}
