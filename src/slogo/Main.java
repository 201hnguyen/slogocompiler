package slogo;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.paint.Color;
import javafx.stage.*;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.TurtleManager;
import slogo.frontend.Visualization;

import java.util.ArrayList;
import java.util.List;

    @Override
    public void start(Stage stage) throws Exception {
        Visualization visualization = new Visualization(stage);
        visualization.helpHost = getHostServices();
        TurtleManager turtleManager = visualization.getTurtle();
        CommandBlockManager commandBlockManager = new CommandBlockManager("Forward 50", turtleManager);
        commandBlockManager.executeInstructionBlock();
    }

        commandBlockManager.executeInstructionBlock();
    }
}
