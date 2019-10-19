package image.holefilling;

import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an input matrix and a hole matrix, will interweave them such that
 * any pixels with a positive value in the mask will incur a hole (-1 value) in same
 * location of the input matrix
 */
public class HoleInterlacer implements IHoleInterlacer {

    /* Most image editing software will create values of 250-255ish in the areas
     * You define as white on a mask. There will also be smaller values of
     * 1-10 dotted around the hole (at least this was the case when I was
     * using GIMP). To ensure that only the white pixels (or "very-close-to
     * -white" pixels are picked up as hole, this cutoff will define a point where
     * pixels greater than or equal to it are a hole, while less than is not a hole.
     * 0.96 roughly equivalent to a cutoff of 244 in a 0-255 greyscale image.
     */
    private final float minimumHoleValue = 0.96f;

    public Set<IPixel> interlace(IPixelMatrix originalMatrix, IPixelMatrix holeMask) {
        if (originalMatrix.columns() != holeMask.columns() || originalMatrix.rows() != holeMask.rows()) {
            throw new IllegalArgumentException("Matrix and mask must have same number of rows and columns");
        }

        return carveOutHole(originalMatrix, holeMask);
    }

    private Set<IPixel> carveOutHole(IPixelMatrix originalMatrix, IPixelMatrix holeMask) {
        Set<IPixel> holeElements = new HashSet<>();
        for (int row = 0; row < originalMatrix.rows(); row++) {
            for (int col = 0; col < originalMatrix.columns(); col++) {
                float holeValue = holeMask.getValue(row, col);
                if (holeValue >= minimumHoleValue) {
                    originalMatrix.setValue(row, col, -1);
                    holeElements.add(originalMatrix.getPixel(row, col));
                }
            }
        }
        return holeElements;
    }
}
