package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.ScaledComplexPlane;
import fr.univartois.butinfo.fractalens.complex.TranslatedComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import fr.univartois.butinfo.fractalens.color.GrayscalePalette;
import fr.univartois.butinfo.fractalens.color.IColorPalette;
import fr.univartois.butinfo.fractalens.color.GradientPalette;
import fr.univartois.butinfo.fractalens.color.InvertedColorPalette;

import java.awt.Color;

/**
 * La classe Main permet d'exécuter l'application et de générer des images de fractales
 * pour le Sprint 3 (avec gestion du zoom, de la translation et de nouvelles palettes).
 *
 * @author Lucas Vanstraceele
 * @version 2.0
 */
public class Main {

    /**
     * Point d'entrée de l'application.
     *
     *
     */
    public static void main(String[] args) {
        // --- DEMO 1
        int width1 = 800;
        int height1 = 800;
        int maxIterations1 = 250;
        double zoom1 = 0.001;

        IComplexPlane plane1 = new ComplexPlane(height1, width1);
        IComplexPlane scaledPlane1 = new ScaledComplexPlane(plane1, zoom1);
        IComplexPlane translatedPlane1 = new TranslatedComplexPlane(scaledPlane1, new Complex(0.1, 0.6));

        IColorPalette palette1 = new GradientPalette(Color.YELLOW, Color.RED);

        IFractalGenerator generator1 = new FractalGenerator(
                width1,
                height1,
                translatedPlane1,
                (z0, maxIter) -> new JuliaSequence(z0, new Complex(-0.4, 0.6), maxIter),
                palette1,
                maxIterations1,
                "julia_demo_1.png"
        );

        generator1.generateImage();
        generator1.saveImage();


        // --- DEMO 2
        int width2 = 800;
        int height2 = 800;
        int maxIterations2 = 250;
        double zoom2 = 0.001;

        IComplexPlane plane2 = new ComplexPlane(height2, width2);
        IComplexPlane scaledPlane2 = new ScaledComplexPlane(plane2, zoom2);
        IComplexPlane translatedPlane2 = new TranslatedComplexPlane(scaledPlane2, new Complex(-0.47, -0.21));

        IColorPalette basePalette = new GrayscalePalette();
        IColorPalette palette2 = new InvertedColorPalette(basePalette);

        IFractalGenerator generator2 = new FractalGenerator(
                width2,
                height2,
                translatedPlane2,
                (z0, maxIter) -> new JuliaSequence(z0, new Complex(0.285, 0.01), maxIter),
                palette2,
                maxIterations2,
                "julia_demo_2.png"
        );

        generator2.generateImage();
        generator2.saveImage();
    }
}
