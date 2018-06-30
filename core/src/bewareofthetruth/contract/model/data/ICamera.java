package bewareofthetruth.contract.model.data;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public interface ICamera {
	public OrthographicCamera getCamera();

	public void setCamera(OrthographicCamera cam);

	public void setRotationSpeed(float rotationSpeed);

	public float getRotationSpeed();

	public void resize(float width, float height);

	public void cameraUpdate(Vector2 target);

	public IBewareOfTruthModel getBewareOfTruthModel();

	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel);
}
