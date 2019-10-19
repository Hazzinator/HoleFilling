package matrix;

import image.matrix.*;
import image.matrix.pixel.IPixelMatrix;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class MatrixTestFactory {

    private static final MatrixFactory matrixFactory = new MatrixFactory();

    public static IPixelMatrix CreateMatrix(Mat mat) {
        return matrixFactory.CreatePixelMatrix(mat);
    }

    public static IPixelMatrix CreatePixelMatrixWithAll0s() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    public static IPixelMatrix CreatePixelMatrixWithAll1s() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     *   1,  2,  3,  4,  5,  6,
     *   7,  8, -1, -1,  9, 10,
     *  11, 12, -1, -1, -1, 13,
     *  14, 15, -1, -1, -1, 16,
     *  17, -1, -1, -1, -1, 18,
     *  19, 20, 21, 22, 23, 24};
     * */
    public static IPixelMatrix CreatePixelMatrixIncrementalValuesAnd4ConnectedHole() {
        Mat matrix = new Mat(6, 6, CvType.CV_32FC1);
        float[] imgData = new float[]{
                1,   2,  3,  4,  5,  6,
                7,   8, -1, -1,  9, 10,
                11, 12, -1, -1, -1, 13,
                14, 15, -1, -1, -1, 16,
                17, -1, -1, -1, -1, 18,
                19, 20, 21, 22, 23, 24};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }


    /**
     *   1,  2,  3,  4,  5,  6,
     *   7,  8, -1, -1,  9, 10,
     *  11, -1, -1, -1, -1, 13,
     *  14, 15, -1, -1, -1, 16,
     *  17, -1, -1, -1, -1, 18,
     *  19, 20, 21, 22, 23, -1};
     * */
    public static IPixelMatrix CreatePixelMatrixIncrementalValuesAnd8ConnectedHole() {
        Mat matrix = new Mat(6, 6, CvType.CV_32FC1);
        float[] imgData = new float[]{
                1,   2,  3,  4,  5,  6,
                7,   8, -1, -1,  9, 10,
                11, -1, -1, -1, -1, 13,
                14, 15, -1, -1, -1, 16,
                17, -1, -1, -1, -1, 18,
                19, 20, 21, 22, 23, -1};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     *  1  2  3  4
     *  5  6  7  8
     *  9 10 11 12
     * 13 14 15 16
     */
    public static IPixelMatrix CreatePixelMatrixIncrementalValues() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     * - - - -
     * - H - -
     * - - H -
     * - - - -
     */
    public static IPixelMatrix CreatePixelMatrix8ConnectedHole() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     * - - - -
     * - H H
     * - H H -
     * - - H -
     */
    public static IPixelMatrix CreateMaskPixelMatrix4ConnectedHole() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0 };
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     *  1  2  3  4
     *  5 -1 -1  8
     *  9 -1 -1 12
     * 13 14 -1 16
     */
    public static IPixelMatrix CreateSmallPixelMatrix4ConnectedHole() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{1, 2, 3, 4, 5, -1, -1, 8, 9, -1, -1, 12, 13, 14, -1, 16};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     *  1  2  3  4
     *  5 -1  7  8
     *  9 10 -1 12
     * 13 14 15 16
     */
    public static IPixelMatrix CreateSmallPixelMatrix8ConnectedHole() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{1, 2, 3, 4, 5, -1, 7, 8, 9, 10, -1, 12, 13, 14, 15, 16};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }

    /**
     *  1  2  3  4
     *  5 -1  7  8
     *  9 10 11 12
     * 13 14 15 -1
     */
    public static IPixelMatrix CreateIncrementalMatrixWithDisconnectedHole() {
        Mat matrix = new Mat(4, 4, CvType.CV_32FC1);
        float[] imgData = new float[]{-1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1};
        matrix.put(0, 0, imgData);
        return matrixFactory.CreatePixelMatrix(matrix);
    }
}
