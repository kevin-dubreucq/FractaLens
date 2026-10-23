package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;

/**
 * The {@code Main} class is the entry point of the application.
 */
public class Main {

    /**
     * Private constructor to prevent instantiation of the {@code Main} class.
     */
    private Main(){
        throw new IllegalStateException("Main class should not be instantiated");
    }

    public static void main() {

        IFractalGenerator mandelbrotImage = new FractalGeneratorBuilder()
                .dimensions(600, 800)
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.GRADIENT_BLUE_BLACK)
                .focus(-0.75, 0.0)
                .scale(0.001)
                .maxIterations(250)
                .outputPath("mandelbrot_statique.png")
                .build();

        mandelbrotImage.saveImage();

        IFractalGenerator juliaImage = new FractalGeneratorBuilder()
                .dimensions(800, 800)
                .sequence(ComplexSequenceType.JULIA)
                .palette(ColorPaletteType.GRADIENT_YELLOW_RED)
                .focus(0.1, 0.6)
                .scale(0.001)
                .maxIterations(250)
                .outputPath("julia_statique.png")
                .build();

        juliaImage.saveImage();

        IFractalGenerator burningShipImage = new FractalGeneratorBuilder()
                .dimensions(800, 800)
                .sequence(ComplexSequenceType.BURNING_SHIP)
                .palette(ColorPaletteType.GRADIENT_BLACK_YELLOW)
                .focus(-1.755, -0.03)
                .scale(0.0001)
                .maxIterations(64)
                .outputPath("burningship_statique.png")
                .build();

        burningShipImage.saveImage();

        IFractalGenerator mandelbrotZoom = new FractalGeneratorBuilder()
                .dimensions(400, 400)
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.GRADIENT_BLUE_BLACK)
                .focus(-0.743643887037151, 0.131825904205330)
                .scale(0.005)
                .maxIterations(200)
                .zoomAnimation(30, 0.85, 80)
                .outputPath("mandelbrot_zoom.gif")
                .build();

        mandelbrotZoom.saveImage();

        IFractalGenerator juliaRotation = new FractalGeneratorBuilder()
                .dimensions(400, 400)
                .sequence(ComplexSequenceType.JULIA)
                .palette(ColorPaletteType.GRADIENT_YELLOW_RED)
                .focus(0.0, 0.0)
                .scale(0.004)
                .maxIterations(150)
                .rotationAnimation(30, new Complex(0.7885, 0.0), 80)
                .outputPath("julia_rotation.gif")
                .build();

        juliaRotation.saveImage();
    }
}
