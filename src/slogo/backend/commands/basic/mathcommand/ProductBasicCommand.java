package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class ProductBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) * parameters.get(1);
    }
}
