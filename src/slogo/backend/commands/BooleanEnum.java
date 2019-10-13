package slogo.backend.commands;

public enum BooleanEnum {
    LESS("LESS", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) < parameters.get(1) ? 1d : 0d;
        }
    },

    GREATER("GREATER", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) > parameters.get(1) ? 1d : 0d;
        }
    },

    EQUAL("EQUAL", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) == parameters.get(1) ? 1d : 0d;
        }
    },

    NOTEQUAL("NOTEQUAL", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) != parameters.get(1) ? 1d : 0d;
        }
    },

    AND("AND", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> (parameters.get(0) != 0 && parameters.get(1) != 0) ? 1d : 0d;
        }
    },

    OR("OR", 2) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> (parameters.get(0) != 0 || parameters.get(1) != 0) ? 1d : 0d;
        }
    },

    NOT("NOT", 1) {
        @Override
        public MathAndBooleanCommandsInterface getCommandsInterface() {
            return parameters -> parameters.get(0) == 0 ? 1d : 0d;
        }
    };


    private String name;
    private int numParameters;

    BooleanEnum(String name, int numParameters) {
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
