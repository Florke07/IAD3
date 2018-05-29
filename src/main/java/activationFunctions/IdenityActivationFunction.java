package activationFunctions;

public class IdenityActivationFunction implements ActivationFunction {
    @Override
    public double Value(double arg) {
        return arg;
    }

    @Override
    public double Derivative(double arg) {
        return 1;
    }
}
