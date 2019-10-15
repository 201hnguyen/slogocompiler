package slogo.backend.commands.basic;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.MovementCommandsInterface;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

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
                    return new Movement(turtleSetting.getCurCoordinate(), endingPos, angle, turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
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
                    return new Movement(turtleSetting.getCurCoordinate(), endingPos, angle, turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
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
                            turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
                }
            };
        }
    },
    LEFT("LEFT", 1) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return parameters.get(0); }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    double angle = ((turtleSetting.getCurOrientation() + parameters.get(0))) % 360;
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), angle,
                            turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
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
                            turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
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
                            turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
                }
            };
        }
    },
    SETPOSITION("SETPOSITION", 2) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
                    return turtleSetting.getCurCoordinate().distance(parameters.get(0), parameters.get(1));
                }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), new Point2D(parameters.get(0), parameters.get(1)),
                            turtleSetting.getCurOrientation(), turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
                }
            };
        }
    },
    PENDOWN("PENDOWN", 0) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return 1; }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(),
                            turtleSetting.getCurOrientation(), turtleSetting.isVisible(), true, false);
                }
            };
        }
    },
    PENUP("PENUP", 0) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return 0; }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(),
                            turtleSetting.getCurOrientation(), turtleSetting.isVisible(), false, false);
                }
            };
        }
    },
    SHOWTURTLE("SHOWTURTLE", 0) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return 1; }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(),
                            turtleSetting.getCurOrientation(), true, turtleSetting.isPenDown(), false);
                }
            };
        }
    },
    HIDETURTLE("HIDETURTLE", 0) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return 0; }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(),
                            turtleSetting.getCurOrientation(), false, turtleSetting.isPenDown(), false);
                }
            };
        }
    },
    HOME("HOME", 0) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
                    return turtleSetting.getCurCoordinate().distance(0, 0);
                }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), new Point2D(0, 0), 90,
                            turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
                }
            };
        }
    },
    CLEARSCREEN("CLEARSCREEN", 0) {
        @Override
        public MovementCommandsInterface getCommandsInterface() {
            return new MovementCommandsInterface() {
                @Override
                public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
                    return turtleSetting.getCurCoordinate().distance(0, 0);
                }

                @Override
                public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
                    return new Movement(turtleSetting.getCurCoordinate(), new Point2D(0, 0), 90,
                            turtleSetting.isVisible(), turtleSetting.isPenDown(), true);
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
