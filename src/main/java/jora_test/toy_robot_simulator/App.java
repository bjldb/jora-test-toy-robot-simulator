package jora_test.toy_robot_simulator;

import java.awt.Point;
import java.io.IOException;
import java.util.List;

import jora_test.toy_robot_simulator.base.ToyRobot;
import jora_test.toy_robot_simulator.guides.basic.MoveGuide;
import jora_test.toy_robot_simulator.guides.basic.CommandGuide;

import jora_test.toy_robot_simulator.util.FileReaderUtil;

public class App {

	public static final int DEFAULT_DIMENSION = 5;

	public static void main(String[] args) {
		String commandSourceFile = args[0];
		
		try {
			int dimension = DEFAULT_DIMENSION;

			List<String> lines = FileReaderUtil.processLines(commandSourceFile);

			MoveGuide moveGuide = new MoveGuide(new Point(dimension, dimension));
			CommandGuide commandGuide = new CommandGuide();
			
			ToyRobot robot = new ToyRobot(commandGuide, moveGuide);
			
			for(String command : lines) {
				System.out.println(robot.receiveCommand(command));
			}
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
