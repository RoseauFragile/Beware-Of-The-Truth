package bewareofthetruth.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import bewareofthetruth.controller.managers.GameStateManager;

public class MainMenuState extends GameState {

	private Texture						paused;
	public static OrthographicCamera	cam;
	Image								pausedImage;

	protected MainMenuState(GameStateManager gsm) {
		super(gsm);

	}

	@Override
	public void init() {
		paused = new Texture("sprite/paused.png");
		pausedImage = new Image(paused);
		stage.addActor(pausedImage);
	}

	@Override
	public void update(float delta) {
		stage.act(delta);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int w, int h) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
