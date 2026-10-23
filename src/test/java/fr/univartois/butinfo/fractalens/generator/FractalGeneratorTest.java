package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.image.IFractalImage;
import fr.univartois.butinfo.fractalens.color.IColorPalette;

/**
 * The FractalGeneratorTest tests the implementation of {@link FractalGenerator}.
 *
 * @author Thomas Barloy
 * @version 1.0
 */
class FractalGeneratorTest {

    /**
     * Create a new FractalGenerator for tests.
     *
     * @param width The width of the image.
     * @param height The height of the image.
     * @param palette The color palette.
     * @return The FractalGenerator created.
     */
    private static IFractalGenerator createGenerator(int width, int height, IColorPalette palette) {
        IComplexPlane plane = new ComplexPlane(height, width);
        BiFunction<IComplex, Integer, IComplexSequence> factory = (z0, maxIter) -> new IComplexSequence() {
            @Override
            public IComplex computeNext(IComplex previous) {
                return previous;
            }

            @Override
            public Iterator<IComplex> iterator() {
                return List.of(z0).iterator();
            }
        };

        return new FractalGenerator(
                width, height, plane, factory, palette, 50, "target/test-fractal.png"
        );
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.FractalGenerator#computePixelColor(int, int)}.
     */
    @Test
    void testComputePixelColor() {
        IColorPalette palette = (iterations, maxIterations) -> Color.RED;
        IFractalGenerator generator = createGenerator(10, 10, palette);

        Color color = generator.computePixelColor(0, 0);

        assertNotNull(color, "The calculated color must not be null");
        assertEquals(Color.RED, color, "The color must correspond to the one provided by the palette.");
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.FractalGenerator#generateImage()}.
     */
    @Test
    void testGenerateImage() {
        IColorPalette palette = (iterations, maxIterations) -> Color.BLUE;
        int width = 5;
        int height = 8;
        IFractalGenerator generator = createGenerator(width, height, palette);

        IFractalImage image = generator.generateImage();

        assertNotNull(image, "The generated image should not be null.");
        assertEquals(width, image.getWidth(), "The image width must be correct");
        assertEquals(height, image.getHeight(), "The image height must be correct");
    }
}