package slogo.backend.external_api;

import slogo.backend.utils.Movement;
import slogo.backend.commands.Interpreter;

import java.util.List;
import java.util.Map;

public class BackendManager {
    Interpreter myInterpreter;

    public BackendManager() {
        myInterpreter = new Interpreter();
    }

    public void setCommand(List<String> movementsList) {
        myInterpreter.setCommands(movementsList);
    }

    public boolean hasNextMovement() {
        return myInterpreter.hasNextMovement();
    }

    public Movement getNextMovement() {
        return myInterpreter.getMovement();
    }

    public Map<String, Double> getMyVariables() {
        return myInterpreter.getUserVariables();
    }
}
