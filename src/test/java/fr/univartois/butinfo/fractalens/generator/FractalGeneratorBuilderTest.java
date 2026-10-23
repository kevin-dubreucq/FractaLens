package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The FractalGeneratorBuilderTest class tests the configuration and instantiation
 * of fractal generators using the builder pattern.
 *
 * @author Lucas Vanstraceele
 * @version 1.0
 */
class FractalGeneratorBuilderTest {

    @Test
    void testBuildStandardGenerator() {
        IFractalGenerator generator = new FractalGeneratorBuilder()
                .dimensions(200, 200)
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.GRAYSCALE)
                .focus(0.0, 0.0)
                .scale(0.01)
                .maxIterations(50)
                .outputPath("test_mandelbrot.png")
                .build();

        assertNotNull(generator, "The built generator should not be null.");
    }

    @Test
    void testBuildAnimatedGenerator() {
        IFractalGenerator animatedGenerator = new FractalGeneratorBuilder()
                .dimensions(200, 200)
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.GRAYSCALE)
                .focus(0.0, 0.0)
                .scale(0.01)
                .maxIterations(50)
                .zoomAnimation(10, 0.9, 50)
                .outputPath("test_animation.gif")
                .build();

        assertNotNull(animatedGenerator, "The built animated generator should not be null.");
    }
}
