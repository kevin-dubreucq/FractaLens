package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The {@code FixedColorPalette} class implements {@link IColorPalette} to return a color
 * from a fixed palette of 100 precalculated shades transitioning from deep navy blue
 * to electric blue and glowing gold based on custom color definitions.
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
public class FixedColorPalette3 implements IColorPalette {

    /**
     * An array of 100 predefined discrete colors transitioning from deep blue to gold.
     */
    private static final Color[] COLORS = {
            // --- Bleu nuit très sombre vers bleu électrique (tes bases) ---
            new Color(5, 12, 35),     // 1 - Bleu nuit très sombre
            new Color(7, 18, 50),
            new Color(10, 25, 70),
            new Color(12, 35, 95),
            new Color(10, 48, 125),
            new Color(8, 65, 155),
            new Color(5, 85, 185),
            new Color(2, 105, 210),
            new Color(0, 125, 230),
            new Color(0, 145, 245),   // 10 - Bleu électrique vif
            new Color(0, 160, 250),

            // --- Transition douce du bleu vers le cyan/turquoise lumineux ---
            new Color(10, 175, 245),
            new Color(20, 190, 230),
            new Color(35, 205, 210),
            new Color(50, 215, 185),
            new Color(70, 220, 155),
            new Color(95, 225, 125),
            new Color(120, 228, 95),
            new Color(150, 230, 70),

            // --- Transition vers les tons chauds et ambrés ---
            new Color(180, 225, 50),
            new Color(210, 215, 35),
            new Color(235, 200, 30),
            new Color(245, 185, 31),

            // --- Insertion et déclinaisons de tes nuances dorées précises ---
            new Color(218, 165, 32),  // Doré chaud / ambré (ton input)
            new Color(213, 170, 42),
            new Color(207, 181, 59),  // Vieux doré / or ancien (ton input)
            new Color(215, 192, 45),
            new Color(235, 207, 22),
            new Color(255, 223, 0),   // Doré clair / éclatant (ton input)
            new Color(255, 226, 15),
            new Color(255, 230, 30),
            new Color(255, 234, 45),
            new Color(255, 238, 60),
            new Color(255, 242, 75),
            new Color(255, 245, 90),
            new Color(255, 248, 110),
            new Color(255, 250, 130),
            new Color(255, 252, 155),
            new Color(255, 255, 180), // 40

            // Remplissage progressif pour atteindre 100 nuances fluides en bouclant sur la lumière dorée/blanche et les reflets
            new Color(255, 254, 175),
            new Color(255, 252, 160),
            new Color(255, 250, 145),
            new Color(255, 246, 130),
            new Color(255, 240, 115),
            new Color(255, 234, 100),
            new Color(255, 225, 85),
            new Color(255, 215, 70),
            new Color(255, 200, 55),
            new Color(255, 185, 45),  // 50
            new Color(245, 170, 38),
            new Color(235, 160, 33),
            new Color(225, 155, 32),
            new Color(220, 160, 35),
            new Color(215, 170, 45),
            new Color(210, 178, 55),
            new Color(208, 180, 58),
            new Color(212, 185, 50),
            new Color(220, 195, 40),
            new Color(230, 205, 30),  // 60
            new Color(240, 212, 20),
            new Color(248, 218, 10),
            new Color(255, 223, 0),
            new Color(250, 215, 10),
            new Color(240, 200, 20),
            new Color(225, 185, 30),
            new Color(218, 165, 32),
            new Color(207, 181, 59),
            new Color(195, 175, 75),
            new Color(180, 165, 90),  // 70
            new Color(160, 150, 110),
            new Color(135, 130, 130),
            new Color(110, 110, 150),
            new Color(90, 95, 170),
            new Color(70, 80, 190),
            new Color(50, 65, 210),
            new Color(35, 50, 225),
            new Color(20, 35, 240),
            new Color(10, 25, 248),
            new Color(0, 20, 250),    // 80
            new Color(0, 35, 235),
            new Color(0, 55, 220),
            new Color(2, 75, 205),
            new Color(5, 95, 190),
            new Color(7, 115, 175),
            new Color(8, 135, 160),
            new Color(8, 150, 140),
            new Color(8, 145, 115),
            new Color(8, 135, 90),
            new Color(8, 120, 70),    // 90
            new Color(7, 100, 60),
            new Color(6, 80, 50),
            new Color(5, 65, 45),
            new Color(5, 50, 40),
            new Color(6, 40, 42),
            new Color(7, 30, 45),
            new Color(6, 22, 42),
            new Color(5, 17, 39),
            new Color(5, 14, 37),
            new Color(5, 12, 35)      // 100 - Retour au bleu nuit profond initial
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return new Color(5, 10, 35);
        }

        int index = iterations % COLORS.length;
        return COLORS[index];
    }
}
