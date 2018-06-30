package bewareofthetruth.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

import bewareofthetruth.controller.managers.GameStateManager;


public class SplashState extends GameState {

	private GameStateManager	gsm;
	private Sprite				sprite;
	private Image				splashImage;
	protected Viewport			viewport;
	float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
	public static final float		GAME_WIDTH	= 64;
	public static final float		GAME_HEIGHT	= 32;
	protected Stage					stage;

	public SplashState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		this.init();
	}

	public void init() {

		this.getConstant().CAMERA.setSplashCamera(aspectRatio, GAME_WIDTH, GAME_HEIGHT);
		stage = new Stage(this.getConstant().CAMERA.getViewport());
		this.sprite = new Sprite(new Texture("sprite/Beware-Of-Truth.png"));
		Sprite menuSprite = new Sprite(new Texture("gameMenu.png"));
		Image menuImage = new Image(menuSprite);
		menuImage.setPosition(10, 0);
		menuImage.setSize(getGlobalWidth(), getGlobalHeight());
		this.splashImage = new Image(sprite);
		stage.addActor(splashImage);
		stage.addActor(menuImage);
		this.splashImage.setSize(0, 0);
		this.splashImage.setPosition(0, this.getGlobalHeight() + 10);
		/*
		 * stage.addActor(splashImage); splashImage.setSize(this.getGlobalWidth(),
		 * this.getGlobalHeight()); splashImage.setPosition(0, 0);
		 * 
		 * splashImage.addAction(Actions.sequence(Actions.alpha(1200),
		 * Actions.fadeIn(4.0f), Actions.delay(), Actions.fadeOut(4.0f), Actions.run(new
		 * Runnable() {
		 * 
		 * @Override public void run() { System.out.println("Menu loading...");
		 * game.setState(BewareOfTruth.MENU); } })));
		 */

		splashImage.addAction(Actions
				.sequence(Actions.parallel(Actions.sizeTo(this.getGlobalWidth() - 10, this.getGlobalHeight() - 10, 5f),
						Actions.moveTo(5f, 5, 5f)), Actions.run(new Runnable() {
							@Override
							public void run() {
								System.out.println("Menu loading...");
								gsm.setState(State.MAINMENUSCREEN);
							}
						})));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.getConstant().CAMERA.getCamera().update();
		
		this.getConstant().BATCH.begin();
		
		stage.draw();
		System.out.println("CameraX : " + splashImage.getX() + " CameraY : " + splashImage.getY());
		//this.getConstant().BATCH.setProjectionMatrix(this.getConstant().CAMERA.getCamera().combined);
		
		this.getConstant().BATCH.end();
	}

	@Override
	public void update(float delta) {
		stage.act(delta);
		if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
			this.gsm.setState(State.PLAY);
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	private float getGlobalHeight() {
		return GAME_HEIGHT;
	}

	private float getGlobalWidth() {
		return GAME_WIDTH;
	}

	@Override
	public void resize(int w, int h) {
		this.gsm.game().getModelFacade().getBewareOfTruthModel().getCam().getCamera().setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
		this.gsm.game().getModelFacade().getBewareOfTruthModel().getCam().getViewport().update(w, h);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
