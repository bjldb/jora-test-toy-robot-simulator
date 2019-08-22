package jora_test.toy_robot_simulator.base.command;

import java.awt.Point;
import jora_test.toy_robot_simulator.base.command.ICommand;

public interface ICommandGuide {
	public ICommand getExecutor(String commandString);
}
