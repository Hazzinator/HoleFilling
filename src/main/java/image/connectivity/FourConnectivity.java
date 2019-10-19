package image.connectivity;

import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.ArrayList;
import java.util.List;

public class FourConnectivity extends ConnectivityBase implements IConnectivityType {

    @Override
    public List<IPixel> findConnectedPixels(IPixelMatrix matrix, IPixel matrixElement) {
        return findConnectedPixels(matrix, matrixElement.getRow(), matrixElement.getColumn());
    }

    @Override
    public List<IPixel> findConnectedPixels(IPixelMatrix matrix, int row, int column) {
        List<IPixel> boundaryPixels = new ArrayList<>();
        // Grab y - 1, x, y + 1, x, x + 1 y
        if (row - 1 >= 0) {
            boundaryPixels.add(matrix.getPixel(row - 1, column));
        }

        if (column - 1 >= 0) {
            boundaryPixels.add(matrix.getPixel(row, column - 1));
        }

        if (column + 1 < matrix.columns()) {
            boundaryPixels.add(matrix.getPixel(row, column + 1));
        }

        if (row + 1 < matrix.rows()) {
            boundaryPixels.add(matrix.getPixel(row + 1, column));
        }

        return boundaryPixels;
    }
}
