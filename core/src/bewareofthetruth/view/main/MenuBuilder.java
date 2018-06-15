package bewareofthetruth.view.main;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuBuilder implements IGraphicsBuilder {

	// private ShapeRenderer sr;
	private SpriteBatch					sb;
	private Texture						paused;
	public static OrthographicCamera	cam;

	@Override
	public void init() {
		// sr = new ShapeRenderer();
		paused = new Texture("sprite/paused.png");
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
	}

	@Override
	public void applyModelToGraphics() {
		cam.setToOrtho(false, getGlobalWidth(), getGlobalHeight());
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		sb.draw(paused, 0, 0);
		sb.end();
	}

	@Override
	public int getGlobalWidth() {
		return paused.getWidth();
	}

	@Override
	public int getGlobalHeight() {
		return paused.getHeight();
	}

}
