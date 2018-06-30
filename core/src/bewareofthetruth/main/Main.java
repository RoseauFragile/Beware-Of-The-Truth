package bewareofthetruth.main;

import static bewareofthetruth.model.util.Constants.SCALE;
import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.controller.managers.GameStateManager;
import bewareofthetruth.model.ModelFacade;
import bewareofthetruth.model.util.Constants;

public class Main implements ApplicationListener {

	private IModelFacade modelFacade;
	private Constants constant;
	private GameStateManager gsm;

	@Override
	public void create() {

		try {
			this.modelFacade = new ModelFacade();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.constant = new Constants(this.modelFacade);
		this.gsm = new GameStateManager(this);
	}

	@Override
	public void resize(final int width, final int height) {
		this.gsm.resize((int) (width / SCALE), (int) (height / SCALE));
	}

	@Override
	public void render() {

		this.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.gsm.render();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
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
