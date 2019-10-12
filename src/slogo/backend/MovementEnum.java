package slogo.backend;


import javafx.geometry.Point2D;

import java.util.List;

public enum MovementEnum {
    FORWARD("FORWARD", 1) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return parameters.get(0); }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    double angle = turtleSetting.getCurOrientation();
                    Point2D endingPos = turtleSetting.getCurCoordinate().add(
                            new Point2D(parameters.get(0) * Math.cos(angle), parameters.get(0) * Math.sin(angle)));
                    return new Movement(turtleSetting.getCurCoordinate(), endingPos, angle, turtleSetting.isVisible(), turtleSetting.isPenDown());
                }
            };
        }
    },
    BACKWARD("BACKWARD", 1) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return parameters.get(0); }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    double angle = (turtleSetting.getCurOrientation() + 180) % 360;
                    Point2D endingPos = turtleSetting.getCurCoordinate().add(
                            new Point2D(parameters.get(0) * Math.cos(angle), parameters.get(0) * Math.sin(angle)));
                    return new Movement(turtleSetting.getCurCoordinate(), endingPos, angle, turtleSetting.isVisible(), turtleSetting.isPenDown());
                }
            };
        }
    },
    RIGHT("RIGHT", 1) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return parameters.get(0); }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    double angle = ((turtleSetting.getCurOrientation() - parameters.get(0)) + 360) % 360;
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), angle,
                            turtleSetting.isVisible(), turtleSetting.isPenDown());
                }
            };
        }
    },
    LEFT("LEFT", 1) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
                    return parameters.get(0);
                }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    double angle = ((turtleSetting.getCurOrientation() + parameters.get(0))) % 360;
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), angle,
                            turtleSetting.isVisible(), turtleSetting.isPenDown());
                }
            };
        }
    },
    SETHEADING("SETHEADING", 1) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
                    double degreeTurned = Math.abs(turtleSetting.getCurOrientation() - parameters.get(0));
                    return degreeTurned <= 180 ? degreeTurned : 360 - degreeTurned;
                }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), parameters.get(0),
                            turtleSetting.isVisible(), turtleSetting.isPenDown());
                }
            };
        }
    },
    SETTOWARDS("SETTOWARDS", 2) {
        private double getSlope(Point2D curPos, Point2D targetPos) {
            return new Point2D(1, 0).angle(targetPos.getX() - curPos.getX(), targetPos.getY() - curPos.getY());
        }
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
                    double newOrientation = getSlope(turtleSetting.getCurCoordinate(), new Point2D(parameters.get(0), parameters.get(1)));
                    double degreeTurned = Math.abs(turtleSetting.getCurOrientation() - newOrientation);
                    return degreeTurned <= 180 ? degreeTurned : 360 - degreeTurned;
                }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    double newOrientation = getSlope(turtleSetting.getCurCoordinate(), new Point2D(parameters.get(0), parameters.get(1)));
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), newOrientation,
                            turtleSetting.isVisible(), turtleSetting.isPenDown());
                }
            };
        }
    };


    private String name;
    private int numParameters;

    MovementEnum(String name, int numParameters) {
        this.name = name;
        this.numParameters = numParameters;
    }

    public String getName() {return name;}

    public int getNumParameters() {return numParameters;}

    public abstract MovementCommandsInterface getCommandsInterface();
}
