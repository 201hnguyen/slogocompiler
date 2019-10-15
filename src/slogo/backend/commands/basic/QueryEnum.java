package slogo.backend.commands.basic;

import slogo.backend.commands.basic.QueryCommandsInterface;
import slogo.backend.utils.TurtleSetting;

public enum QueryEnum {

    XCOORDINATE("XCOORDINATE") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return turtleSetting -> turtleSetting.getCurCoordinate().getX();
        }
    },

    YCOORDINATE("YCOORDINATE") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return turtleSetting -> turtleSetting.getCurCoordinate().getY();
        }
    },

    HEADING("HEADING") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return turtleSetting -> turtleSetting.getCurOrientation();
        }
    },

    ISPENDOWN("ISPENDOWN") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return turtleSetting -> turtleSetting.isPenDown() ? 1d : 0d;
        }
    },

    ISSHOWING("ISSHOWING") {
        @Override
        public QueryCommandsInterface getCommandsInterface() {
            return turtleSetting -> turtleSetting.isVisible() ? 1d : 0d;
        }
    };

    private String name;

    QueryEnum(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public abstract QueryCommandsInterface getCommandsInterface();
}
