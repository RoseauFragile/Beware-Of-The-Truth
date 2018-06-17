package bewareofthetruth.view.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuBuilder implements IGraphicsBuilder {

	// private ShapeRenderer sr;
	private SpriteBatch					sb;
	private Texture						paused;
	public static OrthographicCamera	cam;
	private Stage						stage	= new Stage();
	Image								pausedImage;

	@Override
	public void init(final BewareOfTruth gsm) {
		// sr = new ShapeRenderer();
		paused = new Texture("sprite/paused.png");
		pausedImage = new Image(paused);
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		stage.addActor(pausedImage);
		pausedImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		;

	}

	@Override
	public void applyModelToGraphics() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.setToOrtho(false, paused.getWidth(), paused.getHeight());
		stage.act();
		stage.draw();

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
