package bewareofthetruth.view.main;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BewareOfTruth extends ApplicationAdapter {

	public static boolean			DEBUG		= false;

	// Game Information
	public static final String		TITLE		= "BewareOfTruth";

	public static final float		GAME_WIDTH	= 64;
	public static final float		GAME_HEIGHT	= 32;
	public static final float		SCALE		= 1f;

	public static Engine			ashley;
	public static AssetManager		assets;

	protected OrthographicCamera	camera;
	public Viewport					viewport;
	private SpriteBatch				batch;

	private GameStateManager		gsm;

	@Override
	public void create() {
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		camera = new OrthographicCamera(32 * aspectRatio, 32 * aspectRatio);
		camera.position.set(GAME_WIDTH / 2, GAME_HEIGHT / 2, 0);
		viewport = new FillViewport(GAME_WIDTH, GAME_HEIGHT, camera);
		viewport.apply();
		batch = new SpriteBatch();
		gsm = new GameStateManager(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.begin();
		// batch.setProjectionMatrix(camera.combined);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, BewareOfTruth.GAME_WIDTH, BewareOfTruth.GAME_HEIGHT);
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		gsm.dispose();
		batch.dispose();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
