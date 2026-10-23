package fr.univartois.butinfo.fractalens.generator;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import fr.univartois.butinfo.fractalens.image.FractalImage;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates an animated fractal GIF.
 */
public class AnimatedFractalGenerator implements IFractalGenerator {

    private final List<IFractalGenerator> generators;
    private final String outputPath;
    private final int delayMs;

    /**
     * Creates a new AnimatedFractalGenerator.
     *
     * @param generators The list of sub-generators to produce each frame.
     * @param outputPath The file path where the animated GIF will be saved.
     * @param frameDelayMs Delay between frames in milliseconds.
     */
    public AnimatedFractalGenerator(List<IFractalGenerator> generators, String outputPath, int frameDelayMs) {
        this.generators = new ArrayList<>(generators);
        this.outputPath = outputPath;
        this.delayMs = frameDelayMs;
    }

    /**
     * Generates all frame images sequentially.
     * Returns the image of the last generated frame.
     *
     * @return The last generated IFractalImage frame.
     */
    @Override
    public IFractalImage generateImage() {
        IFractalImage lastImage = null;
        for (IFractalGenerator generator : generators) {
            lastImage = generator.generateImage();
        }
        return lastImage;
    }

    /**
     * Delegates pixel color calculation to the first frame generator.
     */
    @Override
    public Color computePixelColor(int row, int column) {
        if (generators.isEmpty()) {
            return Color.BLACK;
        }
        return generators.getFirst().computePixelColor(row, column);
    }

    /**
     * Saves all frames as an animated GIF file at the specified output path.
     */
    @Override
    public void saveImage() {
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setDelay(delayMs);

        encoder.setRepeat(0);
        encoder.setQuality(10);

        for (IFractalGenerator generator : generators) {
            IFractalImage frameImage = generator.generateImage();
            encoder.addFrame(((FractalImage) frameImage).getImage());
        }

        encoder.finish();
    }
}