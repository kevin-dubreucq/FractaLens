package fr.univartois.butinfo.fractalens.color;

import java.awt.*;

/**
 * A {@code GradientPalette} is a color palette that generates colors using a gradient between two colors.
 */
public class GradientPalette implements IColorPalette{

    private Color color1;
    private Color color2;

    /**
     * Creates a new {@code GradientPalette}.
     *
     * @param color1 The first color of the gradient.
     * @param color2 The second color of the gradient.
     */
    public GradientPalette(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    /**
     * Gives the color corresponding to the given number of iterations.
     *
     * @param iterations    The number of iterations performed.
     * @param maxIterations The maximum number of iterations allowed.
     * @return The corresponding Color.
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return color2;
        }
        double p = (double) iterations / maxIterations;
        int r = (int) (color1.getRed() + p * (color2.getRed() - color1.getRed()));
        int g = (int) (color1.getGreen() + p * (color2.getGreen() - color1.getGreen()));
        int b = (int) (color1.getBlue() + p * (color2.getBlue() - color1.getBlue()));
        return new Color(r, g, b);
    }
}
