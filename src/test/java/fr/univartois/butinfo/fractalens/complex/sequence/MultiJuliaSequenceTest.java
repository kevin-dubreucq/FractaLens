package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MultiJuliaSequenceTest {

    @Test
    void testMultiJuliaSequenceIteration() {
        IComplex first = new Complex(0.1, 0.1);
        IComplex constant = new Complex(-0.4, 0.6);
        int power = 2;
        int maxTerms = 5;

        IComplexSequence sequence = new MultiJuliaSequence(first, constant, power, maxTerms);
        Iterator<IComplex> iterator = sequence.iterator();

        assertTrue(iterator.hasNext());
        IComplex z0 = iterator.next();
        assertEquals(0.1, z0.getRealPart(), 1e-9);
        assertEquals(0.1, z0.getImaginaryPart(), 1e-9);

        assertTrue(iterator.hasNext());
        IComplex z1 = iterator.next();
        assertEquals(-0.4, z1.getRealPart(), 1e-9);
        assertEquals(0.62, z1.getImaginaryPart(), 1e-9);
    }
}
