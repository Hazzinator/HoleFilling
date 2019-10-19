package holefilling.holefiller;

import image.colour.DefaultColourCalculator;
import image.colour.IColourCalculator;
import image.connectivity.EightConnectivity;
import image.connectivity.FourConnectivity;
import image.connectivity.IConnectivityType;
import image.holefilling.holefiller.IHoleFiller;
import image.holefilling.holefiller.SlowHoleFiller;
import image.holefilling.IBoundaryFinder;
import image.holefilling.IHoleInterlacer;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;
import image.matrix.pixel.Pixel;
import image.weighting.DefaultWeightingFunction;
import image.weighting.IWeightingFunction;
import matrix.MatrixTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;

import java.util.*;

public class SlowHoleFillerTest extends HoleFillerTestBase {

    @Before
    public void SetUp() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void TestHoleFilledSlow4Connected() {
        IPixelMatrix interlacedMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd4ConnectedHole();

        interlacedMatrix.dump();

        Set<IPixel> holes = new HashSet<>(Arrays.asList(
                new Pixel(1, 2, -1),
                new Pixel(1, 3, -1),
                new Pixel(2, 2, -1),
                new Pixel(2, 3, -1),
                new Pixel(2, 4, -1),
                new Pixel(3, 2, -1),
                new Pixel(3, 3, -1),
                new Pixel(3, 4, -1),
                new Pixel(4, 1, -1),
                new Pixel(4, 2, -1),
                new Pixel(4, 3, -1),
                new Pixel(4, 4, -1)));

        Set<IPixel> boundary = new HashSet<>(Arrays.asList(
                new Pixel(0, 2, 3),
                new Pixel(0, 3, 4),
                new Pixel(1, 1, 8),
                new Pixel(1, 4, 9),
                new Pixel(2, 1, 12),
                new Pixel(2, 5, 13),
                new Pixel(3, 1, 15),
                new Pixel(3, 5, 16),
                new Pixel(4, 0, 17),
                new Pixel(4, 5, 18),
                new Pixel(5, 1, 20),
                new Pixel(5, 2, 21),
                new Pixel(5, 3, 22),
                new Pixel(5, 4, 23)));

        IHoleFiller holeFiller = createHoleFiller(new FourConnectivity(), 1.5, 0.0001, holes, boundary);

        holeFiller.fillHole(interlacedMatrix, MatrixTestFactory.CreatePixelMatrixWithAll1s());

        Assert.assertEquals(0, interlacedMatrix.findAllHolePixels().size());

        interlacedMatrix.dump();
    }

    @Test
    public void TestHoleFilledSlow8Connected() {
        IPixelMatrix interlacedMatrix = MatrixTestFactory.CreatePixelMatrixIncrementalValuesAnd8ConnectedHole();

        Set<IPixel> holes = new HashSet<>(Arrays.asList(
                new Pixel(1, 2, -1),
                new Pixel(1, 3, -1),
                new Pixel(2, 1, -1),
                new Pixel(2, 2, -1),
                new Pixel(2, 3, -1),
                new Pixel(2, 4, -1),
                new Pixel(3, 2, -1),
                new Pixel(3, 3, -1),
                new Pixel(3, 4, -1),
                new Pixel(4, 1, -1),
                new Pixel(4, 2, -1),
                new Pixel(4, 3, -1),
                new Pixel(4, 4, -1),
                new Pixel(5, 5, -1)));

        Set<IPixel> boundary = new HashSet<>(Arrays.asList(
                new Pixel(0, 1, 2),
                new Pixel(0, 2, 3),
                new Pixel(0, 3, 4),
                new Pixel(0, 4, 5),
                new Pixel(1, 0, 7),
                new Pixel(1, 1, 8),
                new Pixel(1, 4, 9),
                new Pixel(1, 5, 10),
                new Pixel(2, 0, 11),
                new Pixel(2, 5, 13),
                new Pixel(3, 0, 14),
                new Pixel(3, 1, 15),
                new Pixel(3, 5, 16),
                new Pixel(4, 0, 17),

                new Pixel(4, 5, 18),
                new Pixel(5, 0, 19),
                new Pixel(5, 1, 20),
                new Pixel(5, 2, 21),
                new Pixel(5, 3, 22),
                new Pixel(5, 4, 23)));

        IHoleFiller holeFiller = createHoleFiller(new EightConnectivity(), 1.5, 0.0001, holes, boundary);

        // Second parameter has no effect as the mock interlacer returns the holes above
        holeFiller.fillHole(interlacedMatrix, MatrixTestFactory.CreatePixelMatrixWithAll1s());

        Assert.assertEquals(0, interlacedMatrix.findAllHolePixels().size());
    }

    private IHoleFiller createHoleFiller(IConnectivityType connectivityType, double z, double epsilon,
                                         Set<IPixel> holes,
                                         Set<IPixel> boundary) {
        IHoleInterlacer holeInterlacer = super.createMockHoleInterlacer(holes);
        IBoundaryFinder boundaryFinder = super.createMockBoundaryFinder(connectivityType, boundary, holes);
        IWeightingFunction weightingFunction = new DefaultWeightingFunction(1.5, 0.00001);
        IColourCalculator colourCalculator = new DefaultColourCalculator(weightingFunction);

        return new SlowHoleFiller(holeInterlacer, boundaryFinder, connectivityType, colourCalculator);
    }
}
