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

    public FractalGeneratorBuilder width(int width) {
        this.width = width;
        return this;
    }

    public FractalGeneratorBuilder height(int height) {
        this.height = height;
        return this;
    }

    public FractalGeneratorBuilder dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public FractalGeneratorBuilder scale(double scale) {
        this.scale = scale;
        return this;
    }

    public FractalGeneratorBuilder zoom(double zoom) {
        this.scale = zoom;
        return this;
    }

    public FractalGeneratorBuilder focus(double focusX, double focusY) {
        this.focusX = focusX;
        this.focusY = focusY;
        return this;
    }

    public FractalGeneratorBuilder focus(IComplex focus) {
        this.focusX = focus.getRealPart();
        this.focusY = focus.getImaginaryPart();
        return this;
    }

    public FractalGeneratorBuilder maxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public FractalGeneratorBuilder sequence(BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = sequenceFactory;
        return this;
    }

    public FractalGeneratorBuilder sequence(ComplexSequenceType sequenceType) {
        this.sequenceFactory = sequenceType.getFactory();
        return this;
    }

    public FractalGeneratorBuilder palette(IColorPalette colorPalette) {
        this.colorPalette = colorPalette;
        return this;
    }

    public FractalGeneratorBuilder palette(ColorPaletteType paletteType) {
        this.colorPalette = paletteType.getPalette();
        return this;
    }

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
            else {
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
}
