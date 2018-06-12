package bewareofthetruth.model.util;

import org.newdawn.slick.geom.Vector2f;

import bewareofthetruth.contract.model.utils.IPosition;

public class Position implements IPosition {

	private float x;
	private float y;
	private float maxX;
	private float maxY;
	private Vector2f vector;

	public Position() {
		this.setVector();
	}

	@Override
	public Vector2f getPosition() {
		return this.vector;
	}

	@Override
	public void setVector() {
		this.vector = new Vector2f();
	}

	@Override
	public void addVector(Vector2f vectorToAdd) {
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
		return this.vector.getX();
	}

	@Override
	public float getY() {
		return this.vector.getY();
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
		this.x = x;
		this.vector.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
		this.vector.y = y;
	}

}
