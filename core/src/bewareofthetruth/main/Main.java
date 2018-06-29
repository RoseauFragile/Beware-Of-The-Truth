package bewareofthetruth.main;

import static bewareofthetruth.model.util.Constants.PPM;

import java.sql.SQLException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.model.utils.Direction;
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
	public void resize(final int width, final int height) {
		this.constant.CAMERA.resize(width / 2, height / 2);
	}

	@Override
	public void render() {

		this.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.constant.CAMERA.getCamera().update();
		this.constant.TMR.render(this.constant.LEVEL.getLayerBackground());

		// BATCH START
		this.constant.BATCH.begin();
		this.modelFacade.getBewareOfTruthModel().drawBatch();
		// BATCH END
		this.constant.BATCH.end();

		this.constant.TMR.render(this.constant.LEVEL.getLayerAfterBackground());
		
		this.constant.DEBUG_RENDERER.render(this.constant.WORLD, this.constant.CAMERA.getCamera().combined.scl(PPM));
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
		this.constant.WORLD.dispose();
		this.constant.DEBUG_RENDERER.dispose();
		this.constant.BATCH.dispose();
		//this.constant.TMR.dispose();
		this.constant.TILEDMAP.dispose();
	}

	public void update(final float delta) {
		float stateTime = this.modelFacade.getBewareOfTruthModel().getStateTime();
		this.constant.WORLD.step(1 / 60f, 6, 2);
		this.modelFacade.getBewareOfTruthModel().setStateTime(stateTime += delta);
		this.inputUpdate(delta);
		this.constant.CAMERA.cameraUpdate(this.constant.PLAYER.getBody().getPosition());
		this.constant.TMR.setView(this.constant.CAMERA.getCamera());
		this.constant.BATCH.setProjectionMatrix(this.constant.CAMERA.getCamera().combined);
		this.constant.LEVEL.updateEnnemiesMovement();
	}

	public void inputUpdate(final float delta) {

		int horizontalForce = 0;
		int verticalForce = 0;
		boolean moving = false;

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			verticalForce += 1;
			this.constant.PLAYER.moveUp();
			this.constant.PLAYER.setLastDirection(Direction.UP);
			moving = true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			verticalForce -= 1;
			this.constant.PLAYER.moveDown();
			this.constant.PLAYER.setLastDirection(Direction.DOWN);
			moving = true;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 1;
			this.constant.PLAYER.moveLeft();
			this.constant.PLAYER.setLastDirection(Direction.LEFT);
			moving = true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 1;
			this.constant.PLAYER.moveRight();
			this.constant.PLAYER.setLastDirection(Direction.RIGHT);
			moving = true;
		}

		if (!moving) {
			switch (this.constant.PLAYER.getLastDirection()) {
			case UP:
				this.constant.PLAYER.idleUp();
				break;
			case DOWN:
				this.constant.PLAYER.idleDown();
				break;
			case RIGHT:
				this.constant.PLAYER.idleRight();
				break;
			case LEFT:
				this.constant.PLAYER.idleLeft();
				break;
			}
		}

		this.constant.PLAYER.getBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);
	}
}
