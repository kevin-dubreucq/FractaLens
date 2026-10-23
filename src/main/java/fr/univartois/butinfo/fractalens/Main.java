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

        IFractalGenerator mandelbrotImage1 = new FractalGeneratorBuilder()
                .dimensions(600, 800)
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED2)
                .focus(-0.75, 0.0)
                .scale(0.005)
                .maxIterations(250)
                .outputPath("mandelbrot_statique_test.png")
                .build();

        mandelbrotImage1.saveImage();

        IFractalGenerator aetherhelix = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED3) // Ou ton instance de palette Bleu -> Doré
                .focus(0.0, 0.0)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.005)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX1_statique_test.png")
                .build();

        aetherhelix.saveImage();

        IFractalGenerator aetherhelix1 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED3) // Ou ton instance de palette Bleu -> Doré
                .focus(0.392, 0.145)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.00007)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX2_statique_test.png")
                .build();

        aetherhelix1.saveImage();

        IFractalGenerator aetherhelix9 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED6) // Ou ton instance de palette Bleu -> Doré
                .focus(0.392, 0.145)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.00007)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX9_Violet_statique_test.png")
                .build();

        aetherhelix9.saveImage();

        IFractalGenerator aetherhelix10 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED4) // Ou ton instance de palette Bleu -> Doré
                .focus(0.392, 0.145)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.00007)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX10_Violet_statique_test.png")
                .build();

        aetherhelix10.saveImage();

        IFractalGenerator aetherhelix11 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED2) // Ou ton instance de palette Bleu -> Doré
                .focus(0.392, 0.145)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.00007)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX11_Violet_statique_test.png")
                .build();

        aetherhelix11.saveImage();




        IFractalGenerator aetherhelix2 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED3) // Ou ton instance de palette Bleu -> Doré
                .focus(0.3725, 0.091)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.000018)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX3_statique_test.png")
                .build();

        aetherhelix2.saveImage();

        IFractalGenerator aetherhelix3 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED) // Ou ton instance de palette Bleu -> Doré
                .focus(0.3725, 0.091)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.000018)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX4_statique_test.png")
                .build();

        aetherhelix3.saveImage();

        IFractalGenerator aetherhelix4 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED2) // Ou ton instance de palette Bleu -> Doré
                .focus(0.3725, 0.091)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.000018)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX5_statique_test.png")
                .build();

        aetherhelix4.saveImage();

        IFractalGenerator aetherhelix5 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED4) // Ou ton instance de palette Bleu -> Doré
                .focus(0.3725, 0.091)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.000018)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX6_statique_test.png")
                .build();

        aetherhelix5.saveImage();

        IFractalGenerator aetherhelix6 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED5) // Ou ton instance de palette Bleu -> Doré
                .focus(0.3725, 0.091)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.000018)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX7_statique_test.png")
                .build();

        aetherhelix6.saveImage();

        IFractalGenerator aetherhelix7 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.MANDELBROT)
                .palette(ColorPaletteType.FIXED6) // Ou ton instance de palette Bleu -> Doré
                .focus(0.3725, 0.091)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.000018)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(250)
                .outputPath("AETHERHELIX8_statique_test.png")
                .build();

        aetherhelix7.saveImage();


        IFractalGenerator burning_ship1 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.BURNING_SHIP)
                .palette(ColorPaletteType.FIXED3) // Ou ton instance de palette Bleu -> Doré
                .focus(-1.755, -0.03)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.0001)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(64)
                .outputPath("BURNING_SHIP1_statique_test.png")
                .build();

        burning_ship1.saveImage();


        IFractalGenerator burning_ship2 = new FractalGeneratorBuilder()
                .dimensions(800, 800) // Format carré pour bien centrer la structure spirale
                .sequence(ComplexSequenceType.BURNING_SHIP)
                .palette(ColorPaletteType.FIXED4) // Ou ton instance de palette Bleu -> Doré
                .focus(-1.755, -0.03)      // Recentré sur l'origine (0,0) pour voir toute la structure
                .scale(0.0001)         // Échelle adaptée pour englober la fractale entière
                .maxIterations(64)
                .outputPath("BURNING_SHIP2_statique_test.png")
                .build();

        burning_ship2.saveImage();


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




    }
}
