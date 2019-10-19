package image.holefilling.holefiller;

import image.colour.IColourCalculator;
import image.connectivity.IConnectivityType;
import image.exception.HoleFillerException;
import image.holefilling.IBoundaryFinder;
import image.holefilling.IHoleInterlacer;
import image.matrix.pixel.IPixelMatrix;

abstract class HoleFillerBase implements IHoleFiller {
    IHoleInterlacer holeInterlacer;
    IBoundaryFinder boundaryFinder;
    IConnectivityType connectivityType;
    IColourCalculator colourCalculator;

    HoleFillerBase(IHoleInterlacer holeInterlacer,
                   IBoundaryFinder boundaryFinder,
                   IConnectivityType connectivityType,
                   IColourCalculator colourCalculator) {

        this.holeInterlacer = holeInterlacer;
        this.boundaryFinder = boundaryFinder;
        this.connectivityType = connectivityType;
        this.colourCalculator = colourCalculator;
    }

    protected void ThrowIfHoleStillExists(IPixelMatrix matrix) {
        if (!matrix.findAllHolePixels().isEmpty()) {
            throw new HoleFillerException("The hole filler could not fill all the holes using the specified connectivity type. " +
                    "Please check that all hole pixels in the mask are connected.");
        }
    }


}
