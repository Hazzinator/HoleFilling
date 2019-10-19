package image.holefilling.holefiller;

import image.colour.IColourCalculator;
import image.connectivity.IConnectivityType;
import image.holefilling.IBoundaryFinder;
import image.holefilling.IHoleInterlacer;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.Set;

public class SlowHoleFiller extends HoleFillerBase implements IHoleFiller {

    private IHoleInterlacer holeInterlacer;
    private IBoundaryFinder boundaryFinder;
    private IConnectivityType connectivityType;
    private IColourCalculator colourCalculator;

    public SlowHoleFiller(IHoleInterlacer holeInterlacer,
                          IBoundaryFinder boundaryFinder,
                          IConnectivityType connectivityType,
                          IColourCalculator colourCalculator) {
        super(holeInterlacer, boundaryFinder, connectivityType, colourCalculator);
        this.holeInterlacer = holeInterlacer;
        this.boundaryFinder = boundaryFinder;
        this.connectivityType = connectivityType;
        this.colourCalculator = colourCalculator;
    }

    /* Public members */

    /**
     * Slower but accurate implementation of the hole filling algorithm (runs in O(nm))
     * @param inputMatrix Matrix to fill hole on.
     * @param mask Matrix defining the mask where the hole will exist.
     */
    @Override
    public void fillHole(IPixelMatrix inputMatrix, IPixelMatrix mask) {
        var holePixels = holeInterlacer.interlace(inputMatrix, mask);
        var boundaryPixels = boundaryFinder.findBoundaryPixels(inputMatrix, holePixels, connectivityType);

        FillHoleSlow(inputMatrix, boundaryPixels, holePixels);
        ThrowIfHoleStillExists(inputMatrix);
    }

    /* Private members */

    private void FillHoleSlow(IPixelMatrix matrix, Set<IPixel> boundaryPixels, Set<IPixel> holePixels) {
        // Calculate weights
        for (IPixel holePixel : holePixels) {
            float newColour = colourCalculator.CalculateWeightedColourFromPixels(holePixel, boundaryPixels);
            holePixel.setValue(newColour);
            matrix.setPixel(holePixel);
        }
    }
}
