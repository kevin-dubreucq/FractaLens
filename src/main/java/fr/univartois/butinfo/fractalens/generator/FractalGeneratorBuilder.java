package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.color.IColorPalette;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.ScaledComplexPlane;
import fr.univartois.butinfo.fractalens.complex.TranslatedComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;

import java.util.function.BiFunction;

/**
 * The FractalGeneratorBuilder class allows configuring and instantiating a FractalGenerator
 * in a fluent way, without any default values.
 *
 * @author Lucas Vanstraceele
 * @version 1.0
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

    /**
     * Sets the width of the image to generate.
     *
     * @param width The width in pixels.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder width(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the image to generate.
     *
     * @param height The height in pixels.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder height(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets both the width and height of the image to generate.
     *
     * @param width The width in pixels.
     * @param height The height in pixels.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the scale factor for the complex plane.
     *
     * @param scale The scale value.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder scale(double scale) {
        this.scale = scale;
        return this;
    }

    /**
     * Sets the zoom factor for the complex plane.
     *
     * @param zoom The zoom value.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder zoom(double zoom) {
        this.scale = zoom;
        return this;
    }

    /**
     * Sets the focus coordinates for the center of the image.
     *
     * @param focusX The real part of the center.
     * @param focusY The imaginary part of the center.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder focus(double focusX, double focusY) {
        this.focusX = focusX;
        this.focusY = focusY;
        return this;
    }

    /**
     * Sets the focus complex number for the center of the image.
     *
     * @param focus The complex number representing the center.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder focus(IComplex focus) {
        this.focusX = focus.getRealPart();
        this.focusY = focus.getImaginaryPart();
        return this;
    }

    /**
     * Sets the maximum number of iterations allowed for the sequences.
     *
     * @param maxIterations The maximum number of iterations.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder maxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Sets the sequence factory using a BiFunction.
     *
     * @param sequenceFactory The sequence factory.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder sequence(BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = sequenceFactory;
        return this;
    }

    /**
     * Sets the sequence using a predefined complex sequence type enumeration.
     *
     * @param sequenceType The sequence type enum.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder sequence(ComplexSequenceType sequenceType) {
        this.sequenceFactory = sequenceType.getFactory();
        return this;
    }

    /**
     * Sets the color palette strategy.
     *
     * @param colorPalette The color palette.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder palette(IColorPalette colorPalette) {
        this.colorPalette = colorPalette;
        return this;
    }

    /**
     * Sets the color palette using a predefined color palette type enumeration.
     *
     * @param paletteType The color palette type enum.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder palette(ColorPaletteType paletteType) {
        this.colorPalette = paletteType.getPalette();
        return this;
    }

    /**
     * Sets the output file path where the image will be saved.
     *
     * @param outputPath The file path string.
     * @return This builder instance.
     */
    public FractalGeneratorBuilder outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    /**
     * Builds and returns a configured instance of FractalGenerator.
     *
     * @return The configured FractalGenerator.
     */
    public FractalGenerator build() {
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
