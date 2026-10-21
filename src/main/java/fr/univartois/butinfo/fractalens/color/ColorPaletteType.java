package fr.univartois.butinfo.fractalens.color;

import java.awt.*;

/**
 * The {@code ColorPaletteType} enum lists the various types of color palettes
 * available for fractal rendering. Each type is associated with a concrete
 * implementation of the {@link IColorPalette} interface.
 *
 * @author University of Artois
 * @version 1.0
 * @see IColorPalette
 */
public enum ColorPaletteType {

    /**
     * Grayscale color palette, ranging from black to white.
     */
    GRAYSCALE(new GrayscalePalette()),

    /**
     * Progressive gradient color palette transitioning from yellow to red.
     */
    GRADIENT_YELLOW_RED(new GradientPalette(Color.YELLOW, Color.RED)),

    /**
     * Progressive gradient color palette transitioning from blue to black.
     */
    GRADIENT_BLUE_BLACK(new GradientPalette(Color.BLUE, Color.BLACK)),

    /**
     * Progressive gradient color palette transitioning from black to yellow.
     */
    GRADIENT_BLACK_YELLOW(new GradientPalette(Color.BLACK, Color.YELLOW)),

    /**
     * Inverted version of the grayscale palette, ranging from white to black.
     */
    INVERTED_GRAYSCALE(new InvertedColorPalette(new GrayscalePalette())),

    /**
     * Fixed or custom color palette.
     */
    FIXED(new FixedColorPalette());

    /**
     * The concrete instance of the color palette associated with this type.
     */
    private final IColorPalette palette;

    /**
     * Constructs a color palette type with the specified palette implementation.
     *
     * @param palette The {@link IColorPalette} instance corresponding to this type.
     */
    ColorPaletteType(IColorPalette palette) {
        this.palette = palette;
    }

    /**
     * Returns the color palette instance associated with this type.
     *
     * @return The encapsulated {@link IColorPalette} object.
     */
    public IColorPalette getPalette() {
        return palette;
    }
}
