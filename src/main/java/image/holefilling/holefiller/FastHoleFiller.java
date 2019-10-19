package image.holefilling.holefiller;

import image.colour.IColourCalculator;
import image.connectivity.IConnectivityType;
import image.holefilling.IBoundaryFinder;
import image.holefilling.IHoleInterlacer;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Fills a hole using a faster but less accurate method. Works well for smaller holes or for holes where
 * the surrounding boundary has similar colours
 */
public class FastHoleFiller extends HoleFillerBase implements IHoleFiller {
    public FastHoleFiller(
            IHoleInterlacer holeInterlacer,
            IBoundaryFinder boundaryFinder,
            IConnectivityType connectivityType,
            IColourCalculator colourCalculator) {
        super(holeInterlacer, boundaryFinder, connectivityType, colourCalculator);
    }


    /**
     * Less accurate but faster implementation of the hole filling algorithm (runs in O(n))
     * @param inputMatrix Matrix to fill hole on.
     * @param mask Matrix defining the mask where the hole will exist.
     */
    @Override
    public void fillHole(IPixelMatrix inputMatrix, IPixelMatrix mask) {
        var holePixels = holeInterlacer.interlace(inputMatrix, mask);
        var holePixelsConnectedToBoundary =
                boundaryFinder.findBoundaryConnectedHolePixels(inputMatrix, holePixels, connectivityType);

        fillHoleFast(inputMatrix, holePixelsConnectedToBoundary);
        ThrowIfHoleStillExists(inputMatrix);
    }

    private void fillHoleFast(IPixelMatrix matrix, Set<IPixel> initialHoleLayerPixels) {
        Set<IPixel> currentLayerHolePixels = initialHoleLayerPixels;
        while (!currentLayerHolePixels.isEmpty()) {
            currentLayerHolePixels = generateColoursForCurrentHoleLayer(matrix, currentLayerHolePixels);
        }
    }

    /**
     * Given a matrix and a set of pixels that are connected to the current boundary, calculate the new colour
     * for those pixels.
     * @return Set of the next boundary hole pixels (going in one layer)
     */
    private Set<IPixel> generateColoursForCurrentHoleLayer(IPixelMatrix matrix, Set<IPixel> currentLayer) {
        Set<IPixel> nextBoundaryHolePixels = new HashSet<>();
        for (IPixel holePixel : currentLayer) {

            List<IPixel> connectedHolePixels = findHolePixelsInNextLayer(matrix, holePixel, currentLayer);
            List<IPixel> connectedNonHolePixels = connectivityType.findNonHoleConnectedPixels(matrix, holePixel);

            nextBoundaryHolePixels.addAll(connectedHolePixels);

            float newColour = colourCalculator.CalculateWeightedColourFromPixels(holePixel, connectedNonHolePixels);

            holePixel.setValue(newColour);
            matrix.setPixel(holePixel);
        }
        return nextBoundaryHolePixels;
    }

    private List<IPixel> findHolePixelsInNextLayer(IPixelMatrix matrix, IPixel pixel, Set<IPixel> currentLayer) {
        List<IPixel> connectedHolePixels = connectivityType.findConnectedHolePixels(matrix, pixel);
        return connectedHolePixels.stream().filter(p -> !currentLayer.contains(p)).collect(Collectors.toList());
    }
}
