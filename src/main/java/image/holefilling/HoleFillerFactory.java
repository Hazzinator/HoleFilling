package image.holefilling;

import image.colour.DefaultColourCalculator;
import image.colour.IColourCalculator;
import image.connectivity.IConnectivityType;
import image.holefilling.holefiller.FastHoleFiller;
import image.holefilling.holefiller.IHoleFiller;
import image.holefilling.holefiller.SlowHoleFiller;
import image.weighting.DefaultWeightingFunction;
import image.weighting.IWeightingFunction;

/**
 * Hides the creation of an IHoleFiller by handling dependencies during the creation.
 */
public class HoleFillerFactory {

    public IHoleFiller CreateSlowHoleFiller(IConnectivityType connectivityType, double distanceExponent, double zeroCorrector) {
        return CreateHoleFiller(connectivityType, distanceExponent, zeroCorrector, false);
    }

    public IHoleFiller CreateFastHoleFiller(IConnectivityType connectivityType, double distanceExponent, double zeroCorrector) {
        return CreateHoleFiller(connectivityType, distanceExponent, zeroCorrector, true);
    }

    private IHoleFiller CreateHoleFiller(IConnectivityType connectivityType, double distanceExponent, double zeroCorrector, boolean isFast) {
        IWeightingFunction weightingFunction = new DefaultWeightingFunction(distanceExponent, zeroCorrector);
        IColourCalculator colourCalculator = new DefaultColourCalculator(weightingFunction);
        BoundaryFinder boundaryFinder = new BoundaryFinder();
        HoleInterlacer holeInterlacer = new HoleInterlacer();
        if (isFast) {
            return new FastHoleFiller(holeInterlacer, boundaryFinder, connectivityType, colourCalculator);
        }
        return new SlowHoleFiller(holeInterlacer, boundaryFinder, connectivityType, colourCalculator);
    }
}
