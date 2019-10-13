package slogo.backend.commands;

import java.util.List;
import java.util.Random;

public enum MathEnum {
    SUM("SUM", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) + parameters.get(1);
        }
    },

    DIFFERENCE("DIFFERENCE", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) - parameters.get(1);
        }
    },

    PRODUCT("PRODUCT", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) * parameters.get(1);
        }
    },

    QUOTIENT("QUOTIENT", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) / parameters.get(1);
        }
    },

    REMAINDER("REMAINDER", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) % parameters.get(1);
        }
    },

    MINUS("MINUS", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) * -1;
        }
    },

    RANDOM("RANDOM", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return new MathAndBooleanCommandsInterface() {
                @Override
                public double getValue(List<Double> parameters) {
                    Random random = new Random();
                    return parameters.get(0) * random.nextDouble();
                }
            };
        }
    },

    SINE("SINE", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.sin(Math.toRadians(parameters.get(0)));
        }
    },

    COSINE("COSINE", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.cos(Math.toRadians(parameters.get(0)));
        }
    },

    TANGENT("TANGENT", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.tan(Math.toRadians(parameters.get(0)));
        }
    },

    ARCTANGENT("ARCTANGENT", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.atan(Math.toRadians(parameters.get(0)));
        }
    },

    NATURALLOG("NATURALLOG", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.log(parameters.get(0));
        }
    },

    POWER("POWER", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.pow(parameters.get(0), parameters.get(1));
        }
    },

    PI("PI", 0) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> Math.PI;
        }
    };


    private String name;
    private int numParameters;

    MathEnum(String name, int numParameters) {
        this.name = name;
        this.numParameters = numParameters;
    }

    public String getName() {
        return name;}

    public int getNumParameters() {
        return numParameters;
    }

    public abstract MathAndBooleanCommandsInterface getCommandsInterface();
}
