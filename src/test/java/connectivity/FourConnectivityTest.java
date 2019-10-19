package connectivity;

import image.connectivity.EightConnectivity;
import image.connectivity.FourConnectivity;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;
import image.matrix.pixel.Pixel;
import matrix.MatrixTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;

import java.util.Arrays;

public class FourConnectivityTest {
    @Before
    public void SetUp() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void GetFourConnectivityInTopLeftCorner() {
        FourConnectivity connectFunc = new FourConnectivity();

        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var connectedElements = connectFunc.findConnectedPixels(matrix, 0, 0);

        IPixel[] expectedPixels = new Pixel[] {
                new Pixel(0, 1, 2),
                new Pixel(1, 0, 5)
        };

        var expectedList = Arrays.asList(expectedPixels);

        Assert.assertEquals(expectedList, connectedElements);
    }

    @Test
    public void GetFourConnectivityInBottomRightCorner() {
        FourConnectivity connectFunc = new FourConnectivity();

        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var connectedElements = connectFunc.findConnectedPixels(matrix, 3, 3);

        IPixel[] expectedElements = new Pixel[] {
                new Pixel(2, 3, 12),
                new Pixel(3, 2, 15)
        };

        var expectedList = Arrays.asList(expectedElements);

        Assert.assertEquals(expectedList, connectedElements);
    }

    @Test
    public void GetFourConnectivityInMiddle() {
        FourConnectivity connectFunc = new FourConnectivity();

        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var connectedElements = connectFunc.findConnectedPixels(matrix, 1, 1);

        IPixel[] expectedElements = new Pixel[] {
                new Pixel(0, 1, 2),
                new Pixel(1, 0, 5),
                new Pixel(1, 2, 7),
                new Pixel(2, 1, 10),
        };

        var expectedList = Arrays.asList(expectedElements);

        Assert.assertEquals(expectedList, connectedElements);
    }

    /**
     *   1,  2,  3,  4,  5,  6,
     *   7,  8, -1, -1,  9, 10,
     *  11, -1, -1, -1, -1, 13,
     *  14, 15, -1, -1, -1, 16,
     *  17, -1, -1, -1, -1, 18,
     *  19, 20, 21, 22, 23, -1};
     * */
    @Test
    public void GetConnectedNonHoleElementsFromHole() {
        FourConnectivity connectFunc = new FourConnectivity();

        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd8ConnectedHole();

        IPixel pixel = new Pixel(1, 2, -1);

        var connectedNonHoleElements = connectFunc.findNonHoleConnectedPixels(matrix, pixel);

        IPixel[] expectedElements = new Pixel[] {
                new Pixel(0, 2, 3),
                new Pixel(1, 1, 8)
        };

        var expectedList = Arrays.asList(expectedElements);

        Assert.assertEquals(expectedList, connectedNonHoleElements);
    }

    /**
     *   1,  2,  3,  4,  5,  6,
     *   7,  8, -1, -1,  9, 10,
     *  11, -1, -1, -1, -1, 13,
     *  14, 15, -1, -1, -1, 16,
     *  17, -1, -1, -1, -1, 18,
     *  19, 20, 21, 22, 23, -1};
     * */
    @Test
    public void GetConnectedHoleElementsFromHole() {
        FourConnectivity connectFunc = new FourConnectivity();

        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd8ConnectedHole();

        IPixel pixel = new Pixel(1, 2, -1);

        var connectedNonHoleElements = connectFunc.findConnectedHolePixels(matrix, pixel);

        IPixel[] expectedElements = new Pixel[] {
                new Pixel(1, 3, -1),
                new Pixel(2, 2, -1)
        };

        var expectedList = Arrays.asList(expectedElements);

        Assert.assertEquals(expectedList, connectedNonHoleElements);
    }
}
