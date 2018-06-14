package bewareofthetruth.model.util;

import bewareofthetruth.contract.model.utils.IDimension;

public class Dimension implements IDimension {

	private float width;

	private float height;

	public Dimension() {

	}

	public Dimension(float height, float width) {
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}
}
