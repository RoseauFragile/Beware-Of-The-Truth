package bewareofthetruth.view.gameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

import bewareofthetruth.view.main.BewareOfTruth;
import bewareofthetruth.view.main.GameStateManager;

public class SplashState extends GameState {

	private GameStateManager	gsm;
	private Sprite				sprite;
	private Image				splashImage;
	protected Viewport			viewport;

	public SplashState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		this.init();
	}

	public void init() {
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
		camera.update();
		stage.draw();
		System.out.println("CameraX : " + splashImage.getX() + " CameraY : " + splashImage.getY());
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void update(float delta) {
		stage.act(delta);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	private float getGlobalHeight() {
		return BewareOfTruth.GAME_HEIGHT;
	}

	private float getGlobalWidth() {
		return BewareOfTruth.GAME_WIDTH;
	}

}
