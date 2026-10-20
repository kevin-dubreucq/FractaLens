package fr.univartois.butinfo.fractalens.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * The GrayscalePaletteTest class tests the implementation of {@link GrayscalePalette}.
 *
 * @author Lucas Vanstraceele
 * @version 1.0
 */
class GrayscalePaletteTest {

    /**
     * Test method for {@link GrayscalePalette#getColor(int, int)} when iterations reach or exceed maxIterations.
     */
    @Test
    void testGetColorMaxIterations() {
        IColorPalette palette = new GrayscalePalette();

        Color color1 = palette.getColor(100, 100);
        assertEquals(Color.BLACK, color1);

        Color color2 = palette.getColor(150, 100);
        assertEquals(Color.BLACK, color2);
    }

    /**
     * Test method for {@link GrayscalePalette#getColor(int, int)} for intermediate iterations.
     */
    @Test
    void testGetColorIntermediate() {
        IColorPalette palette = new GrayscalePalette();

        Color colorWhite = palette.getColor(0, 100);
        assertEquals(new Color(1.0f, 1.0f, 1.0f), colorWhite);

        Color colorGrey = palette.getColor(50, 100);
        assertEquals(new Color(0.5f, 0.5f, 0.5f), colorGrey);
    }
}
