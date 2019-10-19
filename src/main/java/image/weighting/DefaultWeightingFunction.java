package image.weighting;

import image.matrix.IMatrixElement;

/**
 * An inverse weighted distance algorithm to find the weight between two matrix elements based
 * on the distance, a value z and a value epsilon
 */
public class DefaultWeightingFunction implements IWeightingFunction {
    private double zeroCorrector;
    private double distanceExponent;

    public DefaultWeightingFunction(double distanceExponent, double zeroCorrector) {
        if (zeroCorrector <= 0) {
            throw new IllegalArgumentException("Value of epsilon cannot be less than or equal to 0");
        }
        this.distanceExponent = distanceExponent;
        this.zeroCorrector = zeroCorrector;
    }

    @Override
    public double findWeight(IMatrixElement elementOne, IMatrixElement elementTwo) {
        return 1 / (Math.pow(elementOne.distanceFrom(elementTwo), distanceExponent) + zeroCorrector);
    }
}
