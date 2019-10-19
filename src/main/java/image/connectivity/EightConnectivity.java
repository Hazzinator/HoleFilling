package image.connectivity;

import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.ArrayList;
import java.util.List;

public class EightConnectivity extends ConnectivityBase implements IConnectivityType {

    @Override
    public List<IPixel> findConnectedPixels(IPixelMatrix matrix, IPixel pixel) {
        return findConnectedPixels(matrix, pixel.getRow(), pixel.getColumn());
    }

    @Override
    public List<IPixel> findConnectedPixels(IPixelMatrix matrix, int centreRow, int centreColumn) {
        List<IPixel> boundaryPixels = new ArrayList<>();

        int leftBound = centreColumn == 0 ? centreColumn : centreColumn - 1;
        int topBound = centreRow == 0 ? centreRow : centreRow - 1;
        int rightBound = centreColumn == matrix.columns() - 1 ? centreColumn : centreColumn + 1;
        int bottomBound = centreRow == matrix.rows() - 1 ? centreRow : centreRow + 1;

        for (int row = topBound; row <= bottomBound; row++) {
            for (int column = leftBound; column <= rightBound; column++) {
                boundaryPixels.add(matrix.getPixel(row, column));
            }
        }
        boundaryPixels.remove(matrix.getPixel(centreRow, centreColumn));

        return boundaryPixels;
    }
}
