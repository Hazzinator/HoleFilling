package image.matrix;

import image.exception.InvalidMatrixTypeException;
import image.matrix.pixel.IPixelMatrix;
import image.matrix.pixel.PixelMatrix;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Creates implementations of IMatrix
 */
public class MatrixFactory {

    public IPixelMatrix CreatePixelMatrix(Mat underlyingMatrix) {
        if (underlyingMatrix.depth() != CvType.CV_32FC1) {
            throw new InvalidMatrixTypeException("The matrix requires depth: CV_32FC1");
        }
        MatrixElementFactory matrixElementFactory = new MatrixElementFactory();
        IMatrixCache matrixCache = new MatrixCache();
        return new PixelMatrix(underlyingMatrix, matrixCache, matrixElementFactory);
    }
}
