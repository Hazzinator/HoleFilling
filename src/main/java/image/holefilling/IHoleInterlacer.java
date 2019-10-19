package image.holefilling;

import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.Set;

/**
 * Interface for an object that cuts a hole out of an image
 */
public interface IHoleInterlacer {
    /**
     * Given a matrix and a mask matrix, will carve out a hole in the original matrix for all elements that
     * have a value greater than 1 in the mask.
     * @param originalMatrix Matrix to cut hole into
     * @param holeMask Matrix of elements to cut out.
     * @return The set of pixels that make up the boundary
     */
    Set<IPixel> interlace(IPixelMatrix originalMatrix, IPixelMatrix holeMask);
}
