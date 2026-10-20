package fr.univartois.butinfo.fractalens.complex;

/**
 * The {@code ScaledComplexPlane} class is a decorator for {@link IComplexPlane}
 * that allows scaling the complex plane by applying a zoom factor.
 *
 * @author Kévin Dubreucq
 * @version 0.1.0
 */
public class ScaledComplexPlane implements IComplexPlane {

    /**
     * The underlying complex plane being decorated.
     */
    private final IComplexPlane plane;

    /**
     * The zoom factor to apply. A value between 0 and 1 enlarges the image.
     */
    private final double zoomFactor;

    /**
     * Creates a new {@code ScaledComplexPlane} decorator.
     *
     * @param plane      The complex plane to decorate.
     * @param zoomFactor The zoom factor to apply (e.g., 0.004).
     */
    public ScaledComplexPlane(IComplexPlane plane, double zoomFactor) {
        this.plane = plane;
        this.zoomFactor = zoomFactor;
    }

    /**
     * Determines the complex number corresponding to a pixel at a given position
     * in the image, scaled by the zoom factor.
     *
     * @param row    The row of the pixel in the image.
     * @param column The column of the pixel in the image.
     * @return The scaled complex number at the given position.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        IComplex baseComplex = plane.asComplex(row, column);
        return baseComplex.multiply(zoomFactor);
    }
}
