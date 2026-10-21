
package fr.univartois.butinfo.fractalens.complex.sequence;

import java.util.Iterator;
import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The GenericComplexSequenceTest tests the implementation of {@link IComplexSequence}
 * provided by {@link ComplexSequence} and its static factories.
 *
 * @author Beauvais Maxence
 * @version 0.2.0
 */
class GenericComplexSequenceTest {

    private static Complex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Tests the base constructor, computeNext() and iterator() methods.
     */
    @Test
    void testGenericSequence() {
        Complex first = createComplex(1, 1);
        int nbTerms = 10;
        // Simple function: previous + initial
        BinaryOperator<IComplex> func = IComplex::add;

        ComplexSequence sequence = new ComplexSequence(first, nbTerms, func);

        Complex previous = createComplex(2, 2);
        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(3, 3), next);

        Iterator<IComplex> iterator = sequence.iterator();
        assertNotNull(iterator);
        assertInstanceOf(ComplexIterator.class, iterator);
    }

    /**
     * Tests the createMandelbrot method (p=2).
     */
    @Test
    void testCreateMandelbrot() {
        Complex first = createComplex(1, 1);
        IComplexSequence sequence = ComplexSequence.createMandelbrot(first, 10);

        Complex previous = createComplex(2, 3);

        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(-4, 13), next);
    }

    /**
     * Tests the createMandelbrotGeneric method with custom power.
     */
    @Test
    void testCreateMandelbrotGeneric() {
        Complex first = createComplex(1, 1);
        IComplexSequence sequence = ComplexSequence.createMandelbrotGeneric(first, 10, 3); // p=3

        Complex previous = createComplex(1, 1);
        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(-1, 3), next);
    }

    /**
     * Tests the createTricorn method (p=2).
     */
    @Test
    void testCreateTricorn() {
        Complex first = createComplex(0, 0);
        Complex constant = createComplex(1, 1);
        IComplexSequence sequence = ComplexSequence.createTricorn(first, 10, constant);

        Complex previous = createComplex(2, 3);

        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(-4, -11), next);
    }

    /**
     * Tests the createTricornGeneric method with custom power.
     */
    @Test
    void testCreateTricornGeneric() {
        Complex first = createComplex(0, 0);
        Complex constant = createComplex(1, 1);
        IComplexSequence sequence = ComplexSequence.createTricornGeneric(first, 10, constant, 3); // p=3

        Complex previous = createComplex(1, 1);
        // Formula: conj(prev)^3 + c -> (1-1i)^3 + (1+1i) = (-2-2i) + (1+1i) = -1-1i
        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(-1, -1), next);
    }

    /**
     * Tests the genericBurningShip method with custom power.
     */
    @Test
    void testGenericBurningShip() {
        Complex first = createComplex(0, 0);
        Complex constant = createComplex(1, 1);
        IComplexSequence sequence = ComplexSequence.genericBurningShip(first, 10, constant, 3); // p=3

        Complex previous = createComplex(-1, -1);
        // Formula: (|Re(prev)| + i|Im(prev)|)^3 + c
        // (| -1 | + i| -1 |)^3 + (1+1i)
        // (1 + 1i)^3 + (1+1i) = (-2 + 2i) + (1+1i) = -1 + 3i
        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(-1, 3), next);
    }

    /**
     * Tests the createBurningShip method (Mandelbrot style).
     */
    @Test
    void testCreateBurningShip() {
        Complex first = createComplex(-1, -1);
        IComplexSequence sequence = ComplexSequence.createBurningShip(first, 10);

        Complex previous = createComplex(-1, -1);
        IComplex next = sequence.computeNext(previous);

        assertEquals(createComplex(-1, 1), next);
    }
}
