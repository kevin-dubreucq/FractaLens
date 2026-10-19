package fr.univartois.butinfo.fractalens.complex;

public class ComplexPlane implements IComplexPlane{

    private int height; // Amount of pixels in each row of the image
    private int width; // Amount of pixels in each column of the image

    /**
     *
     * @param height Amount of pixels in each row of the image
     * @param width Amount of pixels in each column of the image
     */
    public ComplexPlane(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IComplex asComplex(int row, int column) {
        double a = column - width / 2.0;
        double b = height / 2.0 - row;
        return new Complex(a, b);
    }
}