package matrix;

import image.matrix.*;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.Pixel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixCacheTest {

    private IMatrixCache matrixCache;

    @Before
    public void SetUp() {
        matrixCache = new MatrixCache();
    }

    @Test
    public void TestGetWithElementNotInCacheReturnsNull() {
        Assert.assertNull(matrixCache.get(5, 5));
    }

    @Test
    public void TestAddElementThenGet() {
        Pixel pixel = new Pixel(1, 1, 5);
        matrixCache.set(pixel);
        IMatrixElement element = matrixCache.get(1, 1);

        Assert.assertEquals(pixel, element);
    }

    @Test
    public void TestAddElementThenContains() {
        Pixel pixel = new Pixel(1, 1, 5);
        matrixCache.set(pixel);

        Assert.assertTrue(matrixCache.contains(1, 1));
    }

    @Test
    public void TestSetRetrieveChangeSetThenGet() {
        matrixCache.set(new Pixel(1, 1, 5));
        IPixel element = (IPixel)matrixCache.get(1, 1);

        element.setValue(10);

        matrixCache.set(element);

        element = (IPixel)matrixCache.get(1,1);

        IMatrixElement expectedElement = new Pixel(1, 1, 10);

        Assert.assertEquals(expectedElement, element);
    }
}
