package image.matrix.pixel;

import image.matrix.IMatrixCache;
import image.matrix.IMatrixElement;
import image.matrix.Matrix;
import image.matrix.MatrixElementFactory;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@link Matrix} class. Designed to hold
 */
public class PixelMatrix extends Matrix implements IPixelMatrix {
    public PixelMatrix(Mat mat, IMatrixCache matrixCache, MatrixElementFactory elementFactory) {
        super(mat, matrixCache, elementFactory);
    }

    @Override
    public float getValue(int row, int column) {
        ThrowIfOutOfRange(row, column);
        float[] element = new float[1];
        underlyingMat.get(row, column, element);
        return element[0];
    }

    @Override
    public void setValue(int row, int column, float value) {
        setUnderlyingMatrixValue(row, column, value);
        IPixel pixel = getPixel(row, column);
        pixel.setValue(value);
        matrixCache.set(pixel);
    }

    @Override
    public IPixel getPixel(int row, int column) {
        ThrowIfOutOfRange(row, column);
        if (!matrixCache.contains(row, column)) {
            IMatrixElement pixel = elementFactory.CreatePixel(row, column, getValue(row, column));
            matrixCache.set(pixel);
        }

        return (IPixel)matrixCache.get(row, column);
    }

    @Override
    public void setPixel(IPixel pixel) {
        setUnderlyingMatrixValue(pixel.getRow(), pixel.getColumn(), pixel.getValue());
        matrixCache.set(pixel);
    }

    @Override
    public Set<IPixel> findAllHolePixels() {
        return findAllPixelsWithValue(-1, 0);
    }

    @Override
    public Set<IPixel> findAllPixelsWithValue(float value, float delta) {
        float lowerBound = value - delta;
        float upperBound = value + delta;
        Set<IPixel> elements = new HashSet<>();
        for (int row = 0; row < rows(); row++) {
            for (int column = 0; column < columns(); column++) {
                float valueAtLocation = getValue(row, column);
                if (valueAtLocation >= lowerBound && valueAtLocation <= upperBound) {
                    elements.add(getPixel(row, column));
                }
            }
        }
        return elements;
    }

    /* Protected members */

    protected boolean underlyingMatEqual(Mat other) {
        if (other.depth() != CvType.CV_32FC1) {
            return false;
        }

        float[] otherValue = new float[1];
        float[] thisValue = new float[1];

        for (int row = 0; row < rows(); row++) {
            for (int column = 0; column < columns(); column++) {
                other.get(row, column, otherValue);
                underlyingMat.get(row, column, thisValue);
                if (otherValue[0] != thisValue[0]) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Private members */

    private void setUnderlyingMatrixValue(int row, int column, float value) {
        ThrowIfOutOfRange(row, column);
        float[] element = new float[] { value };
        underlyingMat.put(row, column, element);
    }
}
