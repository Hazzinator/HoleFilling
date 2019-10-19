package image;

import image.exception.InvalidMatrixDataException;
import image.matrix.pixel.IPixelMatrix;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Saves an {@link IPixelMatrix} as an image at a specified location
 */
class ImageWriter {

    void saveImage(IPixelMatrix matrix, String outputLocation, String name) {
        if (!matrix.findAllHolePixels().isEmpty()) {
            throw new InvalidMatrixDataException("The image contains hole elements, so cannot be saved.");
        }

        Mat normalisedGreyscaleMat = normaliseToGreyScale(matrix);

        String uri = outputLocation + "\\" + name;
        Imgcodecs.imwrite(uri, normalisedGreyscaleMat);
    }

    /**
     * Normalises the matrix so that all values now exist within the range 0-255 (greyscale) proportionally
     */
    private Mat normaliseToGreyScale(IPixelMatrix matrix) {
        Mat newMat = new Mat();
        Core.normalize(matrix.underlyingMat(), newMat, 0, 255, Core.NORM_MINMAX);
        return newMat;
    }
}
