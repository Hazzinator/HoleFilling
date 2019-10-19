package image.matrix.pixel;

import image.matrix.MatrixElement;

public class Pixel extends MatrixElement implements IPixel {

    protected float value;

    public Pixel(int row, int column, float value) {
        super(row, column);
        this.value = value;
    }

    /* Object overrides */

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + row;
        result = 31 * result + column;
        result = 31 * result + Float.floatToIntBits(value);
        return result;
    }

    @Override
    public String toString() {
        return "("+this.row+","+this.column+") : "+ this.value;
    }

    /* IPixel implementation */

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    /* Protected methods */

    @Override
    protected boolean valuesEqual(MatrixElement otherElement) {
        if (!(otherElement instanceof Pixel)) {
            return false;
        }
        return ((Pixel)otherElement).getValue() == this.value;
    }
}
