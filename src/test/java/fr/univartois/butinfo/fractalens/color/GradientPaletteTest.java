package fr.univartois.butinfo.fractalens.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * The GradientPaletteTest class tests the implementation of {@link GradientPalette}.
 *
 * @author Thomas Barloy
 * @version 1.0
 */
class GradientPaletteTest {

    /**
     * Test method for {@link GradientPalette#getColor(int, int)} when iterations reach or exceed maxIterations.
     */
    @Test
    void testGetColorMaxIterations() {
        IColorPalette palette = new GradientPalette(Color.RED, Color.BLUE);

        Color color1 = palette.getColor(100, 100);
        assertEquals(Color.BLUE, color1);

        Color color2 = palette.getColor(150, 100);
        assertEquals(Color.BLUE, color2);
    }

    /**
     * Test method for {@link GradientPalette#getColor(int, int)} for intermediate iterations.
     */
    @Test
    void testGetColorIntermediate() {
        IColorPalette palette = new GradientPalette(Color.RED, Color.BLUE);

        Color startColor = palette.getColor(0, 100);
        assertEquals(new Color(255, 0, 0), startColor);

        // R = 255 + 0.5 * (0 - 255) = 127
        // G = 0 + 0.5 * (0 - 0) = 0
        // B = 0 + 0.5 * (255 - 0) = 127
        Color middleColor = palette.getColor(50, 100);
        assertEquals(new Color(127, 0, 127), middleColor);
    }
}