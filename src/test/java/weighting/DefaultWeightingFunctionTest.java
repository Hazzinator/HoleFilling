package weighting;

import image.matrix.IMatrixElement;
import image.matrix.MatrixElement;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;
import image.matrix.pixel.Pixel;
import image.weighting.DefaultWeightingFunction;
import org.junit.Assert;
import org.junit.Test;

public class DefaultWeightingFunctionTest {


    @Test(expected = IllegalArgumentException.class)
    public void TestEpsilonEquals0Throws() {
        DefaultWeightingFunction function = new DefaultWeightingFunction(0.1, 0);
    }

    @Test
    public void TestFunctionWithCloseElementsAndSmallFunctionParameter() {

        DefaultWeightingFunction function = new DefaultWeightingFunction(2, 0.01);

        IPixel elementOne = new Pixel(2, 2, 2);
        IPixel elementTwo = new Pixel(3, 3, 3);

        double actual = function.findWeight(elementOne, elementTwo);

        // 1 / ((distance)^2 + epsilon)
        // 1 / sqrt(2)^2 + epsilon
        // 1 / 2 + 0.01
        // 1 / 2.01
        double expected = 0.4975124;

        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void TestFunctionWithFarElementsAndLargeFunctionParameters() {

        DefaultWeightingFunction function = new DefaultWeightingFunction(3.8, 0.5);

        IPixel elementOne = new Pixel(2, 2, 2);
        IPixel elementTwo = new Pixel(16, 101, 3);

        double actual = function.findWeight(elementOne, elementTwo);

        double expected = 0.0000000251331;

        Assert.assertEquals(expected, actual, 0.0000001);
    }


}
