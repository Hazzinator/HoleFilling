package image.matrix.pixel;

import image.matrix.IMatrixElement;

/**
 * An IMatrixElement that contains methods to set or change the value as a float.
 */
public interface IPixel extends IMatrixElement  {
    float getValue();

    void setValue(float value);
}
