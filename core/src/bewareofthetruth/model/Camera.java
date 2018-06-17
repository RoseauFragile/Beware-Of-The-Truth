package bewareofthetruth.model;

import com.badlogic.gdx.graphics.OrthographicCamera;

import bewareofthetruth.contract.model.data.ICamera;

public class Camera implements ICamera {

	private OrthographicCamera cam;
	private float rotationSpeed = 0.5f;     
	
	public Camera(float width, float height) {
		this.setCamera(new OrthographicCamera());
		this.getCamera().setToOrtho(false, width, height);
	}
	
	@Override
	public OrthographicCamera getCamera() {
		return this.cam;
	}

	@Override
	public void setCamera(OrthographicCamera cam) {
		this.cam = cam;
	}

	public float getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}
	
	public void resize(float width, float height) {
		this.getCamera().setToOrtho(false, width, height);
	}

}
