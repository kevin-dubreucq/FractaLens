package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.ScaledComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import fr.univartois.butinfo.fractalens.color.GrayscalePalette;
import fr.univartois.butinfo.fractalens.color.IColorPalette;

/**
 * La classe Main permet d'exécuter l'application et de générer une image de fractale.
 *
 * @author Lucas Vanstraceele
 * @version 1.0
 */
public class Main {

    /**
     * Point d'entrée de l'application.
     *
     */
    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        int maxIterations = 100;
        double zoom = 0.004;

        IComplexPlane plane = new ComplexPlane(height, width);
        IComplexPlane zoomedPlane = new ScaledComplexPlane(plane, zoom);

        IColorPalette palette = new GrayscalePalette();

        IFractalGenerator generator = new FractalGenerator(
                width,
                height,
                zoomedPlane,
                (z0, maxIter) -> new JuliaSequence(z0, new Complex(-0.4, 0.6), maxIter),
                palette,
                maxIterations,
                "julia_fractal.png"
        );

        generator.generateImage();
        generator.saveImage();
    }
}
