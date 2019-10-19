package image.matrix;

/**
 * An element that can exist within a matrix
 */
public interface IMatrixElement {

    int getRow();

    int getColumn();

    /**
     * Calculates the Euclidean distance between this element and another element
     */
    double distanceFrom(IMatrixElement other);
}
