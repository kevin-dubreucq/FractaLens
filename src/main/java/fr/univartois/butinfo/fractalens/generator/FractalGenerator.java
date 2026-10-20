package fr.univartois.butinfo.fractalens.generator;

import java.awt.Color;
import java.io.IOException;
import java.util.function.BiFunction;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.image.FractalImage;
import fr.univartois.butinfo.fractalens.image.IFractalImage;
import fr.univartois.butinfo.fractalens.color.IColorPalette;

/**
 * The FractalGenerator class implements IFractalGenerator to generate fractal images
 * using a complex plane, a sequence factory (BiFunction), a color palette, and output settings.
 *
 * @author Thomas Barloy
 * @version 1.0
 */
public class FractalGenerator implements IFractalGenerator {

    private final int width;
    private final int height;
    private final IComplexPlane complexPlane;
    private final BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory;
    private final IColorPalette colorPalette;
    private final int maxIterations;
    private final String outputPath;

    private IFractalImage generatedImage;

    /**
     * Creates a new FractalGenerator.
     *
     * @param width Width of the image in pixels.
     * @param height Height of the image in pixels.
     * @param complexPlane The complex plane used for coordinates mapping.
     * @param sequenceFactory A BiFunction building the complex sequence from an initial term and max iterations.
     * @param colorPalette The color palette strategy.
     * @param maxIterations Maximum iterations to test sequence divergence.
     * @param outputPath Path where the generated image will be saved.
     */
    public FractalGenerator(int width, int height,
                            IComplexPlane complexPlane,
                            BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory,
                            IColorPalette colorPalette,
                            int maxIterations,
                            String outputPath) {
        this.width = width;
        this.height = height;
        this.complexPlane = complexPlane;
        this.sequenceFactory = sequenceFactory;
        this.colorPalette = colorPalette;
        this.maxIterations = maxIterations;
        this.outputPath = outputPath;
    }

    /**
     * Generates a full fractal image.
     *
     * @return The generated image.
     */
    @Override
    public IFractalImage generateImage() {
        generatedImage = new FractalImage(width, height);

        // Iterate over each pixel in the image.
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Color color = computePixelColor(row, column);
                generatedImage.setColor(row, column, color);
            }
        }
        return generatedImage;
    }

    /**
     * Computes the color of a given pixel.
     * This method iterates of the sequence considered by this generator to evaluate the
     * number of iterations required to decide whether this sequence diverges.
     *
     * @param row The row of the pixel.
     * @param column The column of the pixel.
     *
     * @return The computed color for the pixel.
     */
    @Override
    public Color computePixelColor(int row, int column) {
        // Convert the pixel to a complex number using the complex plane.
        IComplex z0 = complexPlane.asComplex(row, column);

        // Build the complex sequence using the sequence factory.
        IComplexSequence sequence = sequenceFactory.apply(z0, maxIterations);

        // Iterate over the sequence to count the number of iterations.
        int iterations = 0;
        for (IComplex term : sequence) {
            iterations++;
        }

        // Determine the color of the pixel using the color palette.
        return colorPalette.getColor(iterations, maxIterations);
    }

    /**
     * Saves the generated image in the output file.
     */
    @Override
    public void saveImage() {
        // Avoid null pointer exception
        if (generatedImage == null) {
            generateImage();
        }

        // In case of an error while saving the image, print an error message.
        try {
            generatedImage.saveAs(outputPath);
        } catch (IOException e) {
            System.err.println("Error while saving the fractal image: " + e.getMessage());
        }
    }
}