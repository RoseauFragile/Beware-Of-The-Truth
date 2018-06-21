package bewareofthetruth.main;

import static bewareofthetruth.model.util.Constants.PPM;

import java.sql.SQLException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;
import bewareofthetruth.model.util.Constants;

public class Main implements ApplicationListener {

	private IModelFacade modelFacade;
	private Constants constant;

	@Override
	public void create() {
		
		try {
			this.modelFacade = new ModelFacade();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.constant = new Constants(this.modelFacade);
	}

	@Override
	public void resize(int width, int height) {
		this.constant.CAMERA.resize(width / 2, height / 2);
	}

	@Override
	public void render() {

		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.constant.CAMERA.getCamera().update();
		this.constant.TMR.render();
		// BATCH START
		this.constant.BATCH.begin();
		this.constant.BATCH.draw(this.constant.PLAYER.getRegions()[0][0], this.constant.PLAYER.getBody().getPosition().x * PPM 
							  - (this.constant.PLAYER.getRegions()[0][0].getRegionWidth() / 2),
						         this.constant.PLAYER.getBody().getPosition().y * PPM
						      - (this.constant.PLAYER.getRegions()[0][0].getRegionHeight() / 2));
		// BATCH END
		this.constant.BATCH.end();

		this.constant.DEBUG_RENDERER.render(this.constant.WORLD,this.constant.CAMERA.getCamera().combined.scl(PPM));
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { Gdx.app.exit(); }
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		this.constant.WORLD.dispose();
		this.constant.DEBUG_RENDERER.dispose();
		this.constant.BATCH.dispose();
		this.constant.TMR.dispose();
		this.constant.TILEDMAP.dispose();
	}

	public void update(float delta) {
		this.constant.WORLD.step(1 / 60f, 6, 2);
		inputUpdate(delta);
		this.constant.CAMERA.cameraUpdate(this.constant.PLAYER.getBody().getPosition());
		this.constant.TMR.setView( this.constant.CAMERA.getCamera().combined, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		this.constant.BATCH.setProjectionMatrix(this.constant.CAMERA.getCamera().combined);
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
		this.constant.PLAYER.getBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);
	}
}
