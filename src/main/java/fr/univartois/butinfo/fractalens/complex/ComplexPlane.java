package fr.univartois.butinfo.fractalens.complex;

public class ComplexPlane implements IComplexPlane{

    private int height;
    private int width;

    public ComplexPlane(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public IComplex asComplex(int row, int column) {
        double a = column - width / 2.0;
        double b = height / 2.0 - row;
        return new Complex(a, b);
    }
}