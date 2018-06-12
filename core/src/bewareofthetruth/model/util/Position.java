package bewareofthetruth.model.util;

import com.badlogic.gdx.math.Vector2;
import bewareofthetruth.contract.model.utils.IPosition;

public class Position implements IPosition {

	private float maxX;
	private float maxY;
	private Vector2 vector;

	public Position() {
		this.setVector();
	}

	@Override
	public Vector2 getPosition() {
		return this.vector;
	}

	@Override
	public void setVector() {
		this.vector = new Vector2();
	}

	@Override
	public void addVector(Vector2 vectorToAdd) {
		this.vector.add(vectorToAdd);
	}

	@Override
	public float getMaxX() {
		return this.maxX;
	}

	@Override
	public float getMaxY() {
		return this.maxY;
	}

	@Override
	public float getX() {
		return this.vector.x;
	}

	@Override
	public float getY() {
		return this.vector.y;
	}

	@Override
	public void setMaxX(float maxX) {
		this.maxX = maxX;
	}

	@Override
	public void setMaxY(float maxY) {
		this.maxY = maxY;
	}

	@Override
	public void setX(float x) {
		this.vector.x = x;
	}

	@Override
	public void setY(float y) {
		this.vector.y = y;
	}

}
