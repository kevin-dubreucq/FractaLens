package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The InvertedColorPalette class decorates another {@link IColorPalette}
 * by inverting all of its RGB components.
 *
 * @author Beauvais Maxence
 * @version 0.1.0
 */
public class InvertedColorPalette implements IColorPalette {

    /**
     * The underlying palette being decorated.
     */
    private final IColorPalette palette;

    /**
     * Constructs a new InvertedColorPalette.
     *
     * @param palette The color palette to decorate and invert.
     */
    public InvertedColorPalette(IColorPalette palette) {
        this.palette = palette;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        Color originalColor = palette.getColor(iterations, maxIterations);

        int red = 255 - originalColor.getRed();
        int green = 255 - originalColor.getGreen();
        int blue = 255 - originalColor.getBlue();

        return new Color(red, green, blue);
    }
}