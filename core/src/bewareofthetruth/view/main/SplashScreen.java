package bewareofthetruth.view.main;

import com.badlogic.gdx.Screen;

public class SplashScreen implements Screen {

	private SplashScreenBuilder	screenBuilder;
	private BewareOfTruth		game;

	public SplashScreen(BewareOfTruth game) {
		this.setGame(game);
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
		this.screenBuilder.rezise(width, height);
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

	public BewareOfTruth getGame() {
		return game;
	}

	public void setGame(BewareOfTruth game) {
		this.game = game;
	}

}
