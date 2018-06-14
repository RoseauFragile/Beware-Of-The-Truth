package bewareofthetruth.controller.movement;

import com.badlogic.gdx.math.Vector2;

public class Movement {
	private Vector2 vector;

	public Movement() {
		this.setVector(0, 0);
	}

	public Movement(final float x, final float y) {
		this.setVector(x, y);
	}

	public void addToMovement(final Vector2 vector) {
		this.getVector().add(vector);
	}

	public Vector2 getVector() {
		return this.vector;
	}

	public void setVector(final float x, final float y) {
		this.vector.set(x, y);
	}

	public void setVector(final Vector2 vector) {
		this.vector.set(vector);
	}
}
