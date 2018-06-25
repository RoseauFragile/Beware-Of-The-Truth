package bewareofthetruth.view.gameState;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bewareofthetruth.view.main.BewareOfTruth;
import bewareofthetruth.view.main.GameStateManager;

public abstract class GameState {

	// References
	protected GameStateManager		gsm;
	protected BewareOfTruth			game;
	protected SpriteBatch			batch;
	protected OrthographicCamera	camera;
	protected Stage					stage;

	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		this.game = gsm.game();
		batch = game.getBatch();
		camera = game.getCamera();
		stage = new Stage(game.viewport);
	}

	public abstract void init();

	public abstract void update(float delta);

	public abstract void render();

	public abstract void dispose();

}
