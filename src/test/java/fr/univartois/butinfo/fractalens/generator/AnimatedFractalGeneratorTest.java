package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.MandelbrotSequence;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * The AnimatedFractalGeneratorTest class tests the implementation of {@link AnimatedFractalGenerator}.
 *
 * @author Thomas Barloy
 * @version 1.0
 */
class AnimatedFractalGeneratorTest {

    /**
     * Test method for {@link AnimatedFractalGenerator#computePixelColor(int, int)} when the generator list is empty.
     */
    @Test
    void testComputePixelColorEmpty() {
        AnimatedFractalGenerator generator = new AnimatedFractalGenerator(List.of(), "target/test.gif", 100);
        Color color = generator.computePixelColor(0, 0);
        assertEquals(Color.BLACK, color, "Should return BLACK when generators list is empty.");
    }

    /**
     * Test method for {@link AnimatedFractalGenerator#computePixelColor(int, int)} with generators.
     */
    @Test
    void testComputePixelColorDelegation() {
        FractalGenerator dummySubGenerator = new FractalGenerator(
                10, 10,
                new ComplexPlane(10, 10),
                MandelbrotSequence::new,
                (iterations, maxIter) -> Color.RED,
                50,
                "target/dummy.png"
        );

        AnimatedFractalGenerator generator = new AnimatedFractalGenerator(
                List.of(dummySubGenerator), "target/test.gif", 100
        );

        Color color = generator.computePixelColor(0, 0);
        assertEquals(Color.RED, color, "Should delegate color computation to the first generator.");
    }

    /**
     * Test method for {@link AnimatedFractalGenerator#generateImage()}.
     */
    @Test
    void testGenerateImageReturnsLast() {
        FractalGenerator gen1 = new FractalGenerator(
                5, 5,
                new ComplexPlane(5, 5),
                MandelbrotSequence::new,
                (i, max) -> Color.BLACK,
                10,
                "target/dummy1.png"
        );

        FractalGenerator gen2 = new FractalGenerator(
                10, 10,
                new ComplexPlane(10, 10),
                MandelbrotSequence::new,
                (i, max) -> Color.WHITE,
                10,
                "target/dummy2.png"
        );

        AnimatedFractalGenerator generator = new AnimatedFractalGenerator(
                List.of(gen1, gen2), "target/test.gif", 100
        );

        IFractalImage result = generator.generateImage();

        assertNotNull(result, "The generated image should not be null.");
        assertEquals(10, result.getWidth(), "It should return the image from the last generator (width 10).");
    }
}