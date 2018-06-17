package bewareofthetruth.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.ICamera;
import static bewareofthetruth.model.util.Constants.PPM;
import static bewareofthetruth.model.util.Constants.SCALE;

public class Camera implements ICamera {

	private OrthographicCamera cam;
	private float rotationSpeed = 0.5f;
	private IBewareOfTruthModel bewareOfTruthModel;
	
	public Camera(float width, float height) {
		this.setCamera(new OrthographicCamera());
		this.getCamera().setToOrtho(false, width / SCALE, height / SCALE);
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
		this.getCamera().setToOrtho(false, width / SCALE, height/ SCALE);
	}
	
	public void cameraUpdate(float delta) {
		Vector3 position = this.getCamera().position;
		position.x = this.getBewareOfTruthModel().getPlayer().getBody().getPosition().x * PPM;
		position.y = this.getBewareOfTruthModel().getPlayer().getBody().getPosition().y * PPM;
		this.getCamera().position.set(position);
		this.getCamera().update();
	}

	public IBewareOfTruthModel getBewareOfTruthModel() {
		return bewareOfTruthModel;
	}

	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

}
