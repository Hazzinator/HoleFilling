package image.colour;

import image.matrix.pixel.IPixel;
import image.weighting.IWeightingFunction;

import java.util.Collection;

public class DefaultColourCalculator implements IColourCalculator {

    private IWeightingFunction weightingFunction;

    public DefaultColourCalculator(IWeightingFunction weightingFunction) {
        this.weightingFunction = weightingFunction;
    }

    /**
     * Implements the inverse distance weighting algorithm to find a weighted colour based on the distance
     * each of the surrounding pixels is from the current one.
     * @param pixel Pixel to have it's colour changed
     * @param otherPixels Pixels that will be weighted/analysed to determine the new colour of the chosen pixel
     */
    @Override
    public float CalculateWeightedColourFromPixels(IPixel pixel, Collection<IPixel> otherPixels) {
        if (otherPixels.isEmpty()) {
            return pixel.getValue();
        }

        float sumBoundaryWeights = 0;
        float sumBoundaryWeightsWithColour = 0;
        for (IPixel boundaryPixel : otherPixels) {
            double weight = weightingFunction.findWeight(boundaryPixel, pixel);
            sumBoundaryWeights += weight;
            sumBoundaryWeightsWithColour += weight * boundaryPixel.getValue();
        }
        return sumBoundaryWeightsWithColour / sumBoundaryWeights;
    }
}
