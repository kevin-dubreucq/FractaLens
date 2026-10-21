package fr.univartois.butinfo.fractalens.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import org.junit.jupiter.api.Test;

/**
 * The {@code ColorMaskPaletteTest} class tests the proper functionality
 * of the color mask decorator on color palettes.
 *
 * @author Lucas Vanstraceele
 * @version 0.1.0
 */
class ColorMaskPaletteTest {

    /**
     * Tests that the {@link ColorMaskPalette} correctly filters the color components
     * based on the given boolean flags.
     */
    @Test
    void testColorMask() {
        // Creates a dummy color palette implementation for testing purposes
        IColorPalette dummyPalette = new IColorPalette() {
            @Override
            public Color getColor(int iterations, int maxIterations) {
                return new Color(100, 150, 200);
            }
        };

        // Keeps Red and Blue, masks out Green (true, false, true)
        IColorPalette maskedPalette = new ColorMaskPalette(dummyPalette, true, false, true);

        // Retrieves the color for given iterations
        Color result = maskedPalette.getColor(10, 100);

        // Verifies the masked RGB components
        assertEquals(100, result.getRed());
        assertEquals(0, result.getGreen());
        assertEquals(200, result.getBlue());
    }
}
