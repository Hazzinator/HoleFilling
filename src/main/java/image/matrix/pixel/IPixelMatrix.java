package image.matrix.pixel;

import image.matrix.IMatrix;
import image.matrix.IMatrixElement;

import java.util.Set;

/**
 * Contains image processing specific terminology and functions.
 * */
public interface IPixelMatrix extends IMatrix {

    /**
     * Sets the value for a specified element in the matrix. Will also update or create the value in the cache.
     */
    void setValue(int row, int column, float value);

    /**
     * Gets the float value for an element at the specified row and column in the matrix
     */
    float getValue(int row, int column);

    /**
     * Gets an {@link IMatrixElement} from the cache if one exists. If one doesn't,
     * then creates it and returns it.
     */
    IPixel getPixel(int row, int column);

    /**
     * Given a pixel, will set the value in the cache to be equal to the passed in value, as well as updating the
     * underlying matrix
     * @param element
     */
    void setPixel(IPixel element);

    Set<IPixel> findAllHolePixels();

    Set<IPixel> findAllPixelsWithValue(float value, float delta);
}
