package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The GrayscalePalette class implements IColorPalette to generate grayscale colors.
 */
public class GrayscalePalette implements IColorPalette {

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(int iterations, int maxIterations) {
        if (iterations >= maxIterations) {
            return Color.BLACK;
        }
        double ratio = (double) iterations / maxIterations;
        float gray = (float) (1.0 - ratio);
        return new Color(gray, gray, gray);
    }
}
