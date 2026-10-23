package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * The FractalCli class implements the command-line interface for FractaLens
 * using Picocli, managing all required parameters for generating images or animations.
 *
 * @author Your Team
 * @version 1.1
 */
@Command(
        name = "fractalens",
        mixinStandardHelpOptions = true,
        version = "FractaLens 1.1",
        description = "Generates fractal images and animations from mathematical complex sequences."
)
public class FractalCli implements Runnable {

    @Option(names = {"-f", "--fractal-name"}, description = "The name of the fractal to generate (e.g., MANDELBROT, JULIA).")
    private ComplexSequenceType fractalName = ComplexSequenceType.MANDELBROT;

    @Option(names = {"-h", "--height"}, description = "The height of the image to generate in pixels.")
    private int height = 800;

    @Option(names = {"-w", "--width"}, description = "The width of the image to generate in pixels.")
    private int width = 800;

    @Option(names = {"-s", "--scale"}, description = "The scale (zoom) factor to apply on the image.")
    private double scale = 0.001;

    @Option(names = {"-x", "--focus-x"}, description = "The real part of the center point of the image.")
    private double focusX = 0.0;

    @Option(names = {"-y", "--focus-y"}, description = "The imaginary part of the center point of the image.")
    private double focusY = 0.0;

    @Option(names = {"-n", "--nb-iterations"}, description = "The maximum number of iterations to apply.")
    private int nbIterations = 250;

    @Option(names = {"-p", "--palette-name"}, description = "The name of the color palette to apply.")
    private ColorPaletteType paletteName = ColorPaletteType.GRAYSCALE;

    @Option(names = {"-t", "--type"}, description = "The type of image to generate: single, zoom, or rotation.")
    private String type = "single";

    @Option(names = {"-o", "--output"}, description = "The name of the output file in which the fractal must be saved.")
    private String output = "fractal.png";

    @Option(names = {"--frames"}, description = "Number of frames for the animation.")
    private int frameCount = 30;

    @Option(names = {"--zoom-factor"}, description = "Zoom factor per frame for animation.")
    private double zoomFactor = 0.85;

    @Option(names = {"--delay"}, description = "Delay between frames in milliseconds.")
    private int frameDelay = 80;

    @Override
    public void run() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .dimensions(width, height)
                .sequence(fractalName)
                .palette(paletteName)
                .focus(focusX, focusY)
                .scale(scale)
                .maxIterations(nbIterations)
                .outputPath(output);

        if ("zoom".equalsIgnoreCase(type)) {
            builder.zoomAnimation(frameCount, zoomFactor, frameDelay);
        } else if ("rotation".equalsIgnoreCase(type)) {
            builder.rotationAnimation(frameCount, new Complex(0.7885, 0.0), frameDelay);
        }

        IFractalGenerator generator = builder.build();
        generator.saveImage();
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FractalCli()).execute(args);
        System.exit(exitCode);
    }
}
