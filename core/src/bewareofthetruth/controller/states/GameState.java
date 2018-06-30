package bewareofthetruth.controller.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bewareofthetruth.controller.managers.GameStateManager;
import bewareofthetruth.main.Main;
import bewareofthetruth.model.util.Constants;

public abstract class GameState {

	// References
	protected GameStateManager		gsm;
	protected Main					game;
	protected SpriteBatch			batch;
	protected OrthographicCamera	camera;
	protected Stage					stage;
	private Constants constant;

	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		this.game = gsm.game();
		this.setConstant(new Constants(this.game.getModelFacade()));
		batch = game.getBatch();
		camera = game.getModelFacade().getBewareOfTruthModel().getCam().getCamera();
//stage = new Stage(game.getModelFacade().getBewareOfTruthModel().getCam().getViewport());
	}

	public abstract void init();

	public abstract void update(float delta);

	public abstract void render();

	public abstract void dispose();

	public abstract void resize(int w, int h);

	public abstract void pause();

	public abstract void resume();

	public Constants getConstant() {
		return constant;
	}

	public void setConstant(Constants constant) {
		this.constant = constant;
	}
}
