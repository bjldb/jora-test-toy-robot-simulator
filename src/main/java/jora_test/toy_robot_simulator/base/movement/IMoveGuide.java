package jora_test.toy_robot_simulator.base.movement;

import java.awt.Point;
import jora_test.toy_robot_simulator.base.movement.IMover;
import jora_test.toy_robot_simulator.base.movement.ITurner;

public interface IMoveGuide {
	public IMover getMover(String facingDirection);
	public ITurner getTurner(String turnDirection);
	public boolean isValidLocation(Point locToValidate);
	public boolean isValidDirection(String faceDirection);
}
