package jora_test.toy_robot_simulator.guides.basic;

import java.awt.Point;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Assert.*;

import jora_test.toy_robot_simulator.base.movement.IMover;
import jora_test.toy_robot_simulator.base.movement.ITurner;

public class MoveGuideTest {

	@Test
	public void testIsValidLocation() {
		MoveGuide mg = new MoveGuide(new Point(5,5));

		assertTrue(mg.isValidLocation(new Point(0,0)));
		assertTrue(mg.isValidLocation(new Point(4,4)));
		assertTrue(mg.isValidLocation(new Point(0,4)));
		assertTrue(mg.isValidLocation(new Point(4,0)));
		assertTrue(mg.isValidLocation(new Point(3,3)));

		assertFalse(mg.isValidLocation(new Point(5,0)));
		assertFalse(mg.isValidLocation(new Point(0,5)));
		assertFalse(mg.isValidLocation(new Point(-1,0)));
		assertFalse(mg.isValidLocation(new Point(-1,-1)));
		assertFalse(mg.isValidLocation(new Point(5,5)));
		assertFalse(mg.isValidLocation(null));
	}
	
	@Test
	public void testIsValidDirection() {
		MoveGuide mg = new MoveGuide(new Point(5,5));

		assertTrue(mg.isValidDirection("NORTH"));
		assertTrue(mg.isValidDirection("SOUTH"));
		assertTrue(mg.isValidDirection("EAST"));
		assertTrue(mg.isValidDirection("WEST"));
		
		assertFalse(mg.isValidDirection(""));
		assertFalse(mg.isValidDirection("OTHER"));
		assertFalse(mg.isValidDirection(null));
	}

	@Test
	public void testGetMover() {
		MoveGuide mg = new MoveGuide(new Point(5,5));

		assertSamePoint(new Point(0,1), mg.getMover("NORTH").moveFrom(new Point(0,0)));
		assertSamePoint(new Point(0,4), mg.getMover("NORTH").moveFrom(new Point(0,4)));
		
		assertSamePoint(new Point(0,0), mg.getMover("SOUTH").moveFrom(new Point(0,1)));
		assertSamePoint(new Point(0,0), mg.getMover("SOUTH").moveFrom(new Point(0,0)));
		
		assertSamePoint(new Point(1,0), mg.getMover("EAST").moveFrom(new Point(0,0)));
		assertSamePoint(new Point(4,0), mg.getMover("EAST").moveFrom(new Point(4,0)));
		
		assertSamePoint(new Point(3,0), mg.getMover("WEST").moveFrom(new Point(4,0)));
		assertSamePoint(new Point(0,0), mg.getMover("WEST").moveFrom(new Point(0,0)));

		assertSamePoint(new Point(0,4), mg.getMover("OTHER").moveFrom(new Point(0,4)));
		assertSamePoint(new Point(0,4), mg.getMover(null).moveFrom(new Point(0,4)));
	}

	@Test
	public void testGetTurner() {
		MoveGuide mg = new MoveGuide(new Point(5,5));

		assertEquals("EAST", mg.getTurner("RIGHT").turnFrom("NORTH"));
		assertEquals("SOUTH", mg.getTurner("RIGHT").turnFrom("EAST"));
		assertEquals("WEST", mg.getTurner("RIGHT").turnFrom("SOUTH"));
		assertEquals("NORTH", mg.getTurner("RIGHT").turnFrom("WEST"));
		
		assertEquals("WEST", mg.getTurner("LEFT").turnFrom("NORTH"));
		assertEquals("SOUTH", mg.getTurner("LEFT").turnFrom("WEST"));
		assertEquals("EAST", mg.getTurner("LEFT").turnFrom("SOUTH"));
		assertEquals("NORTH", mg.getTurner("LEFT").turnFrom("EAST"));
		
		assertEquals("NORTH", mg.getTurner("OTHER").turnFrom("NORTH"));
	}

	private void assertSamePoint(Point a, Point b) {
		assertEquals(a.x, b.x);
		assertEquals(a.y, b.y);
	}
}