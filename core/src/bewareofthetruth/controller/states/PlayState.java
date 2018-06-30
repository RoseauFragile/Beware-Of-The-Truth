package bewareofthetruth.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.controller.managers.GameStateManager;
import static bewareofthetruth.model.util.Constants.PPM;

public class PlayState extends GameState {

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
		Gdx.graphics.setTitle("Beware of -The- truth");
	}

	@Override
	public void init() {
		
		this.getConstant().CAMERA.setPlayCamera();
		//stage = new Stage(this.getConstant().CAMERA.getSplashViewport());
	}

	@Override
	public void update(float delta) {
		
		float stateTime = this.game.getModelFacade().getBewareOfTruthModel().getStateTime();
		this.getConstant().WORLD.step(1 / 60f, 6, 2);
		this.game.getModelFacade().getBewareOfTruthModel().setStateTime(stateTime += delta);
		this.inputUpdate(delta);
		this.getConstant().CAMERA.cameraUpdate(this.getConstant().PLAYER.getBody().getPosition());
		this.getConstant().TMR.setView(this.getConstant().CAMERA.getCamera());
		this.getConstant().BATCH.setProjectionMatrix(this.getConstant().CAMERA.getCamera().combined);
		this.getConstant().LEVEL.updateEnnemiesMovement();
	}

	@Override
	public void render() {
		
		this.getConstant().CAMERA.getCamera().update();
		this.getConstant().TMR.render(this.getConstant().LEVEL.getLayerBackground());
		this.getConstant().BATCH.begin();
		this.game.getModelFacade().getBewareOfTruthModel().drawBatch();
		this.getConstant().BATCH.end();
		this.getConstant().TMR.render(this.getConstant().LEVEL.getLayerAfterBackground());
		this.getConstant().DEBUG_RENDERER.render(this.getConstant().WORLD, this.getConstant().CAMERA.getCamera().combined.scl(PPM));
	}

	@Override
	public void dispose() {
		this.getConstant().BATCH.dispose();
		this.getConstant().WORLD.dispose();
		this.getConstant().DEBUG_RENDERER.dispose();
		this.getConstant().TILEDMAP.dispose();
	}

	@Override
	public void resize(int w, int h) {
		this.getConstant().CAMERA.resize(w / 2, h / 2);
	}
	
	public void inputUpdate(final float delta) {

		int horizontalForce = 0;
		int verticalForce = 0;
		boolean moving = false;

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			verticalForce += 1;
			this.getConstant().PLAYER.moveUp();
			this.getConstant().PLAYER.setLastDirection(Direction.UP);
			moving = true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			verticalForce -= 1;
			this.getConstant().PLAYER.moveDown();
			this.getConstant().PLAYER.setLastDirection(Direction.DOWN);
			moving = true;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 1;
			this.getConstant().PLAYER.moveLeft();
			this.getConstant().PLAYER.setLastDirection(Direction.LEFT);
			moving = true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 1;
			this.getConstant().PLAYER.moveRight();
			this.getConstant().PLAYER.setLastDirection(Direction.RIGHT);
			moving = true;
		}

		if (!moving) {
			switch (this.getConstant().PLAYER.getLastDirection()) {
			case UP:
				this.getConstant().PLAYER.idleUp();
				break;
			case DOWN:
				this.getConstant().PLAYER.idleDown();
				break;
			case RIGHT:
				this.getConstant().PLAYER.idleRight();
				break;
			case LEFT:
				this.getConstant().PLAYER.idleLeft();
				break;
			}
		}
		this.getConstant().PLAYER.getBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
