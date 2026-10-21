package fr.univartois.butinfo.fractalens.color;

import java.awt.*;

public enum ColorPaletteType {
    GRAYSCALE(new GrayscalePalette()),
    GRADIENT_YELLOW_RED(new GradientPalette(Color.YELLOW, Color.RED)),
    GRADIENT_BLUE_BLACK(new GradientPalette(Color.BLUE, Color.BLACK)),
    GRADIENT_BLACK_YELLOW(new GradientPalette(Color.BLACK, Color.YELLOW)),
    INVERTED_GRAYSCALE(new InvertedColorPalette(new GrayscalePalette())),
    FIXED(new FixedColorPalette());



    private final IColorPalette palette;

    ColorPaletteType(IColorPalette palette) {
        this.palette = palette;
    }
    public IColorPalette getPalette() {
        return palette;
    }
}

