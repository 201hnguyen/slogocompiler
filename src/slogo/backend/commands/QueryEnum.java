package slogo.backend.commands;

import slogo.backend.utils.TurtleSetting;

import java.util.List;

public enum QueryEnum {

    XCOR("XCOR") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return new QueryCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting) {
                    return turtleSetting.getCurCoordinate().getX();
                }
            };
        }
    },

    YCOR("YCOR") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return new QueryCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting) {
                    return turtleSetting.getCurCoordinate().getY();
                }
            };
        }
    },

    HEADING("HEADING") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return new QueryCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting) {
                    return turtleSetting.getCurOrientation();
                }
            };
        }
    },

    PENDOWN("PENDOWN") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return new QueryCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting) {
                    return turtleSetting.isPenDown() ? 1d : 0d;
                }
            };
        }
    },

    SHOWING("SHOWING") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return new QueryCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting) {
                    return turtleSetting.isVisible() ? 1d : 0d;
                }
            };
        }
    };

    private String name;

    QueryEnum(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public abstract QueryCommandsInterface getCommandsInterface();
}
