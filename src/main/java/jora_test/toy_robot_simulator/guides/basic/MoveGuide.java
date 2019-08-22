package jora_test.toy_robot_simulator.guides.basic;

import java.awt.Point;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

import jora_test.toy_robot_simulator.base.movement.IMover;
import jora_test.toy_robot_simulator.base.movement.IMoveGuide;
import jora_test.toy_robot_simulator.base.movement.ITurner;

public class MoveGuide implements IMoveGuide{

	private Point dimension;
	private List<String> directions;
	private HashMap<String, IMover> moves;
	private HashMap<String, ITurner> turns;
	
	public MoveGuide(Point dimension) {
		this.directions = Arrays.asList("NORTH", "EAST", "SOUTH", "WEST");
		this.dimension = dimension;

		this.moves = new HashMap<>();
		this.moves.put("NORTH", (pointFrom) -> new Point(pointFrom.x, Math.min(pointFrom.y + 1, dimension.y - 1)));
		this.moves.put("SOUTH", (pointFrom) -> new Point(pointFrom.x, Math.max(pointFrom.y - 1, 0)));
		this.moves.put("EAST", (pointFrom) -> new Point(Math.min(pointFrom.x + 1, dimension.x - 1), pointFrom.y));
		this.moves.put("WEST", (pointFrom) -> new Point(Math.max(pointFrom.x - 1, 0), pointFrom.y));

		this.turns = new HashMap<>();
		this.turns.put("LEFT", (from) -> this.directions.get(Math.floorMod(this.directions.indexOf(from) - 1, this.directions.size())));
		this.turns.put("RIGHT", (from) -> this.directions.get(Math.floorMod(this.directions.indexOf(from) + 1, this.directions.size())));
	}

	public IMover getMover(String facingDirection) {
		IMover mover = this.moves.get(facingDirection);
		return mover != null ? mover : this.defaultMover();
	}
	
	public ITurner getTurner(String turnDirection) {
		ITurner turner = this.turns.get(turnDirection);
		return turner != null ? turner : this.defaultTurner();
	}

	public boolean isValidLocation(Point locToValidate) {
		return locToValidate != null
			&& locToValidate.x >= 0 
			&& locToValidate.x < this.dimension.x
			&& locToValidate.y >= 0 
			&& locToValidate.y < this.dimension.y;
	}

	public boolean isValidDirection(String faceDirection) {
		return this.directions.contains(faceDirection);
	}

	private IMover defaultMover() {
		return ((start) -> start);
	}

	private ITurner defaultTurner() {
		return ((from) -> from);
	}
}
