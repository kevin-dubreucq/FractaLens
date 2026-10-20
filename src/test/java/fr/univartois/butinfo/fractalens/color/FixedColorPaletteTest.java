package fr.univartois.butinfo.fractalens.color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import org.junit.jupiter.api.Test;

/**
 * The {@code FixedColorPaletteTest} class tests the proper functionality
 * of the fixed color palette.
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
class FixedColorPaletteTest {

    /**
     * Tests that the palette returns black when iterations reach or exceed maxIterations.
     */
    @Test
    void testGetColorDivergedOrMax() {
        IColorPalette palette = new FixedColorPalette();

        Color color = palette.getColor(100, 100);
        assertEquals(Color.BLACK, color);

        Color colorAbove = palette.getColor(150, 100);
        assertEquals(Color.BLACK, colorAbove);
    }

    /**
     * Tests that the palette returns a valid non-null color for standard iterations.
     */
    @Test
    void testGetColorValidIterations() {
        IColorPalette palette = new FixedColorPalette();

        Color color = palette.getColor(10, 100);
        assertNotNull(color);
    }
}
