package image.colour;

import image.matrix.pixel.IPixel;

import java.util.Collection;

/**
 * Calculates a new colour value for an instance of {@link IPixel}
 */
public interface IColourCalculator {

    /**
     * Given a pixel and a set of other pixels, will perform a function to return a new float value
     * based on some weighting between the chosen pixel and the pixels in the other set.
     * @param pixel Pixel to have it's colour changed
     * @param otherPixels Pixels that will be weighted/analysed to determine the new colour of the chosen pixel
     * @return New colour value
     */
    float CalculateWeightedColourFromPixels(IPixel pixel, Collection<IPixel> otherPixels);
}
