package image;

import image.exception.ImageNotFoundException;
import image.matrix.MatrixFactory;
import image.matrix.pixel.IPixelMatrix;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Reads an image and returns a matrix object that can be manipulated
 */
class ImageReader {

    private MatrixFactory matrixFactory;

    ImageReader() {
        matrixFactory = new MatrixFactory();
    }

    /**
     * Reads an image from the source location and returns an {@link IPixelMatrix} representation of it
     * @param imageLocation
     * @return
     */
    IPixelMatrix readGreyScaleImage(String imageLocation) {
        Mat underlyingMat = Imgcodecs.imread(imageLocation, Imgcodecs.IMREAD_GRAYSCALE);
        if (underlyingMat.empty()) {
            throw new ImageNotFoundException("The file " + imageLocation + " was not found.");
        }
        Mat normalisedMat = normaliseBetweenZeroAndOne(underlyingMat);
        return matrixFactory.CreatePixelMatrix(normalisedMat);
    }

    /**
     * Normalises a {@link Mat} such that all values are normalised to appear in the range [0-1]
     * @param initialMatrix
     * @return
     */
    private Mat normaliseBetweenZeroAndOne(Mat initialMatrix) {
        Mat destMat = new Mat();
        initialMatrix.convertTo(destMat, CvType.CV_32F, 1.f/255);
        return destMat;
    }
}
