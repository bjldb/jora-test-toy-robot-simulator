package jora_test.toy_robot_simulator.guides.basic;

import java.util.HashMap;
import java.awt.Point;

import jora_test.toy_robot_simulator.base.ToyRobot;
import jora_test.toy_robot_simulator.base.command.ICommand;
import jora_test.toy_robot_simulator.base.command.ICommandGuide;

public class CommandGuide implements ICommandGuide{

	private HashMap<String, ICommand> commands;

	public CommandGuide() {
		this.commands = new HashMap<>();
		this.commands.put("PLACE", (robot, command) -> { this.placeRobot(robot, command); return command; } );
		this.commands.put("LEFT", (robot, command) -> { robot.turn(command); return command; });
		this.commands.put("RIGHT", (robot, command) -> { robot.turn(command); return command; });
		this.commands.put("MOVE", (robot, command) -> { robot.move(); return command; });
		this.commands.put("REPORT", (robot, command) -> command + ": " + robot.report());
	}

	public ICommand getExecutor(String commandString) {
		ICommand command = this.commands.get(commandString.trim().toUpperCase());
		return command != null ? command : this.defaultCommand();
	}

	private ICommand defaultCommand() {
		return ((robot, command) -> command + "(Ignored)");
	}

	private void placeRobot(ToyRobot robot, String command) {
		// PLACE X,Y,DIRECTION
		String[] commandParts = command.split(" ");
		if( commandParts.length == 2 ) {
			String params = commandParts[1];
			String[] paramsParts = params.split(",");
			if( paramsParts.length == 3) {
				int pointX = Integer.parseInt(paramsParts[0]);
				int pointY = Integer.parseInt(paramsParts[1]);
				String faceDirection = paramsParts[2];
				robot.place(new Point(pointX, pointY), faceDirection);
			}
		}
	}

}
