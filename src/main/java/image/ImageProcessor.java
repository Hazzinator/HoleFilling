package image;

import image.connectivity.EightConnectivity;
import image.connectivity.FourConnectivity;
import image.connectivity.IConnectivityType;
import image.holefilling.HoleFillerFactory;
import image.holefilling.holefiller.IHoleFiller;
import image.matrix.pixel.IPixelMatrix;
import org.opencv.core.Core;

/**
 * Main class for handling the processing of an image
 */
public class ImageProcessor {

    private ImageReader reader;
    private ImageWriter writer;

    public ImageProcessor() {
        // Load the C OpenCV native libraries
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.reader = new ImageReader();
        this.writer = new ImageWriter();
    }

    public void fillHoleFourConnectivity(String imageURI,
                                         String maskURI,
                                         String outputFolder,
                                         String outputFileName,
                                         double z,
                                         double epsilon,
                                         boolean isFast) {
        fillHole(imageURI, maskURI, outputFolder, outputFileName, z, epsilon, new FourConnectivity(), isFast);
    }

    public void fillHoleEightConnectivity(String imageURI,
                                          String maskURI,
                                          String outputFolder,
                                          String outputFileName,
                                          double z,
                                          double epsilon,
                                          boolean isFast) {
        fillHole(imageURI, maskURI, outputFolder, outputFileName, z, epsilon, new EightConnectivity(), isFast);
    }

    private void fillHole(String inputImageLocation,
                          String holeMaskLocation,
                          String outputLocation,
                          String newImageName,
                          double z,
                          double epsilon,
                          IConnectivityType connectivityType,
                          boolean isFast) {

        IPixelMatrix greyScaleImage = reader.readGreyScaleImage(inputImageLocation);
        writer.saveImage(greyScaleImage, outputLocation, "greyscale.jpg");

        IPixelMatrix maskImage = reader.readGreyScaleImage(holeMaskLocation);

        HoleFillerFactory fillerFactory = new HoleFillerFactory();
        IHoleFiller holeFiller = isFast
                ? fillerFactory.CreateFastHoleFiller(connectivityType, z, epsilon)
                : fillerFactory.CreateSlowHoleFiller(connectivityType, z, epsilon);

        holeFiller.fillHole(greyScaleImage, maskImage);

        writer.saveImage(greyScaleImage, outputLocation, newImageName);
    }
}
