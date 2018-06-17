package bewareofthetruth.view.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreenBuilder {

	private BewareOfTruth	game;
	private Texture			texture;
	private Stage			stage;
	Image					splashImage;

	public SplashScreenBuilder(BewareOfTruth game) {
		super();
		this.game = game;

	}

	public void show() {
		this.texture = new Texture("sprite/Beware-Of-Truth.png");
		this.stage = new Stage();
		this.splashImage = new Image(texture);
		stage.addActor(splashImage);
		splashImage.setSize(getGlobalWidth() / 4, getGlobalHeight() / 3);
		splashImage.addAction(
				Actions.sequence(Actions.alpha(0), Actions.fadeIn(5.0f), Actions.delay(1), Actions.run(new Runnable() {
					@Override
					public void run() {
						System.out.println("Menu loading...");
						game.setState(BewareOfTruth.MENU);
					}
				})));
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	public void dispose() {
		texture.dispose();
		stage.dispose();
	}

	public void rezise() {

	}

	private float getGlobalHeight() {
		return splashImage.getHeight();
	}

	private float getGlobalWidth() {
		return splashImage.getWidth();
	}

}
