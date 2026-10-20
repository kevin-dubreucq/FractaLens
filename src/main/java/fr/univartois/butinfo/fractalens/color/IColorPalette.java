package fr.univartois.butinfo.fractalens.color;

import java.awt.Color;

/**
 * The IColorPalette interface defines how to choose a color based on the number
 * of iterations performed.
 */
public interface IColorPalette {

    /**
     * Gives the color corresponding to the given number of iterations.
     *
     * @param iterations The number of iterations performed.
     * @param maxIterations The maximum number of iterations allowed.
     * @return The corresponding Color.
     */
    Color getColor(int iterations, int maxIterations);
}
