package image.holefilling.holefiller;

import image.matrix.pixel.IPixelMatrix;

public interface IHoleFiller {
    void fillHole(IPixelMatrix inputMatrix, IPixelMatrix mask);
}
