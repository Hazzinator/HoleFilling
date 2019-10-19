package image.matrix;

import org.opencv.core.Mat;

/**
 * Base matrix interface.
 */
public interface IMatrix {

    int columns();

    int rows();

    void dump();

    Mat underlyingMat();
}
