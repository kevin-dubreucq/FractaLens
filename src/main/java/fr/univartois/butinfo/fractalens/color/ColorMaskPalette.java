package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The {@code ColorMaskPalette} class is a decorator for {@link IColorPalette}
 * that keeps only selected color components (red, green, blue).
 *
 * @author Lucas Vanstraceele
 * @version 0.1.0
 */
public class ColorMaskPalette implements IColorPalette {

    /**
     * The underlying color palette being decorated.
     */
    private final IColorPalette palette;

    /**
     * Indicates whether the red component should be kept.
     */
    private final boolean keepRed;

    /**
     * Indicates whether the green component should be kept.
     */
    private final boolean keepGreen;

    /**
     * Indicates whether the blue component should be kept.
     */
    private final boolean keepBlue;

    /**
     * Creates a new {@code ColorMaskPalette} decorator.
     *
     * @param palette   The color palette to decorate.
     * @param keepRed   True to keep the red component, false to set it to 0.
     * @param keepGreen True to keep the green component, false to set it to 0.
     * @param keepBlue  True to keep the blue component, false to set it to 0.
     */
    public ColorMaskPalette(IColorPalette palette, boolean keepRed, boolean keepGreen, boolean keepBlue) {
        this.palette = palette;
        this.keepRed = keepRed;
        this.keepGreen = keepGreen;
        this.keepBlue = keepBlue;
    }

    /**
     * Gives the color corresponding to the given number of iterations, applying the RGB mask.
     *
     * @param iterations    The number of iterations performed.
     * @param maxIterations The maximum number of iterations allowed.
     * @return The masked corresponding Color.
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        Color color = palette.getColor(iterations, maxIterations);
        int r = keepRed ? color.getRed() : 0;
        int g = keepGreen ? color.getGreen() : 0;
        int b = keepBlue ? color.getBlue() : 0;
        return new Color(r, g, b);
    }
}
