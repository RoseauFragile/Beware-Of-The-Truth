package bewareofthetruth.model;

import static bewareofthetruth.model.util.Constants.SCALE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.ICamera;

public class Camera implements ICamera {

	private OrthographicCamera	cam;
	private Viewport			viewport;
	private Viewport			splashViewport;
	private float				rotationSpeed	= 0.5f;
	private IBewareOfTruthModel	bewareOfTruthModel;

	

	public Camera(float width, float height) {
		this.setCamera(new OrthographicCamera());
		this.setViewport(new ScreenViewport(cam));
		cam.setToOrtho(false, Gdx.graphics.getWidth()/ SCALE, Gdx.graphics.getHeight() / SCALE);
		SpriteBatch batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		cam.update();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
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

	public void cameraUpdate(Vector2 target) {
		System.out.println((target.x + "   " + target.y));
		Vector3 position = this.getCamera().position;
		position.x = cam.position.x + (target.x * 32 - cam.position.x) * .05f;
		position.y = cam.position.y + (target.y * 32 - cam.position.y) * .05f;
		this.getCamera().position.set(position);
		this.getCamera().update();
	}

	public IBewareOfTruthModel getBewareOfTruthModel() {
		return bewareOfTruthModel;
	}

	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public Viewport getSplashViewport() {
		return splashViewport;
	}

	public void setSplashViewport(Viewport splashViewport) {
		this.splashViewport = splashViewport;
	}

	@Override
	public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
		this.cam = orthographicCamera;
	}
}
