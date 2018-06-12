package bewareofthetruth.controller.movement;

public abstract class MovementFactory {
	private static final float unit = 1.0f;

	public static Movement downMovement() {
		return new Movement(0, unit);
	}

	public static Movement leftMovement() {
		return new Movement(-unit, 0);
	}

	public static Movement noMovement() {
		return new Movement(0, 0);
	}

	public static Movement rightMovement() {
		return new Movement(unit, 0);
	}

	public static Movement upMovement() {
		return new Movement(0, -unit);
	}

}
