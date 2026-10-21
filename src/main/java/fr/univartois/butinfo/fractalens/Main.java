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

        int width1 = 800;
        int height1 = 800;
        int maxIterations1 = 250;
        double zoom1 = 0.001;

        IComplexPlane plane1 = new ComplexPlane(height1, width1);
        IComplexPlane scaledPlane1 = new ScaledComplexPlane(plane1, zoom1);
        IComplexPlane translatedPlane1 = new TranslatedComplexPlane(scaledPlane1, new Complex(0.1, 0.6));

        IColorPalette palette1 = new GradientPalette(Color.YELLOW, Color.RED);

        IFractalGenerator generator1 = new FractalGenerator(
                width1, height1, translatedPlane1,
                (z0, maxIter) -> new JuliaSequence(z0, new Complex(-0.4, 0.6), maxIter),
                palette1, maxIterations1, "julia_demo_1.png"
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
                width2, height2, translatedPlane2,
                (z0, maxIter) -> new JuliaSequence(z0, new Complex(0.285, 0.01), maxIter),
                palette2, maxIterations2, "julia_demo_2.png"
        );
        generator2.generateImage();
        generator2.saveImage();

        // --- DEMO 3
        int width3 = 800;
        int height3 = 600;
        int maxIterations3 = 100;
        double zoom3 = 0.004;

        IComplexPlane plane3 = new ComplexPlane(height3, width3);
        IComplexPlane zoomedPlane3 = new ScaledComplexPlane(plane3, zoom3);
        IColorPalette palette3 = new FixedColorPalette();

        IFractalGenerator generator3 = new FractalGenerator(
                width3, height3, zoomedPlane3,
                (z0, maxIter) -> new JuliaSequence(z0, new Complex(-0.4, 0.6), maxIter),
                palette3, maxIterations3, "julia_fractal_fixed_color.png"
        );
        generator3.generateImage();
        generator3.saveImage();

        // --- DEMO 4
        int width4 = 800;
        int height4 = 600;
        int maxIterations4 = 250;
        double zoom4 = 0.001;

        IComplexPlane plane4 = new ComplexPlane(width4, height4);
        IComplexPlane scaledPlane4 = new ScaledComplexPlane(plane4, zoom4);
        IComplexPlane translatedPlane4 = new TranslatedComplexPlane(scaledPlane4, new Complex(-0.75, 0.0));

        IColorPalette palette4 = new GradientPalette(Color.BLUE, Color.BLACK);

        IFractalGenerator generator4 = new FractalGenerator(
                width4, height4, translatedPlane4,
                (z0, maxIter) -> ComplexSequence.createMandelbrot((Complex) z0, maxIter),
                palette4, maxIterations4,
                "mandelbrot_demo.png"
        );
        generator4.generateImage();
        generator4.saveImage();


        // --- DEMO 5
        int width5 = 800;
        int height5 = 800;
        int maxIterations5 = 64;
        double zoom5 = 0.0001;

        IComplexPlane plane5 = new ComplexPlane(width5, height5);
        IComplexPlane scaledPlane5 = new ScaledComplexPlane(plane5, zoom5);
        IComplexPlane translatedPlane5 = new TranslatedComplexPlane(scaledPlane5, new Complex(-1.755, -0.03));

        IColorPalette palette5 = new GradientPalette(Color.BLACK, Color.YELLOW);

        IFractalGenerator generator5 = new FractalGenerator(
                width5, height5, translatedPlane5,
                (z0, maxIter) -> ComplexSequence.createBurningShip((Complex) z0, maxIter),
                palette5, maxIterations5,
                "burningship_demo.png"
        );
        generator5.generateImage();
        generator5.saveImage();


        // --- DEMO 6
        int width6 = 800;
        int height6 = 800;
        int maxIterations6 = 100;
        double zoom6 = 0.005;

        IComplexPlane plane6 = new ComplexPlane(height6, width6);
        IComplexPlane scaledPlane6 = new ScaledComplexPlane(plane6, zoom6);
        IColorPalette palette6 = new GrayscalePalette();

        IFractalGenerator generator6 = new FractalGenerator(
                width6, height6, scaledPlane6,
                (z0, maxIter) -> new ComplexSequence(
                        z0, maxIter,
                        (prev, initial) -> prev.pow(3).subtract(prev).add(new Complex(-0.2, 0.4))
                ),
                palette6, maxIterations6, "lambda_fractal_demo.png"
        );
        generator6.generateImage();
        generator6.saveImage();
    }
}
