package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The {@code FixedColorPalette2} class implements {@link IColorPalette} to return a color
 * from a fixed palette or a dynamic gradient when max iterations are reached.
 *
 * @author Kévin Dubreucq
 * @version 0.3.0
 */
public class FixedColorPalette2 implements IColorPalette {

    /**
     * An array of 100 predefined discrete colors used for the fixed palette.
     */
    private static final Color[] COLORS = {
            new Color(10, 20, 78),
            new Color(10, 25, 92),
            new Color(10, 30, 106),
            new Color(10, 35, 120),
            new Color(10, 40, 134),
            new Color(10, 58, 176),
            new Color(10, 64, 190),
            new Color(10, 70, 204),
            new Color(15, 100, 186),
            new Color(15, 110, 180),
            new Color(15, 150, 150),
            new Color(20, 155, 137),
            new Color(24, 160, 125),
            new Color(28, 165, 112),
            new Color(32, 170, 100),
            new Color(36, 175, 88),
            new Color(40, 186, 65),
            new Color(40, 198, 45),
            new Color(120, 214, 40),
            new Color(144, 218, 40),
            new Color(168, 222, 40),
            new Color(192, 226, 40),
            new Color(216, 230, 40),
            new Color(240, 180, 27),
            new Color(240, 171, 26),
            new Color(240, 162, 24),
            new Color(240, 126, 20),
            new Color(240, 117, 19),
            new Color(240, 108, 18),
            new Color(240, 99, 16),
            new Color(240, 90, 15),
            new Color(240, 83, 16),
            new Color(240, 77, 18),
            new Color(240, 70, 20),
            new Color(237, 63, 22),
            new Color(233, 56, 24),
            new Color(230, 50, 25),
            new Color(227, 46, 26),
            new Color(223, 43, 28),
            new Color(220, 40, 30),
            new Color(210, 35, 30),
            new Color(200, 30, 40),
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return Color.BLACK;
        }


        int offset = 15;
        int index = (iterations + offset) % COLORS.length;

        return COLORS[index];
    }
}
