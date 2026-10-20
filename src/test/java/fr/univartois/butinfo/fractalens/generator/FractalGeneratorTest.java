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
     * Crée un nouveau générateur de fractale préconfiguré pour les tests.
     *
     * @param width La largeur de l'image.
     * @param height La hauteur de l'image.
     * @param palette La palette de couleurs.
     * @return Le générateur de fractale créé.
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

        assertNotNull(color, "La couleur calculée ne doit pas être nulle.");
        assertEquals(Color.RED, color, "La couleur doit correspondre à celle fournie par la palette.");
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

        assertNotNull(image, "L'image générée ne doit pas être nulle.");
        assertEquals(width, image.getWidth(), "La largeur de l'image doit être correcte.");
        assertEquals(height, image.getHeight(), "La hauteur de l'image doit être correcte.");
    }
}