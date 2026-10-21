/*
 * FractaLens - A Fractal Generator Library Developed in Lens.
 * Copyright (c) 2022-2026 Romain Wallon - Université d'Artois.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fr.univartois.butinfo.fractalens.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * The InvertedColorPaletteTest tests the implementation of {@link IColorPalette}
 * provided by {@link InvertedColorPalette}.
 *
 * @author [Ton Nom / Ton Groupe]
 *
 * @version 0.1.0
 */
class InvertedColorPaletteTest {

    /**
     * Creates an inverted color palette decorating a given base palette.
     *
     * @param basePalette The palette to invert.
     * @return The created inverted color palette.
     */
    private static IColorPalette createInvertedPalette(IColorPalette basePalette) {
        return new InvertedColorPalette(basePalette);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.color.InvertedColorPalette#getColor(int, int)}
     * when inverting Black to White.
     */
    @Test
    void testInvertBlackToWhite() {
        IColorPalette blackPalette = (iterations, maxIterations) -> Color.BLACK;
        IColorPalette inverted = createInvertedPalette(blackPalette);

        Color result = inverted.getColor(0, 100);
        assertEquals(255, result.getRed());
        assertEquals(255, result.getGreen());
        assertEquals(255, result.getBlue());
        assertEquals(Color.WHITE, result);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.color.InvertedColorPalette#getColor(int, int)}
     * when inverting White to Black.
     */
    @Test
    void testInvertWhiteToBlack() {
        IColorPalette whitePalette = (iterations, maxIterations) -> Color.WHITE;
        IColorPalette inverted = createInvertedPalette(whitePalette);

        Color result = inverted.getColor(0, 100);
        assertEquals(0, result.getRed());
        assertEquals(0, result.getGreen());
        assertEquals(0, result.getBlue());
        assertEquals(Color.BLACK, result);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.color.InvertedColorPalette#getColor(int, int)}
     * with custom RGB values.
     */
    @Test
    void testInvertCustomColor() {
        IColorPalette customPalette = (iterations, maxIterations) -> new Color(100, 150, 200);
        IColorPalette inverted = createInvertedPalette(customPalette);

        Color result = inverted.getColor(10, 50);

        // 255 - 100 = 155, 255 - 150 = 105, 255 - 200 = 55
        assertEquals(155, result.getRed());
        assertEquals(105, result.getGreen());
        assertEquals(55, result.getBlue());
    }

    /**
     * Test method using {@link GrayscalePalette} as the decorated palette.
     */
    @Test
    void testWithGrayscalePalette() {
        IColorPalette grayscale = new GrayscalePalette();
        IColorPalette inverted = createInvertedPalette(grayscale);

        Color resultEnd = inverted.getColor(100, 100);
        assertEquals(Color.WHITE, resultEnd);
    }
}