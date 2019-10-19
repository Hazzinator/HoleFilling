package image.matrix;

public abstract class MatrixElement implements IMatrixElement {

    protected int row;
    protected int column;

    public MatrixElement(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /* Object overrides */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MatrixElement)) {
            return false;
        }
        MatrixElement other = (MatrixElement) o;
        return other.row == row && other.column == column && valuesEqual(other);
    }

    /* IMatrixElement implementation */

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public double distanceFrom(IMatrixElement other) {
        int xDelta = Math.abs(other.getRow() - this.row);
        int yDelta = Math.abs(other.getColumn() - this.column);
        return Math.sqrt(xDelta * xDelta + yDelta * yDelta);
    }

    /* Protected methods */

    protected abstract boolean valuesEqual(MatrixElement otherElement);
}
