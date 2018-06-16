package bewareofthetruth.view.main;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuBuilder implements IGraphicsBuilder {

	// private ShapeRenderer sr;
	private SpriteBatch					sb;
	private Texture						paused;
	public static OrthographicCamera	cam;
	private Stage						stage	= new Stage();
	Image								pausedImage;

	@Override
	public void init(final GameStateManager gsm) {
		// sr = new ShapeRenderer();
		paused = new Texture("sprite/paused.png");
		pausedImage = new Image(paused);
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		sb.setProjectionMatrix(cam.combined);
		stage.addActor(pausedImage);

		pausedImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(100.0f), Actions.delay(1),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						gsm.setState(0);
					}
				})));

	}

	@Override
	public void applyModelToGraphics() {
		cam.setToOrtho(false, paused.getWidth() * 2, paused.getHeight() * 2);
		sb.begin();
		pausedImage.draw(sb, 1);
		sb.setProjectionMatrix(cam.combined);
		sb.end();
		stage.act();

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
