package image.connectivity;

import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.List;

/**
 * Finds the connected pixels of a pixel
 */
public interface IConnectivityType {
    /**
     * Finds the connected pixels from an {@link IPixel} instance in an {@link IPixelMatrix}
     * @param matrix Matrix to look for connected pixels in
     * @param pixel Pixel to look for neighbours from
     * @return List of connected pixels
     */
    List<IPixel> findConnectedPixels(IPixelMatrix matrix, IPixel pixel);

    /**
     * Finds the connected pixels from an {@link IPixel} instance in an {@link IPixelMatrix}
     * @param matrix Matrix to look for connected pixels in
     * @return List of connected pixels
     */
    List<IPixel> findConnectedPixels(IPixelMatrix matrix, int row, int column);

    /**
     * Finds the connected pixels that aren't hole pixels
     * @param matrix Matrix to look for connected pixels in
     * @param pixel Pixel to look for connected pixels around
     */
    List<IPixel> findNonHoleConnectedPixels(IPixelMatrix matrix, IPixel pixel);

    /**
     * Finds the connected pixels that are hole pixels
     * @param matrix Matrix to look for connected pixels in
     * @param pixel Pixel to look for connected pixels around
     */
    List<IPixel> findConnectedHolePixels(IPixelMatrix matrix, IPixel pixel);
}
