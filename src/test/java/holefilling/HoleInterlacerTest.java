package holefilling;

import image.holefilling.HoleInterlacer;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.Pixel;
import matrix.MatrixTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HoleInterlacerTest {

    private HoleInterlacer holeInterlacer = new HoleInterlacer();

    @Before
    public void SetUp() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void TestEmptyHoleMaskHasDoesNotAffectMatrix() {
        var interlacedMatrix = MatrixTestFactory.CreatePixelMatrixWithAll1s();
        var interlacedMatrixCopy = MatrixTestFactory.CreatePixelMatrixWithAll1s();

        var maskMatrix = MatrixTestFactory.CreatePixelMatrixWithAll0s();

        holeInterlacer.interlace(interlacedMatrix, maskMatrix);

        Assert.assertEquals(interlacedMatrix, interlacedMatrixCopy);
    }

    @Test
    public void TestCorrectInterlacingWith4ConnectedMask() {
        var interlacedMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var maskMatrix = MatrixTestFactory.CreateMaskPixelMatrix4ConnectedHole();

        holeInterlacer.interlace(interlacedMatrix, maskMatrix);

        var expectedMatrix = MatrixTestFactory.CreateSmallPixelMatrix4ConnectedHole();

        Assert.assertEquals(expectedMatrix, interlacedMatrix);
    }

    @Test
    public void TestCorrectInterlacingWith8ConnectedMask() {
        var interlacedMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var maskMatrix = MatrixTestFactory.CreatePixelMatrix8ConnectedHole();

        holeInterlacer.interlace(interlacedMatrix, maskMatrix);

        var expectedMatrix = MatrixTestFactory.CreateSmallPixelMatrix8ConnectedHole();

        Assert.assertEquals(expectedMatrix, interlacedMatrix);
    }

    @Test
    public void TestInterlacerCorrectlyReturnsHoleElementsIn8ConnectedMatrix() {
        var interlacedMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var maskMatrix = MatrixTestFactory.CreatePixelMatrix8ConnectedHole();

        var holeElements = holeInterlacer.interlace(interlacedMatrix, maskMatrix);

        Set<IPixel> expectedHoleElements = new HashSet<>(Arrays.asList(
                new Pixel(1, 1, -1),
                new Pixel(2, 2, -1))
        );

        Assert.assertEquals(expectedHoleElements, holeElements);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestMatrixesWithDifferentColumnAndRowCountThrowsException() {
        var interlacedMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValues();

        var maskMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd4ConnectedHole();

        holeInterlacer.interlace(interlacedMatrix, maskMatrix);
    }
}
