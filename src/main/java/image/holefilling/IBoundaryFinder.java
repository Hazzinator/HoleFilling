package image.holefilling;

import image.connectivity.IConnectivityType;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.Set;

/**
 * Object that can find the boundary of a hole in an image.
 */
public interface IBoundaryFinder {
    /**
     * Finds the hole pixels, then finds the boundary from them
     */
    Set<IPixel> findBoundaryElements(IPixelMatrix matrix, IConnectivityType connectivityType);

    /**
     * Given a set of hole pixels and a matrix from which they come from, will find the set of non-hole pixels
     * that are connected to the outer layer of the hole pixels (i.e. the boundary)
     */
    Set<IPixel> findBoundaryElements(IPixelMatrix matrix, Set<IPixel> holeElements, IConnectivityType connectivityType);

    /**
     * Finds the set of pixels that are holes, then filters them and returns the ones that are connected
     * to the boundary.
     * Used for O(n) time hole filling
     */
    Set<IPixel> findBoundaryConnectedHolePixels(IPixelMatrix matrix, IConnectivityType connectivityType);

    /**
     * Given a set of hole elements, filters them and returns the ones that are connected to the boundary.
     * Used if you have already found the hole elements.
     * Used for O(n) time hole filling
     */
    Set<IPixel> findBoundaryConnectedHolePixels(IPixelMatrix matrix, Set<IPixel> holeElements, IConnectivityType connectivityType);
}
