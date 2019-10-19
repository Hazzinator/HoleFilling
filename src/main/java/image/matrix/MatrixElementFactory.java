package image.matrix;

import image.matrix.pixel.Pixel;

/**
 * Creates an concrete instances of {@link MatrixElement} and returns them
 * as an {@link IMatrixElement}
 */
public class MatrixElementFactory {

    public IMatrixElement CreatePixel(int row, int column, float value) {
        return new Pixel(row, column, value);
    }
}
