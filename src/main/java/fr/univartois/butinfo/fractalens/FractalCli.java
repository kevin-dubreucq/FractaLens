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
 * @author Lucas Vanstraceele
 * @version 1.1
 */
@Command(
        name = "fractalens",
        version = "FractaLens 1.1",
        description = "Generates fractal images and animations from mathematical complex sequences."
)
public class FractalCli implements Runnable {

    /** Whether the help is requested. */
    @Option(names = {"--help"}, usageHelp = true, description = "Display this help message and exit.")
    private boolean helpRequested;

    /** Whether the version is requested. */
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Display the version and exit.")
    private boolean versionRequested;

    /** The fractal to generate. */
    @Option(names = {"-f", "--fractal-name"}, description = "The name of the fractal to generate (e.g., MANDELBROT, JULIA).")
    private ComplexSequenceType fractalName = ComplexSequenceType.MANDELBROT;

    /** The height of the image, in pixels. */
    @Option(names = {"-h", "--height"}, description = "The height of the image to generate in pixels.")
    private int height = 800;

    /** The width of the image, in pixels. */
    @Option(names = {"-w", "--width"}, description = "The width of the image to generate in pixels.")
    private int width = 800;

    /** The scale (zoom) of the image. */
    @Option(names = {"-s", "--scale"}, description = "The scale (zoom) factor to apply on the image.")
    private double scale = 0.001;

    /** The real part of the center of the image. */
    @Option(names = {"-x", "--focus-x"}, description = "The real part of the center point of the image.")
    private double focusX = 0.0;

    /** The imaginary part of the center of the image. */
    @Option(names = {"-y", "--focus-y"}, description = "The imaginary part of the center point of the image.")
    private double focusY = 0.0;

    /** The maximum number of iterations. */
    @Option(names = {"-n", "--nb-iterations"}, description = "The maximum number of iterations to apply.")
    private int nbIterations = 250;

    /** The color palette to use. */
    @Option(names = {"-p", "--palette-name"}, description = "The name of the color palette to apply.")
    private ColorPaletteType paletteName = ColorPaletteType.GRAYSCALE;

    /** The type of image: single, zoom or rotation. */
    @Option(names = {"-t", "--type"}, description = "The type of image to generate: single, zoom, or rotation.")
    private String type = "single";

    /** The output file. */
    @Option(names = {"-o", "--output"}, description = "The name of the output file in which the fractal must be saved.")
    private String output = "fractal.png";

    /** The number of frames of an animation. */
    @Option(names = {"--frames"}, description = "Number of frames for the animation.")
    private int frameCount = 30;

    /** The zoom factor applied at each frame. */
    @Option(names = {"--zoom-factor"}, description = "Zoom factor per frame for animation.")
    private double zoomFactor = 0.85;

    /** The delay between two frames, in milliseconds. */
    @Option(names = {"--delay"}, description = "Delay between frames in milliseconds.")
    private int frameDelay = 80;

    /**
     * Generates the fractal described by the options and saves it in the output file.
     */
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

    /**
     * Runs the command and exits with its exit code.
     *
     * @param args The arguments of the command line.
     */
    public static void main(String[] args) {
        int exitCode = new CommandLine(new FractalCli()).execute(args);
        System.exit(exitCode);
    }
}