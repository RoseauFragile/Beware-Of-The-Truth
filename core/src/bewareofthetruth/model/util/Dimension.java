package bewareofthetruth.model.util;

public class Dimension {

	private double width;

	private double height;

	public Dimension() {

	}

	public Dimension(float width, float height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
