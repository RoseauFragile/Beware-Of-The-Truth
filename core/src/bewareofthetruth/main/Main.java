package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bewareofthetruth.contract.controller.IGameController;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.renderer.IGameRenderer;
import bewareofthetruth.controller.GameController;
import bewareofthetruth.controller.managers.GameStateManager;
import bewareofthetruth.model.ModelFacade;
import bewareofthetruth.model.util.Constants;
import bewareofthetruth.view.GameRenderer;


public class Main implements ApplicationListener {

	private IModelFacade modelFacade;
	private Constants constant;
	private GameStateManager gsm;
	private IGameRenderer gameRenderer;
	private IGameController gameController;
	 private static final String TAG = Main.class.getName();

	@Override
	public void create() {
		this.gameController = new GameController();
		this.gameRenderer = new GameRenderer();
		try {
			this.modelFacade = new ModelFacade();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.constant = new Constants(this.modelFacade);
		this.gsm = new GameStateManager(this);
		this.gsm.resume();
	}

	@Override
	public void resize(final int width, final int height) {
		this.gsm.resize(width, height);
		this.gameRenderer.resize(width, height);
	}

	@Override
	public void render() {
		gameController.update(Gdx.graphics.getDeltaTime());
		this.update(Gdx.graphics.getDeltaTime());
		this.gsm.render();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		gameRenderer.render();
	}

	@Override
	public void pause() {
		this.gsm.pause();
	}

	@Override
	public void resume() {
		this.gsm.resume();
	}

	@Override
	public void dispose() {
		this.gsm.dispose();
		this.constant.BATCH.dispose();
		this.constant.SOUNDREADER.dispose();
		this.gameRenderer.dispose();
	}

	public void update(final float delta) {
		this.gsm.update(delta);
	}
	
	public IModelFacade getModelFacade() {
		return this.modelFacade;
	}
	
	public SpriteBatch getBatch() {
		return this.constant.BATCH;
	}
}
