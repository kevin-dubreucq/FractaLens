package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The {@code ScaledComplexPlaneTest} class tests the proper functionality
 * of the zoom decorator on the complex plane.
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
class ScaledComplexPlaneTest {

    /**
     * Tests that the {@link ScaledComplexPlane} correctly applies the zoom factor
     * to the complex number obtained from the underlying plane.
     */
    @Test
    void testAsComplexWithZoom() {
        // Creates a dummy complex plane implementation for testing purposes
        IComplexPlane dummyPlane = new IComplexPlane() {
            @Override
            public IComplex asComplex(int row, int column) {
                return new Complex(2.0, 4.0);
            }
        };

        // Applies a zoom factor of 0.5
        double zoomFactor = 0.5;
        IComplexPlane scaledPlane = new ScaledComplexPlane(dummyPlane, zoomFactor);

        // Retrieves the complex number for a given pixel
        IComplex result = scaledPlane.asComplex(0, 0);

        // Verifies the scaled real and imaginary parts
        assertEquals(1.0, result.getRealPart(), 1e-6);
        assertEquals(2.0, result.getImaginaryPart(), 1e-6);
    }
}
