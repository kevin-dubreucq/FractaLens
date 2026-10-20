package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The {@code TranslatedComplexPlaneTest} class tests the proper functionality
 * of the translation decorator on the complex plane.
 *
 * @author Lucas Vanstraceele
 * @version 0.1.0
 */
class TranslatedComplexPlaneTest {

    /**
     * Tests that the {@link TranslatedComplexPlane} correctly applies the translation
     * by adding the center complex number to the result obtained from the underlying plane.
     */
    @Test
    void testAsComplexWithTranslation() {
        // Creates a dummy complex plane implementation for testing purposes
        IComplexPlane dummyPlane = new IComplexPlane() {
            @Override
            public IComplex asComplex(int row, int column) {
                return new Complex(1.0, 2.0);
            }
        };

        // Defines the translation center (e.g., 0.1 + 0.6i)
        IComplex center = new Complex(0.1, 0.6);
        IComplexPlane translatedPlane = new TranslatedComplexPlane(dummyPlane, center);

        // Retrieves the complex number for a given pixel
        IComplex result = translatedPlane.asComplex(0, 0);

        // Verifies the translated real and imaginary parts
        assertEquals(1.1, result.getRealPart(), 1e-6);
        assertEquals(2.6, result.getImaginaryPart(), 1e-6);
    }
}
