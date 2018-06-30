package bewareofthetruth.view.gameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.controller.managers.GameStateManager;
import bewareofthetruth.controller.states.GameState;
import bewareofthetruth.model.util.Constants;
import static bewareofthetruth.model.util.Constants.PPM;

public class PlayState extends GameState {

	private Constants constant;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
		Gdx.graphics.setTitle("Beware of -The- truth");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.constant = new Constants(this.game.getModelFacade());
	}

	@Override
	public void update(float delta) {
		
		float stateTime = this.game.getModelFacade().getBewareOfTruthModel().getStateTime();
		this.constant.WORLD.step(1 / 60f, 6, 2);
		this.game.getModelFacade().getBewareOfTruthModel().setStateTime(stateTime += delta);
		this.inputUpdate(delta);
		this.constant.CAMERA.cameraUpdate(this.constant.PLAYER.getBody().getPosition());
		this.constant.TMR.setView(this.constant.CAMERA.getCamera());
		this.constant.BATCH.setProjectionMatrix(this.constant.CAMERA.getCamera().combined);
		this.constant.LEVEL.updateEnnemiesMovement();
	}

	@Override
	public void render() {
		
		this.constant.CAMERA.getCamera().update();
		this.constant.TMR.render(this.constant.LEVEL.getLayerBackground());
		this.constant.BATCH.begin();
		this.game.getModelFacade().getBewareOfTruthModel().drawBatch();
		this.constant.BATCH.end();
		this.constant.TMR.render(this.constant.LEVEL.getLayerAfterBackground());
		this.constant.DEBUG_RENDERER.render(this.constant.WORLD, this.constant.CAMERA.getCamera().combined.scl(PPM));
	}

	@Override
	public void dispose() {
		this.constant.BATCH.dispose();
		this.constant.WORLD.dispose();
		this.constant.DEBUG_RENDERER.dispose();
		this.constant.TILEDMAP.dispose();
	}

	@Override
	public void resize(int w, int h) {
		this.constant.CAMERA.resize(w / 2, h / 2);
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
