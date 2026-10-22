package fr.univartois.butinfo.fractalens.color;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ColorPaletteType} enum.
 *
 * @author University of Artois
 * @version 1.0
 */
class ColorPaletteTypeTest {

    @Test
    @DisplayName("Test that all palette types return a non-null IColorPalette instance")
    void testAllPalettesNotNull() {
        for (ColorPaletteType type : ColorPaletteType.values()) {
            assertNotNull(type.getPalette(), "The palette for " + type.name() + " should not be null.");
        }
    }

    @Test
    @DisplayName("Test GRAYSCALE palette association")
    void testGrayscalePalette() {
        IColorPalette palette = ColorPaletteType.GRAYSCALE.getPalette();
        assertInstanceOf(GrayscalePalette.class, palette);
    }

    @Test
    @DisplayName("Test GRADIENT_YELLOW_RED palette association")
    void testGradientYellowRedPalette() {
        IColorPalette palette = ColorPaletteType.GRADIENT_YELLOW_RED.getPalette();
        assertInstanceOf(GradientPalette.class, palette);
    }

    @Test
    @DisplayName("Test GRADIENT_BLUE_BLACK palette association")
    void testGradientBlueBlackPalette() {
        IColorPalette palette = ColorPaletteType.GRADIENT_BLUE_BLACK.getPalette();
        assertInstanceOf(GradientPalette.class, palette);
    }

    @Test
    @DisplayName("Test GRADIENT_BLACK_YELLOW palette association")
    void testGradientBlackYellowPalette() {
        IColorPalette palette = ColorPaletteType.GRADIENT_BLACK_YELLOW.getPalette();
        assertInstanceOf(GradientPalette.class, palette);
    }

    @Test
    @DisplayName("Test INVERTED_GRAYSCALE palette association")
    void testInvertedGrayscalePalette() {
        IColorPalette palette = ColorPaletteType.INVERTED_GRAYSCALE.getPalette();
        assertInstanceOf(InvertedColorPalette.class, palette);
    }

    @Test
    @DisplayName("Test FIXED palette association")
    void testFixedPalette() {
        IColorPalette palette = ColorPaletteType.FIXED.getPalette();
        assertInstanceOf(FixedColorPalette.class, palette);
    }
}
