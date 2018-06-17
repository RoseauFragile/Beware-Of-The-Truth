package bewareofthetruth.view.main;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class SplashScreen implements Screen {

	private SplashScreenBuilder	screenBuilder;
	private BewareOfTruth		game;
	private Texture				texture	= new Texture("sprite/paused.png");

	public SplashScreen(BewareOfTruth game) {
		this.game = game;
		this.screenBuilder = new SplashScreenBuilder(game);
	}

	@Override
	public void show() {
		this.screenBuilder.show();
	}

	@Override
	public void render(float delta) {
		this.screenBuilder.render(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		this.screenBuilder.dispose();
	}

}
