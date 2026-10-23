package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The {@code FixedColorPalette3} class implements {@link IColorPalette} to return a color
 * from an expanded fixed palette transitioning smoothly from Cyan to Blue, Violet, and vibrant Pink.
 *
 * @author Kévin Dubreucq
 * @version 0.3.0
 */
public class FixedColorPalette5 implements IColorPalette {

    /**
     * An array of predefined discrete colors transitioning through Cyan, Blue, Violet, and Pink.
     */
    private static final Color[] COLORS = {
            // --- Phase 1 : Cyan lumineux vers Bleu électrique ---
            new Color(15, 205, 255),
            new Color(0, 175, 255),
            new Color(0, 145, 252),
            new Color(0, 115, 250),
            new Color(10, 85, 245),
            new Color(25, 60, 240),
            new Color(50, 45, 240),

            // --- Phase 2 : Bleu vers Violet profond ---
            new Color(70, 40, 245),
            new Color(95, 35, 248),
            new Color(125, 30, 250),
            new Color(150, 28, 240),
            new Color(175, 25, 220),
            new Color(195, 22, 190),
            new Color(210, 20, 160),

            // --- Phase 3 : Violet vers Rose éclatant / Magenta ---
            new Color(225, 25, 140),
            new Color(240, 35, 130),
            new Color(250, 50, 145),
            new Color(255, 75, 170),
            new Color(255, 100, 195),
            new Color(255, 130, 215),
            new Color(255, 160, 230),

            // --- Phase 4 : Retour doux vers un Violet pastel / Cyan clair ---
            new Color(230, 170, 240),
            new Color(200, 160, 245),
            new Color(165, 150, 250),
            new Color(130, 155, 252),
            new Color(95, 170, 255),
            new Color(60, 190, 255)
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return new Color(15, 5, 25); // Un noir violacé très profond et élégant pour l'intérieur
        }

        int index = iterations % COLORS.length;
        return COLORS[index];
    }
}
