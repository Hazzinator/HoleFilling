package image.matrix;

import org.opencv.core.Mat;

import java.util.HashSet;
import java.util.Set;

/**
 * Adapter over the Mat class in OpenCV
 */
public abstract class Matrix implements IMatrix {

    protected IMatrixCache matrixCache;
    protected Mat underlyingMat;
    protected MatrixElementFactory elementFactory;

    public Matrix(Mat underlyingMatrix, IMatrixCache matrixCache, MatrixElementFactory elementFactory) {
        this.underlyingMat = underlyingMatrix;
        this.matrixCache = matrixCache;
        this.elementFactory = elementFactory;
    }

    /* Object overrides */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix other = (Matrix)o;

        if (other.columns() != this.columns() || other.rows() != this.rows()) {
            return false;
        }
        return underlyingMatEqual(other.underlyingMat);
    }

    @Override
    public int hashCode() {
        int result = 17;
        // NOTE: Don't think this is entirely correct. Mat.equals compares memory addresses not values, therefore
        // it's hashcode implementation probably compares memory locations as well, rather than values.
        // This isn't required for the extent of this project, so will leave as is.
        result = 31 * result + underlyingMat.hashCode();
        result = 31 * result + matrixCache.hashCode();
        return result;
    }

    /* IMatrix implementation */

    @Override
    public int columns() {
        return underlyingMat.cols();
    }

    @Override
    public int rows() {
        return underlyingMat.rows();
    }


    @Override
    public void dump() {
        System.out.println(underlyingMat.dump());
    }

    @Override
    public Mat underlyingMat() {
        return this.underlyingMat;
    }

    /* Private members */

    protected void ThrowIfOutOfRange(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IndexOutOfBoundsException("Cannot have a negative row or column index");
        }

        if (row >= this.rows()) {
            throw new IndexOutOfBoundsException("Row index out of range (0-"+(this.rows()-1)+")");
        } else if (column >= this.columns()) {
            throw new IndexOutOfBoundsException("Column index out of range (0-"+(this.columns()-1)+")");
        }
    }

    protected abstract boolean underlyingMatEqual(Mat other);
}