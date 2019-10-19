package image.holefilling;

import image.connectivity.IConnectivityType;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoundaryFinder implements IBoundaryFinder {

    public Set<IPixel> findBoundaryPixels(IPixelMatrix matrix, IConnectivityType connectivityType) {
        var holeElements = matrix.findAllHolePixels();
        return findBoundaryPixels(matrix, holeElements, connectivityType);
    }

    public Set<IPixel> findBoundaryPixels(
            IPixelMatrix matrix,
            Set<IPixel> holeElements,
            IConnectivityType connectivityType) {

        HashSet<IPixel> boundaryPixels = new HashSet<>();
        for (IPixel holeElement : holeElements) {
            List<IPixel> nonHoleConnectedPixels = connectivityType.findNonHoleConnectedPixels(matrix, holeElement);
            boundaryPixels.addAll(nonHoleConnectedPixels);
        }
        return boundaryPixels;
    }

    public Set<IPixel> findBoundaryConnectedHolePixels(
            IPixelMatrix matrix,
            IConnectivityType connectivityType) {

        var holeElements = matrix.findAllHolePixels();
        return findBoundaryConnectedHolePixels(matrix, holeElements, connectivityType);
    }

     public Set<IPixel> findBoundaryConnectedHolePixels(
             IPixelMatrix matrix,
             Set<IPixel> holeElements,
             IConnectivityType connectivityType) {

         HashSet<IPixel> pixelsConnectedToBoundary = new HashSet<>();
         for (IPixel holeElement : holeElements) {
             List<IPixel> connectedPixels = connectivityType.findConnectedPixels(matrix, holeElement);
             for (IPixel connectedPixel : connectedPixels) {
                 if (connectedPixel.getValue() != -1) {
                     pixelsConnectedToBoundary.add(holeElement);
                     break;
                 }
             }
         }
         return pixelsConnectedToBoundary;
     }
}
