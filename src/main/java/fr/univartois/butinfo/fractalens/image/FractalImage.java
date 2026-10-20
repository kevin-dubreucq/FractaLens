package fr.univartois.butinfo.fractalens.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The FractalImage class is an adapter that encapsulates a {@link BufferedImage}.
 * It provides the concrete implementation to draw and save the fractal image.
 *
 * @author Beauvais Maxence
 */
public class FractalImage implements IFractalImage {

    /**
     * The underlying image on which the fractal is drawn.
     */
    private final BufferedImage image;

    /**
     * Constructs a new FractalImage with the specified dimensions.
     *
     * @param image The underlying image on which the fractal is drawn.
     */
    public FractalImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(int row, int column, Color color) {
        image.setRGB(column, row, color.getRGB());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAs(String path) throws IOException {
        File file = new File(path);
        ImageIO.write(image, path.substring(path.lastIndexOf(".") + 1), file);
    }
}