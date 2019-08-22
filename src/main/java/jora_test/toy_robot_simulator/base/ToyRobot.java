package jora_test.toy_robot_simulator.base;

import java.awt.Point;

import jora_test.toy_robot_simulator.base.movement.ITurner;
import jora_test.toy_robot_simulator.base.movement.IMover;
import jora_test.toy_robot_simulator.base.movement.IMoveGuide;
import jora_test.toy_robot_simulator.base.command.ICommand;
import jora_test.toy_robot_simulator.base.command.ICommandGuide;

public class ToyRobot {
	
	private Point location;
	private String faceDirection;
	private IMoveGuide moveGuide;
	private ICommandGuide commandGuide;
	
	public ToyRobot(ICommandGuide commandGuide, IMoveGuide moveGuide) {
		this.moveGuide = moveGuide;
		this.commandGuide = commandGuide;
	}

	public String receiveCommand(String commandString) {
		String ucaseCommandString = commandString.trim().toUpperCase();
		String plainCommand = ucaseCommandString.split(" ")[0];
		String commandOutcome = "";
		if(!plainCommand.trim().isEmpty()) {
			ICommand command = this.commandGuide.getExecutor(plainCommand);
			commandOutcome = command.perform(this, ucaseCommandString);
		}
		return commandOutcome;
	}

	public void place(Point startLoc, String faceDirection) {
		if(this.moveGuide.isValidLocation(startLoc) && this.moveGuide.isValidDirection(faceDirection)) {
			this.location = startLoc;
			this.faceDirection = faceDirection;
		}
	}

	public void move() {
		if(this.isPlaced()) {
			this.location = this.getMover().moveFrom(this.location);
		}
	}
	
	public void turn(String turnDirection) {
		if(this.isPlaced()) {
			this.faceDirection = this.getTurner(turnDirection).turnFrom(this.faceDirection);
		}
	}

	public String report() {
		String reportString = "Robot not placed in table.";
		if(this.isPlaced()) {
			reportString = this.location.x + "," + this.location.y + "," + this.faceDirection;
		}
		return reportString;
	}

	private boolean isPlaced() {
		return this.moveGuide.isValidLocation(this.location) && this.moveGuide.isValidDirection(this.faceDirection);
	}

	private IMover getMover() {
		return this.moveGuide.getMover(this.faceDirection);
	}

	private ITurner getTurner(String turnDirection) {
		return this.moveGuide.getTurner(turnDirection);
	}
}
