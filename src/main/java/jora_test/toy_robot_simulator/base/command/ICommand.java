package jora_test.toy_robot_simulator.base.command;

import jora_test.toy_robot_simulator.base.ToyRobot;

public interface ICommand {
	public abstract String perform(ToyRobot toyRobot, String command);
}
