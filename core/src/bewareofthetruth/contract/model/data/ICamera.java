package bewareofthetruth.contract.model.data;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface ICamera {
public OrthographicCamera getCamera();
public void setCamera(OrthographicCamera cam);
public void setRotationSpeed(float rotationSpeed);
public float getRotationSpeed();
}
