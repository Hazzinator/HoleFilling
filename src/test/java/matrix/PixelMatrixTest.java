package matrix;

import image.matrix.IMatrixElement;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;
import image.matrix.pixel.Pixel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;

import java.util.HashSet;

public class PixelMatrixTest {

    @Before
    public void SetUp() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void TestGetValueBelowZeroThrows() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();
        matrix.setValue(-1, 1, 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void TestGetValueGreaterThanMaxIndexThrows() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();
        matrix.getValue(4, 4);
    }

    @Test
    public void TestTwoMatsWithSameElementsAreEqual() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        IPixelMatrix sameMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        Assert.assertEquals(matrix, sameMatrix);
    }

    @Test
    public void TestTwoMatsWithDifferentElementsAreNotEqual() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        IPixelMatrix zeroMatrix = MatrixTestFactory.CreatePixelMatrixWithAll0s();

        Assert.assertNotEquals(matrix, zeroMatrix);
    }

    @Test
    public void TestGetValueNormalValue() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        float value = matrix.getValue(1, 1);

        int expectedValue = 6;

        Assert.assertEquals(expectedValue, value, 0.0001);
    }

    @Test
    public void TestSetValue3GetValue() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        matrix.setValue(1, 1, 3);

        float value = matrix.getValue(1,1);

        float expectedValue = 3;

        Assert.assertEquals(expectedValue, value, 0.0001);
    }

    @Test
    public void TestGetElementWhenNotAccessedBefore() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        IPixel expected = new Pixel(1, 1, 6);

        IPixel actual = matrix.getPixel(1, 1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void TestSetElementThenGetValue() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        IPixel newElement = new Pixel(1, 1, 5);

        matrix.setPixel(newElement);

        float actual = matrix.getValue(1, 1);
        float expected = 5;

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void TestSetElementThenGetElement() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixWithAll0s();

        IPixel newElement = new Pixel(1, 1, 5);

        matrix.setPixel(newElement);

        IPixel actual = matrix.getPixel(1, 1);
        IPixel expected = new Pixel(1, 1, 5);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void TestDistanceFromSameElement() {
        IPixel elementOne = new Pixel(1,1,1);
        IPixel elementTwo = new Pixel(1,1,1);

        Assert.assertEquals(0, elementOne.distanceFrom(elementTwo), 0);
    }

    @Test
    public void TestDistanceFromCloseElement() {
        IPixel elementOne = new Pixel(1,1,1);
        IPixel elementTwo = new Pixel(2,2,1);

        double expected = Math.sqrt(2);

        Assert.assertEquals(expected, elementOne.distanceFrom(elementTwo), 0.001);
    }

    @Test
    public void TestDistanceFromFarElement() {
        IPixel elementOne = new Pixel(1,1,1);
        IPixel elementTwo = new Pixel(15,260,1);

        double expected = 259.3781;

        Assert.assertEquals(expected, elementOne.distanceFrom(elementTwo), 0.0001);
    }

    @Test
    public void TestFindElementsWithValueOf5InIncrementalMatrix() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var elements = matrix.findAllPixelsWithValue(5, 0);

        var expected = new HashSet<IMatrixElement>();
        expected.add(new Pixel(1, 0, 5));

        Assert.assertEquals(expected, elements);
    }

    @Test
    public void TestFindHoleElementsIn8ConnectedHoleReturnsValues() {
        IPixelMatrix matrix = MatrixTestFactory.CreateSmallPixelMatrix8ConnectedHole();

        var elements = matrix.findAllHolePixels();

        var expected = new HashSet<IMatrixElement>();
        expected.add(new Pixel(1, 1, -1));
        expected.add(new Pixel(2, 2, -1));

        Assert.assertEquals(expected, elements);
    }

    @Test
    public void TestFindHoleElementsInMatrixWithoutHoleReturnsEmptySet() {
        IPixelMatrix matrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var elements = matrix.findAllHolePixels();

        var expected = new HashSet<IPixel>();

        Assert.assertEquals(expected, elements);
    }
}
