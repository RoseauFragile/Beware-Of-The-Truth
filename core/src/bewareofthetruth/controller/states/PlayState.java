package bewareofthetruth.controller.states;

import static bewareofthetruth.model.util.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import bewareofthetruth.controller.managers.GameStateManager;
import bewareofthetruth.model.util.Constants;

public class PlayState extends GameState {

	public PlayState(final GameStateManager gsm) {
		super(gsm);
		this.init();
		Gdx.graphics.setTitle("Beware of -The- truth");
	}

	@Override
	public void init() {

		this.getConstant().CAMERA.setPlayCamera();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void update(final float delta) {

		this.setConstant(new Constants(this.game.getModelFacade()));
		float stateTime = this.game.getModelFacade().getBewareOfTruthModel().getStateTime();
		this.getConstant().WORLD.step(1 / 60f, 6, 2);
		this.game.getModelFacade().getBewareOfTruthModel().setStateTime(stateTime += delta);
		this.inputUpdate(delta);
		this.getConstant().CAMERA.cameraUpdate(this.getConstant().PLAYER.getBody().getPosition());
		this.getConstant().TMR.setView(this.getConstant().CAMERA.getCamera());
		this.getConstant().BATCH.setProjectionMatrix(this.getConstant().CAMERA.getCamera().combined);
		this.getConstant().RAYHANDLER.update();
		this.getConstant().RAYHANDLER.setCombinedMatrix(this.getConstant().CAMERA.getCamera().combined.cpy().scl(PPM));
		this.getConstant().PLAYER.update();
		this.getConstant().LEVEL.updateEnemiesMovement();
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// System.out.println(" LEVEL DE l'UPDATE =" +
		// this.getConstant().LEVEL.getId());
		this.getConstant().CAMERA.getCamera().update();
		this.getConstant().TMR.render(this.getConstant().LEVEL.getLayerBackground());
		this.getConstant().BATCH.begin();
		this.game.getModelFacade().getBewareOfTruthModel().drawBatch();
		this.getConstant().BATCH.end();
		this.getConstant().TMR.render(this.getConstant().LEVEL.getLayerAfterBackground());

		// DEBUG RENDERER
		// this.getConstant().DEBUG_RENDERER.render(this.getConstant().WORLD,
		// this.getConstant().CAMERA.getCamera().combined.scl(PPM));
		this.getConstant().RAYHANDLER.render();
	}

	@Override
	public void dispose() {
		this.getConstant().BATCH.dispose();
		this.getConstant().WORLD.dispose();
		this.getConstant().DEBUG_RENDERER.dispose();
		this.getConstant().TILEDMAP.dispose();
		this.getConstant().RAYHANDLER.dispose();
	}

	@Override
	public void resize(final int w, final int h) {
		this.getConstant().CAMERA.resize(w / 2, h / 2);
	}

	public void inputUpdate(final float delta) {

		int horizontalForce = 0;
		int verticalForce = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			verticalForce += 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			verticalForce -= 1;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 1;
		}

		this.getConstant().PLAYER.getBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5); // TODO MAGIC
																										// NUMBER
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
