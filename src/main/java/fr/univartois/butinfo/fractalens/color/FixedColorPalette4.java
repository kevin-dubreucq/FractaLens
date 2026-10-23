package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The {@code FixedColorPalette3} class implements {@link IColorPalette} to return a color
 * from a fixed palette of precalculated shades transitioning smoothly from Violet to Pink, Blue, and Cyan.
 *
 * @author Kévin Dubreucq
 * @version 0.2.0
 */
public class FixedColorPalette4 implements IColorPalette {

    /**
     * An array of predefined discrete colors transitioning through Violet, Pink, Blue, and Cyan.
     */
    private static final Color[] COLORS = {

            new Color(40, 10, 60),
            new Color(60, 15, 90),
            new Color(85, 20, 130),
            new Color(110, 25, 170),
            new Color(135, 30, 210),
            new Color(160, 40, 240),


            new Color(190, 60, 245),
            new Color(215, 80, 240),
            new Color(240, 100, 225),
            new Color(250, 125, 210),
            new Color(255, 150, 200),
            new Color(255, 175, 210),


            new Color(240, 150, 225),
            new Color(210, 110, 240),
            new Color(170, 75, 250),
            new Color(130, 50, 250),
            new Color(90, 40, 245),
            new Color(50, 45, 240),
            new Color(25, 60, 240),
            new Color(10, 85, 245),


            new Color(0, 115, 250),
            new Color(0, 145, 252),
            new Color(0, 175, 255),
            new Color(15, 205, 255),
            new Color(40, 225, 255),
            new Color(75, 240, 255),
            new Color(120, 250, 255),
            new Color(165, 252, 255)
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return new Color(30, 8, 45);
        }

        int index = iterations % COLORS.length;
        return COLORS[index];
    }
}
