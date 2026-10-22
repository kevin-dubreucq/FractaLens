package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.color.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;

public class Main {

    public static void main(String[] args) {

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
