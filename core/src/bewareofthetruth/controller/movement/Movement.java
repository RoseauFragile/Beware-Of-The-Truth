package bewareofthetruth.controller.movement;

import org.newdawn.slick.geom.Vector2f;

public class Movement {
	private Vector2f vector;

	public Movement() {
		this.setVector(0, 0);
	}

	public Movement(final float x, final float y) {
		this.setVector(x, y);
	}

	public void addToMovement(final Vector2f vector) {
		this.getVector().add(vector);
	}

	public Vector2f getVector() {
		return this.vector;
	}

	public void setVector(final float x, final float y) {
		this.vector.set(x, y);
	}

	public void setVector(final Vector2f vector) {
		this.vector.set(vector);
	}
}
