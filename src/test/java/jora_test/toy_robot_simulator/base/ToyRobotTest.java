package jora_test.toy_robot_simulator.base;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import org.junit.Assert.*;

import jora_test.toy_robot_simulator.guides.basic.MoveGuide;
import jora_test.toy_robot_simulator.guides.basic.CommandGuide;

@RunWith(Parameterized.class)
public class ToyRobotTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {

			// Valid command set

			/* Single move */ { 
				"PLACE 0,0,NORTH;MOVE;REPORT", 
				"PLACE 0,0,NORTH;MOVE;REPORT: 0,1,NORTH" },
			/* Single turn */ { 
				"PLACE 0,0,NORTH;LEFT;REPORT", 
				"PLACE 0,0,NORTH;LEFT;REPORT: 0,0,WEST" },
			/* Multiple internal moves */ {
				"PLACE 1,2,EAST;MOVE;MOVE;LEFT;MOVE;REPORT", 
				"PLACE 1,2,EAST;MOVE;MOVE;LEFT;MOVE;REPORT: 3,3,NORTH" },
			/* Single edge move */ { 
				"PLACE 0,0,SOUTH;MOVE;REPORT", 
				"PLACE 0,0,SOUTH;MOVE;REPORT: 0,0,SOUTH" },
			/* Multiple edge moves */ {
				"PLACE 4,4,NORTH;MOVE;REPORT;RIGHT;MOVE;REPORT;RIGHT;MOVE;MOVE;MOVE;REPORT", 
				"PLACE 4,4,NORTH;MOVE;REPORT: 4,4,NORTH;RIGHT;MOVE;REPORT: 4,4,EAST;RIGHT;MOVE;MOVE;MOVE;REPORT: 4,1,SOUTH" },
			/* Lower Case Commands */ {
				"place 1,2,east;move;move;left;right;report", 
				"PLACE 1,2,EAST;MOVE;MOVE;LEFT;RIGHT;REPORT: 3,2,EAST" },
			/* Mix Case Commands */ {
				"PlaCE 1,2,East;move;MOVE;left;Right;rePort", 
				"PLACE 1,2,EAST;MOVE;MOVE;LEFT;RIGHT;REPORT: 3,2,EAST" },

			// Command sets with ignored/invalid commands
				
			/* Empty */ { "", "" },
			/* No placement */ {
				"MOVE;MOVE;LEFT;MOVE;REPORT",
				"MOVE;MOVE;LEFT;MOVE;REPORT: Robot not placed in table." },
			/* Delayed placement */ {
				"MOVE;RIGHT;LEFT;MOVE;REPORT;PLACE 1,2,EAST;MOVE;REPORT",
				"MOVE;RIGHT;LEFT;MOVE;REPORT: Robot not placed in table.;PLACE 1,2,EAST;MOVE;REPORT: 2,2,EAST" },
			/* Invalid placement */{
				"PLACE 0,0,SOMEWHERE;LEFT;REPORT",
				"PLACE 0,0,SOMEWHERE;LEFT;REPORT: Robot not placed in table." },
			/* Invalid placement command length */{
				"PLACE 0,0,NORTH OUT;LEFT;REPORT",
				"PLACE 0,0,NORTH OUT;LEFT;REPORT: Robot not placed in table." },
			/* Invalid placement params length */{
				"PLACE 0,0,NORTH,EXTRA;LEFT;REPORT",
				"PLACE 0,0,NORTH,EXTRA;LEFT;REPORT: Robot not placed in table." },
			/* Invalid replacement coordinate*/{
				"PLACE 0,0,EAST;MOVE;MOVE;LEFT;MOVE;PLACE 5,5,EAST;LEFT;REPORT",
				"PLACE 0,0,EAST;MOVE;MOVE;LEFT;MOVE;PLACE 5,5,EAST;LEFT;REPORT: 2,1,WEST" },
			/* Invalid replacement direction */{
				"PLACE 0,0,EAST;MOVE;MOVE;LEFT;MOVE;PLACE 0,0,SOMEHWERE;LEFT;REPORT",
				"PLACE 0,0,EAST;MOVE;MOVE;LEFT;MOVE;PLACE 0,0,SOMEHWERE;LEFT;REPORT: 2,1,WEST" },
			/* Invalid commans */{
				"PLACE 1,2,EAST;MOVE;MOVE;INVALID;MOVE;REPORT",
				"PLACE 1,2,EAST;MOVE;MOVE;INVALID(Ignored);MOVE;REPORT: 4,2,EAST" }
		});
	}

	private String commandsInput;
	private String reportsExpected;

	public ToyRobotTest(String input, String expected) {
		this.commandsInput = input;
		this.reportsExpected = expected;
	}

	@Test
	public void test() {
		int dimension = 5;
		ToyRobot toyRobot = new ToyRobot(new CommandGuide(), new MoveGuide(new Point(dimension, dimension)));

		String[] commands = this.commandsInput.split(";");
		String[] reportsExp = this.reportsExpected.split(";");

		for(int index = 0; index < commands.length; index++) {			
			String outcome = toyRobot.receiveCommand(commands[index]);
			assertEquals(reportsExp[index], outcome);
		}
	}
}