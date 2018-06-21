package bewareofthetruth.main;

import static bewareofthetruth.model.util.Constants.PPM;

import java.sql.SQLException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;

public class Main implements ApplicationListener {

	private IModelFacade modelFacade;

	@Override
	public void create() {

		try {
			this.modelFacade = new ModelFacade();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resize(int width, int height) {
		this.modelFacade.getBewareOfTruthModel().getCam().resize(width / 2, height / 2);
	}

	@Override
	public void render() {

		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.modelFacade.getBewareOfTruthModel().getCam().getCamera().update();
		this.modelFacade.getBewareOfTruthModel().getLevel().getTmr().render();

		// BATCH START
		this.modelFacade.getBewareOfTruthModel().getBatch().begin();
		this.modelFacade.getBewareOfTruthModel().getBatch().draw(
				this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getRegions()[0][0],
				this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getBody().getPosition().x * PPM
						- (this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getRegions()[0][0].getRegionWidth()
								/ 2),
				this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getBody().getPosition().y * PPM
						- (this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getRegions()[0][0].getRegionHeight()
								/ 2));

		// BATCH END
		this.modelFacade.getBewareOfTruthModel().getBatch().end();

		this.modelFacade.getBewareOfTruthModel().getDebugRenderer().render(
				this.modelFacade.getBewareOfTruthModel().getLevel().getWorld(),
				this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined.scl(PPM));
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		this.modelFacade.getBewareOfTruthModel().getLevel().getWorld().dispose();
		this.modelFacade.getBewareOfTruthModel().getDebugRenderer().dispose();
		this.modelFacade.getBewareOfTruthModel().getBatch().dispose();
		this.modelFacade.getBewareOfTruthModel().getLevel().getTmr().dispose();
		this.modelFacade.getBewareOfTruthModel().getLevel().getMap().getTiledMap().dispose();
	}

	public void update(float delta) {
		this.modelFacade.getBewareOfTruthModel().getLevel().getWorld().step(1 / 60f, 6, 2);
		inputUpdate(delta);
		this.modelFacade.getBewareOfTruthModel().getCam()
				.cameraUpdate(this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getBody().getPosition());
		this.modelFacade.getBewareOfTruthModel().getLevel().getTmr().setView(
				this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		this.modelFacade.getBewareOfTruthModel().getBatch()
				.setProjectionMatrix(this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined);
	}

	public void inputUpdate(float delta) {

		int horizontalForce = 0;
		int verticalForce = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			verticalForce += 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			verticalForce -= 1;
		}

		this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer().getBody().setLinearVelocity(horizontalForce * 5,
				verticalForce * 5);
	}
}
