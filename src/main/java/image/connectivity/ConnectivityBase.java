package image.connectivity;

import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ConnectivityBase implements IConnectivityType {

    @Override
    public List<IPixel> findNonHoleConnectedPixels(IPixelMatrix matrix, IPixel pixel) {
        List<IPixel> connectedPixels = this.findConnectedPixels(matrix, pixel);
        return findNonHolePixels(connectedPixels);
    }

    @Override
    public List<IPixel> findConnectedHolePixels(IPixelMatrix matrix, IPixel pixel) {
        List<IPixel> connectedPixels = this.findConnectedPixels(matrix, pixel);
        return findHolePixels(connectedPixels);
    }


    private List<IPixel> findHolePixels(List<IPixel> connectedPixels) {
        return connectedPixels.stream()
                .filter(p -> p.getValue() == -1)
                .collect(Collectors.toList());
    }

    private List<IPixel> findNonHolePixels(List<IPixel> connectedPixels) {
        return connectedPixels
                .stream()
                .filter(p -> p.getValue() != -1)
                .collect(Collectors.toList());
    }
}
