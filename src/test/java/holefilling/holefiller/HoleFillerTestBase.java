package holefilling.holefiller;

import image.connectivity.IConnectivityType;
import image.holefilling.IBoundaryFinder;
import image.holefilling.IHoleInterlacer;
import image.matrix.pixel.IPixel;
import image.matrix.pixel.IPixelMatrix;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HoleFillerTestBase {

    protected IHoleInterlacer createMockHoleInterlacer(Set<IPixel> holeElements) {
        IHoleInterlacer holeInterlacer = mock(IHoleInterlacer.class);
        when(holeInterlacer.interlace(any(IPixelMatrix.class), any(IPixelMatrix.class))).thenReturn(holeElements);
        return holeInterlacer;
    }

    protected IBoundaryFinder createMockBoundaryFinder(
            IConnectivityType connectivityType,
            Set<IPixel> boundaryElements,
            Set<IPixel> holeBoundaryElements) {

        IBoundaryFinder boundaryFinder = mock(IBoundaryFinder.class);
        when(boundaryFinder.findBoundaryPixels(any(IPixelMatrix.class), eq(connectivityType))).thenReturn(boundaryElements);
        when(boundaryFinder.findBoundaryPixels(any(IPixelMatrix.class), any(Set.class), eq(connectivityType))).thenReturn(boundaryElements);
        when(boundaryFinder.findBoundaryConnectedHolePixels(any(IPixelMatrix.class), eq(connectivityType))).thenReturn(holeBoundaryElements);
        when(boundaryFinder.findBoundaryConnectedHolePixels(any(IPixelMatrix.class),any(Set.class), eq(connectivityType))).thenReturn(holeBoundaryElements);
        return boundaryFinder;
    }
}
