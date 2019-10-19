package colour;

import image.colour.DefaultColourCalculator;
import image.colour.IColourCalculator;
import image.matrix.pixel.Pixel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ColourCalculatorTests {

    @Test
    public void TestColourCalculatorReturnsCurrentValueIfBoundaryPixelsIsEmpty() {
        IColourCalculator colourCalculator = new DefaultColourCalculator(null);
        float value = colourCalculator.CalculateWeightedColourFromPixels(
                new Pixel(1, 2, 3),
                new ArrayList<>());
        float expected = 3;

        Assert.assertEquals(expected, value, 0.001);
    }
}
