package image.matrix;

/**
 * Stores instances of {@link IMatrixElement} for all positions
 * in a matrix that have been accessed at least once. This is used
 * to prevent a new IMatrixElement object being created everytime
 * we want to access an element in the matrix for a second+ time.
 */
public interface IMatrixCache {

    boolean contains(int row, int column);

    void set(IMatrixElement element);

    IMatrixElement get(int row, int column);

}
