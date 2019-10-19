package image.matrix;

import java.util.HashMap;
import java.util.Map;

public class MatrixCache implements IMatrixCache {

    private Map<Integer, Map<Integer, IMatrixElement>> matrixCache;

    public MatrixCache() {
        matrixCache = new HashMap<>();
    }

    /* Object override */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MatrixCache)) {
            return false;
        }
        MatrixCache other = (MatrixCache) o;

        return matrixCache.equals(other.matrixCache);
    }

    @Override
    public int hashCode() {
        return 31 * 17 + matrixCache.hashCode();
    }

    /* IMatrixCache implementation */

    @Override
    public boolean contains(int row, int column) {
        return containsRowAndColumn(row, column);
    }

    @Override
    public IMatrixElement get(int row, int column) {
        if (!contains(row, column)) {
            return null;
        }
        return matrixCache.get(row).get(column);
    }

    @Override
    public void set(IMatrixElement element) {
        if (!containsRow(element.getRow())) {
            matrixCache.put(element.getRow(), new HashMap<>());
        }
        matrixCache.get(element.getRow()).put(element.getColumn(), element);
    }

    /* Private members */

    private boolean containsRowAndColumn(int row, int column) {
        return containsRow(row)
                && matrixCache.get(row).containsKey(column);
    }

    private boolean containsRow(int row) {
        return matrixCache.containsKey(row);
    }
}
