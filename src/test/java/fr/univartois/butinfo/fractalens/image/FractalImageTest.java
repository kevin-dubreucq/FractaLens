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

package fr.univartois.butinfo.fractalens.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

/**
 * The FractalImageTest tests the implementation of {@link IFractalImage}
 * provided by {@link FractalImage}.
 *
 * @author [Ton Nom / Ton Groupe]
 *
 * @version 0.1.0
 */
class FractalImageTest {

    /**
     * Creates a new fractal image.
     *
     * @param width The width of the image.
     * @param height The height of the image.
     *
     * @return The created fractal image.
     */
    private static IFractalImage createFractalImage(int width, int height) {
        return new FractalImage(width, height);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#getHeight()}.
     */
    @Test
    void testGetHeight() {
        IFractalImage image1 = createFractalImage(800, 600);
        assertEquals(600, image1.getHeight());

        IFractalImage image2 = createFractalImage(100, 100);
        assertEquals(100, image2.getHeight());
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#getWidth()}.
     */
    @Test
    void testGetWidth() {
        IFractalImage image1 = createFractalImage(800, 600);
        assertEquals(800, image1.getWidth());

        IFractalImage image2 = createFractalImage(100, 100);
        assertEquals(100, image2.getWidth());
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#setColor(int, int, java.awt.Color)}
     * and {@link fr.univartois.butinfo.fractalens.image.IFractalImage#saveAs(String)}.
     *
     * @throws IOException If an I/O error occurs while creating or reading the temporary file.
     */
    @Test
    void testSetColorAndSaveAs() throws IOException {
        IFractalImage image = createFractalImage(10, 10);

        image.setColor(2, 5, Color.RED);
        image.setColor(8, 1, Color.BLUE);

        File tempFile = File.createTempFile("fractal-test", ".png");
        tempFile.deleteOnExit();

        image.saveAs(tempFile.getAbsolutePath());

        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);

        BufferedImage savedImage = ImageIO.read(tempFile);

        assertEquals(10, savedImage.getWidth());
        assertEquals(10, savedImage.getHeight());

        assertEquals(Color.RED.getRGB(), savedImage.getRGB(5, 2));
        assertEquals(Color.BLUE.getRGB(), savedImage.getRGB(1, 8));

        assertEquals(Color.BLACK.getRGB(), savedImage.getRGB(0, 0));
    }
}