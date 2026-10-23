package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The {@code FixedColorPalette3} class implements {@link IColorPalette} to return a color
 * from a fixed palette of 100 precalculated shades transitioning smoothly from Violet to Pink, Blue, and Cyan.
 *
 * @author Kévin Dubreucq
 * @version 0.4.0
 */
public class FixedColorPalette6 implements IColorPalette {

    /**
     * An array of 100 predefined discrete colors transitioning through Violet, Pink, Blue, and Cyan.
     */
    private static final Color[] COLORS = {
            // --- Phase 1 : Violet profond vers Violet électrique (1 à 25) ---
            new Color(40, 10, 60),
            new Color(45, 11, 68),
            new Color(50, 12, 76),
            new Color(55, 13, 84),
            new Color(60, 15, 90),
            new Color(68, 16, 100),
            new Color(76, 17, 110),
            new Color(85, 20, 130),
            new Color(94, 21, 142),
            new Color(102, 23, 155),
            new Color(110, 25, 170),
            new Color(118, 26, 180),
            new Color(126, 28, 195),
            new Color(135, 30, 210),
            new Color(143, 33, 220),
            new Color(151, 36, 230),
            new Color(160, 40, 240),
            new Color(168, 44, 242),
            new Color(175, 48, 243),
            new Color(182, 54, 244),
            new Color(190, 60, 245),
            new Color(198, 65, 243),
            new Color(206, 72, 241),
            new Color(215, 80, 240),
            new Color(222, 87, 235),

            // --- Phase 2 : Violet clair vers Rose éclatant (26 à 50) ---
            new Color(228, 93, 230),
            new Color(234, 96, 227),
            new Color(240, 100, 225),
            new Color(243, 107, 219),
            new Color(246, 114, 214),
            new Color(250, 125, 210),
            new Color(252, 133, 206),
            new Color(254, 141, 203),
            new Color(255, 150, 200),
            new Color(255, 156, 202),
            new Color(255, 162, 205),
            new Color(255, 175, 210),
            new Color(252, 168, 213),
            new Color(248, 160, 217),
            new Color(240, 150, 225),
            new Color(230, 140, 230),
            new Color(220, 125, 235),
            new Color(210, 110, 240),
            new Color(198, 98, 245),
            new Color(184, 86, 247),
            new Color(170, 75, 250),
            new Color(155, 61, 250),
            new Color(142, 55, 250),
            new Color(130, 50, 250),
            new Color(115, 45, 248),

            // --- Phase 3 : Rose vers Bleu intense (51 à 75) ---
            new Color(102, 42, 246),
            new Color(90, 40, 245),
            new Color(75, 41, 243),
            new Color(62, 43, 241),
            new Color(50, 45, 240),
            new Color(40, 50, 240),
            new Color(32, 55, 240),
            new Color(25, 60, 240),
            new Color(18, 70, 243),
            new Color(14, 78, 244),
            new Color(10, 85, 245),
            new Color(8, 95, 247),
            new Color(5, 105, 248),
            new Color(0, 115, 250),
            new Color(0, 123, 251),
            new Color(0, 132, 251),
            new Color(0, 145, 252),
            new Color(0, 152, 253),
            new Color(0, 164, 254),
            new Color(0, 175, 255),
            new Color(5, 184, 255),
            new Color(10, 195, 255),
            new Color(15, 205, 255),
            new Color(25, 215, 255),
            new Color(32, 220, 255),

            // --- Phase 4 : Bleu électrique vers Cyan lumineux (76 à 100) ---
            new Color(40, 225, 255),
            new Color(48, 228, 255),
            new Color(58, 232, 255),
            new Color(75, 240, 255),
            new Color(88, 243, 255),
            new Color(104, 247, 255),
            new Color(120, 250, 255),
            new Color(132, 251, 255),
            new Color(148, 252, 255),
            new Color(165, 252, 255),
            new Color(150, 245, 250),
            new Color(135, 235, 245),
            new Color(120, 220, 240),
            new Color(100, 205, 235),
            new Color(80, 190, 225),
            new Color(65, 170, 215),
            new Color(50, 150, 205),
            new Color(40, 130, 190),
            new Color(30, 110, 175),
            new Color(22, 90, 155),
            new Color(16, 75, 135),
            new Color(12, 60, 115),
            new Color(8, 48, 95),
            new Color(6, 35, 75),
            new Color(30, 8, 45)       // Bouclage final vers le violet sombre
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return new Color(30, 8, 45); // Violet très sombre pour l'intérieur / non-convergence
        }

        int index = iterations % COLORS.length;
        return COLORS[index];
    }
}
