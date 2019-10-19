package image.weighting;

import image.matrix.IMatrixElement;

/**
 * A function for finding the weight between two elements
 */
public interface IWeightingFunction {

    double findWeight(IMatrixElement elementOne, IMatrixElement elementTwo);
}
