package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.color.IColorPalette;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.ScaledComplexPlane;
import fr.univartois.butinfo.fractalens.complex.TranslatedComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * The FractalGeneratorBuilder class allows configuring and instantiating
 * a FractalGenerator or AnimatedFractalGenerator in a fluent way.
 *
 * @author Lucas Vanstraceele
 * @version 1.3
 */
public class FractalGeneratorBuilder {

    private int width;
    private int height;
    private double scale;
    private double focusX;
    private double focusY;
    private int maxIterations;
    private BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory;
    private IColorPalette colorPalette;
    private String outputPath;

    // Animation configuration parameters
    private int frameCount;
    private double zoomFactor;
    private int frameDelayMs;
    private boolean isRotationAnimation;
    private IComplex initialJuliaC;

    /**
     * Sets the width of the generated fractal image.
     *
     * @param width The width of the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder width(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the generated fractal image.
     *
     * @param height The height of the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder height(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the dimensions of the generated fractal image.
     *
     * @param width The width of the generated fractal image.
     * @param height The height of the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the scale of the generated fractal image.
     *
     * @param scale The scale of the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder scale(double scale) {
        this.scale = scale;
        return this;
    }

    /**
     * Sets the zoom factor of the generated fractal image.
     *
     * @param zoom The zoom factor of the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder zoom(double zoom) {
        this.scale = zoom;
        return this;
    }

    /**
     * Sets the focus point of the generated fractal image.
     *
     * @param focusX The x-coordinate of the focus point.
     * @param focusY The y-coordinate of the focus point.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder focus(double focusX, double focusY) {
        this.focusX = focusX;
        this.focusY = focusY;
        return this;
    }

    /**
     * Sets the focus point of the generated fractal image.
     *
     * @param focus The focus point of the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder focus(IComplex focus) {
        this.focusX = focus.getRealPart();
        this.focusY = focus.getImaginaryPart();
        return this;
    }

    /**
     * Sets the maximum number of iterations for the generated fractal image.
     *
     * @param maxIterations The maximum number of iterations for the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder maxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Sets the sequence factory for generating the fractal image.
     *
     * @param sequenceFactory The sequence factory for generating the fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder sequence(BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = sequenceFactory;
        return this;
    }

    /**
     * Sets the sequence type for generating the fractal image.
     *
     * @param sequenceType The sequence type for generating the fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder sequence(ComplexSequenceType sequenceType) {
        this.sequenceFactory = sequenceType.getFactory();
        return this;
    }

    /**
     * Sets the color palette for generating the fractal image.
     *
     * @param colorPalette The color palette for generating the fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder palette(IColorPalette colorPalette) {
        this.colorPalette = colorPalette;
        return this;
    }

    /**
     * Sets the color palette type for generating the fractal image.
     *
     * @param paletteType The color palette type for generating the fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder palette(ColorPaletteType paletteType) {
        this.colorPalette = paletteType.getPalette();
        return this;
    }

    /**
     * Sets the output path for saving the generated fractal image.
     *
     * @param outputPath The output path for saving the generated fractal image.
     * @return The FractalGeneratorBuilder instance for method chaining.
     */
    public FractalGeneratorBuilder outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    /**

     Configures a progressive zoom animation.*
     @param frameCount Number of frames in the GIF.
     @param zoomFactor Multiplicative factor for scale per frame.
     @param frameDelayMs Delay between frames in milliseconds.
     @return This builder instance.*/
    public FractalGeneratorBuilder zoomAnimation(int frameCount, double zoomFactor, int frameDelayMs) {
        this.frameCount = frameCount;
        this.zoomFactor = zoomFactor;
        this.frameDelayMs = frameDelayMs;
        this.isRotationAnimation = false;
        return this;
    }

    /**
     * Configures a rotation animation for Julia set.
     *
     * @param frameCount Number of frames in the GIF.
     * @param initialC Initial complex constant c.
     * @param frameDelayMs Delay between frames in milliseconds.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder rotationAnimation(int frameCount, IComplex initialC, int frameDelayMs) {
        this.frameCount = frameCount;
        this.initialJuliaC = initialC;
        this.frameDelayMs = frameDelayMs;
        this.isRotationAnimation = true;
        return this;
    }

    /**
     * Builds and returns a single FractalGenerator or an AnimatedFractalGenerator composite.
     *
     * @return The configured IFractalGenerator.
     */
    public IFractalGenerator build() {
        if (frameCount > 0) {
            List<IFractalGenerator> generators = new ArrayList<>();

            if (isRotationAnimation) {
                rotation(generators);
            }
            else {
                zoom(generators);
            }

            return new AnimatedFractalGenerator(generators, outputPath, frameDelayMs);
        }

        // Single image generator
        IComplexPlane plane = new ComplexPlane(height, width);
        if (scale != 0.0) {
            plane = new ScaledComplexPlane(plane, scale);
        }
        if (focusX != 0.0 || focusY != 0.0) {
            plane = new TranslatedComplexPlane(plane, new Complex(focusX, focusY));
        }

        return new FractalGenerator(width, height, plane, sequenceFactory, colorPalette, maxIterations, outputPath);
    }

    /**
     * Performs zooming on the fractal generator.
     *
     * @param generators The list of fractal generators.
     */
    private void zoom(List<IFractalGenerator> generators) {
        double currentScale = scale;
        for (int i = 0; i < frameCount; i++) {
            IComplexPlane plane = new ComplexPlane(height, width);
            if (currentScale != 0.0) {
                plane = new ScaledComplexPlane(plane, currentScale);
            }
            if (focusX != 0.0 || focusY != 0.0) {
                plane = new TranslatedComplexPlane(plane, new Complex(focusX, focusY));
            }

            generators.add(new FractalGenerator(width, height, plane, sequenceFactory, colorPalette, maxIterations, outputPath));
            currentScale *= zoomFactor;
        }
    }

    /**
     * Performs rotation on the fractal generator.
     *
     * @param generators The list of fractal generators.
     */
    private void rotation(List<IFractalGenerator> generators) {
        double angleStep = 2 * Math.PI / frameCount;
        for (int i = 0; i < frameCount; i++) {
            double theta = i * angleStep;
            IComplex rotationFactor = new Complex(Math.cos(theta), Math.sin(theta));
            IComplex rotatedC = initialJuliaC.multiply(rotationFactor);

            BiFunction<IComplex, Integer, IComplexSequence> rotatedJuliaFactory =
                    (z0, maxIter) -> ComplexSequence.createJuliaGeneric(z0, maxIter, rotatedC);

            IComplexPlane plane = new ComplexPlane(height, width);
            if (scale != 0.0) {
                plane = new ScaledComplexPlane(plane, scale);
            }
            if (focusX != 0.0 || focusY != 0.0) {
                plane = new TranslatedComplexPlane(plane, new Complex(focusX, focusY));
            }

            generators.add(new FractalGenerator(width, height, plane, rotatedJuliaFactory, colorPalette, maxIterations, outputPath));
        }
    }
}
