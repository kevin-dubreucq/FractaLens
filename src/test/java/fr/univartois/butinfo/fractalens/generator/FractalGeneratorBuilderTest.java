package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The FractalGeneratorBuilderTest class provides unit tests for the FractalGeneratorBuilder class.
 *
 * @author Lucas Vanstraceele
 * @version 5.0
 */
public class FractalGeneratorBuilderTest {

    /**
     * Tests that a FractalGenerator can be successfully built using fluent method calls
     * with enum-based configurations.
     */
    @Test
    public void testBuildWithEnums() {
        FractalGenerator generator = new FractalGeneratorBuilder()
                .dimensions(600, 600)
                .zoom(0.001)
                .focus(0.1, 0.6)
                .maxIterations(100)
                .sequence(ComplexSequenceType.JULIA)
                .palette(ColorPaletteType.GRADIENT_YELLOW_RED)
                .outputPath("test_julia.png")
                .build();

        assertNotNull(generator, "The generated fractal generator should not be null.");
    }

    /**
     * Tests that a FractalGenerator can be successfully built using custom objects and lambda expressions.
     */
    @Test
    public void testBuildWithCustomParameters() {
        FractalGenerator generator = new FractalGeneratorBuilder()
                .width(800)
                .height(400)
                .scale(0.004)
                .focus(new Complex(-0.75, 0.0))
                .maxIterations(50)
                .sequence((z0, maxIter) -> ComplexSequenceType.MANDELBROT.getFactory().apply(z0, maxIter))
                .palette(ColorPaletteType.GRAYSCALE.getPalette())
                .outputPath("test_mandelbrot.png")
                .build();

        assertNotNull(generator, "The generated fractal generator should not be null.");
    }
}
