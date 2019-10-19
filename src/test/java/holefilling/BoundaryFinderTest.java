package holefilling;

import image.holefilling.BoundaryFinder;
import image.connectivity.EightConnectivity;
import image.connectivity.FourConnectivity;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.Pixel;
import matrix.MatrixTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;

import java.util.Arrays;
import java.util.HashSet;

public class BoundaryFinderTest {

    @Before
    public void SetUp() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void TestMatrixWithNoHoleHasNoElementsInBoundaryWithEightConnectivity() {

        BoundaryFinder boundaryFinder = new BoundaryFinder();

        var matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var boundaryPixels = boundaryFinder.findBoundaryElements(matrix, new EightConnectivity());

        Assert.assertEquals(boundaryPixels.size(), 0);
    }


    @Test
    public void TestMatrixWithNoHoleHasNoElementsInBoundaryWithFourConnectivity() {

        BoundaryFinder boundaryFinder = new BoundaryFinder();

        var matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var boundaryPixels = boundaryFinder.findBoundaryElements(matrix, new FourConnectivity());

        Assert.assertEquals(boundaryPixels.size(), 0);
    }

    @Test
    public void Test4ConnectedHoleGetsCorrectBoundaryElementsWith4ConnectedBoundaryFunction() {

        BoundaryFinder boundaryFinder = new BoundaryFinder();

        var matrix = MatrixTestFactory.CreateSmallPixelMatrix4ConnectedHole();

        var boundaryPixels = boundaryFinder.findBoundaryElements(matrix, new FourConnectivity());

        matrix.dump();

        IPixel[] list = {
                new Pixel(0, 1, 2),
                new Pixel(0, 2, 3),
                new Pixel(1, 0, 5),
                new Pixel(1, 3, 8),
                new Pixel(2, 0, 9),
                new Pixel(2, 3, 12),
                new Pixel(3, 1, 14),
                new Pixel(3, 3, 16)
        };
        var expectedBoundaryPixels = new HashSet<>(Arrays.asList(list));

        Assert.assertEquals(expectedBoundaryPixels, boundaryPixels);
    }

    @Test
    public void Test4ConnectedHoleGetsCorrectBoundaryElementsWith8ConnectedBoundaryFunction() {

        BoundaryFinder boundaryFinder = new BoundaryFinder();

        var matrix = MatrixTestFactory.CreateSmallPixelMatrix4ConnectedHole();

        var boundaryPixels = boundaryFinder.findBoundaryElements(matrix, new EightConnectivity());

        IPixel[] list = {
                new Pixel(0, 0, 1),
                new Pixel(0, 1, 2),
                new Pixel(0, 2, 3),
                new Pixel(0, 3, 4),
                new Pixel(1, 0, 5),
                new Pixel(1, 3, 8),
                new Pixel(2, 0, 9),
                new Pixel(2, 3, 12),
                new Pixel(3, 0, 13),
                new Pixel(3, 1, 14),
                new Pixel(3, 3, 16)
        };
        var expectedBoundaryPixels = new HashSet<>(Arrays.asList(list));

        Assert.assertEquals(expectedBoundaryPixels, boundaryPixels);
    }

    @Test
    public void Test4ConnectedHoleGetsCorrectHoleBoundaryElementsWith4ConnectedBoundaryFunction() {

        BoundaryFinder boundaryFinder = new BoundaryFinder();

        var matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd4ConnectedHole();

        var boundaryPixels = boundaryFinder.findBoundaryConnectedHolePixels(matrix, new FourConnectivity());

        matrix.dump();

        IPixel[] list = {
                new Pixel(1, 2, -1),
                new Pixel(1, 3, -1),
                new Pixel(2, 2, -1),
                new Pixel(2, 4, -1),
                new Pixel(3, 2, -1),
                new Pixel(3, 4, -1),
                new Pixel(4, 1, -1),
                new Pixel(4, 2, -1),
                new Pixel(4, 3, -1),
                new Pixel(4, 4, -1)

        };
        var expectedBoundaryPixels = new HashSet<>(Arrays.asList(list));

        Assert.assertEquals(expectedBoundaryPixels, boundaryPixels);
    }

    /**
     *  3  3  3  3  3  3
     *  3  3 -1 -1  3  3
     *  3 -1 -1 -1 -1  3
     *  3  3 -1 -1 -1  3
     *  3 -1 -1 -1 -1  3
     *  3  3  3  3  3 -1
     */
    @Test
    public void Test8ConnectedHoleGetsCorrectHoleBoundaryElementsWith8ConnectedBoundaryFunction() {

        BoundaryFinder boundaryFinder = new BoundaryFinder();

        var matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd8ConnectedHole();

        var boundaryPixels = boundaryFinder.findBoundaryConnectedHolePixels(matrix, new EightConnectivity());

        matrix.dump();

        IPixel[] list = {
                new Pixel(1, 2, -1),
                new Pixel(1, 3, -1),
                new Pixel(2, 1, -1),
                new Pixel(2, 2, -1),
                new Pixel(2, 3, -1),
                new Pixel(2, 4, -1),
                new Pixel(3, 2, -1),
                new Pixel(3, 4, -1),
                new Pixel(4, 1, -1),
                new Pixel(4, 2, -1),
                new Pixel(4, 3, -1),
                new Pixel(4, 4, -1),
                new Pixel(5, 5, -1)

        };
        var expectedBoundaryPixels = new HashSet<>(Arrays.asList(list));

        Assert.assertEquals(expectedBoundaryPixels, boundaryPixels);
    }
}
