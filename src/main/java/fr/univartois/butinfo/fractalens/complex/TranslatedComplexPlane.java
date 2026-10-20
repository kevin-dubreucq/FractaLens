package fr.univartois.butinfo.fractalens.complex;

/**
 * The {@code TranslatedComplexPlane} class is a decorator for {@link IComplexPlane}
 * that allows translating the complex plane by adding a complex center to each point.
 *
 * @author Lucas Vanstraceele
 * @version 0.1.0
 */
public class TranslatedComplexPlane implements IComplexPlane {

    /**
     * The underlying complex plane being decorated.
     */
    private final IComplexPlane plane;

    /**
     * The complex number representing the center of the plane for translation.
     */
    private final IComplex center;

    /**
     * Creates a new {@code TranslatedComplexPlane} decorator.
     *
     * @param plane  The complex plane to decorate.
     * @param center The complex number to add as a translation offset.
     */
    public TranslatedComplexPlane(IComplexPlane plane, IComplex center) {
        this.plane = plane;
        this.center = center;
    }

    /**
     * Determines the complex number corresponding to a pixel at a given position
     * in the image, translated by the center complex number.
     *
     * @param row    The row of the pixel in the image.
     * @param column The column of the pixel in the image.
     * @return The translated complex number at the given position.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        IComplex baseComplex = plane.asComplex(row, column);
        return baseComplex.add(center);
    }
}
