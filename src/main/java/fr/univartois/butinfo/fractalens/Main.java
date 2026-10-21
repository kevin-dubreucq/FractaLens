package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.color.*;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.ScaledComplexPlane;
import fr.univartois.butinfo.fractalens.complex.TranslatedComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;

import java.awt.Color;

/**
 * La classe Main permet d'exécuter l'application et de générer des images de fractales
 * pour le Sprint 3 et le Sprint 4 avec les paramètres ajustés.
 *
 * @author Lucas Vanstraceele
 * @version 4.2
 */
public class Main {

    public static void main(String[] args) {

        FractalGenerator generator1 = new FractalGeneratorBuilder()
                .dimensions(800, 800)
                .zoom(0.001)
                .focus(0.1, 0.6)
                .maxIterations(250)
                .sequence(ComplexSequenceType.JULIA_DEFAULT)
                .palette(ColorPaletteType.GRADIENT_YELLOW_RED)
                .outputPath("julia_builder_1.png")
                .build();

        generator1.generateImage();
        generator1.saveImage();

        FractalGenerator generator2 = new FractalGeneratorBuilder()
                .dimensions(800, 600)
                .zoom(0.0047)
                .focus(-0.75, 0.0)
                .maxIterations(250)
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.GRADIENT_BLUE_BLACK)
                .outputPath("mandelbrot_builder.png")
                .build();

        generator2.generateImage();
        generator2.saveImage();

        FractalGenerator generator3 = new FractalGeneratorBuilder()
                .dimensions(800, 800)
                .zoom(0.0001)
                .focus(-1.755, -0.03)
                .maxIterations(64)
                .sequence(ComplexSequenceType.BURNING_SHIP)
                .palette(ColorPaletteType.GRADIENT_BLACK_YELLOW)
                .outputPath("burningship_builder.png")
                .build();

        generator3.generateImage();
        generator3.saveImage();

    }
}
