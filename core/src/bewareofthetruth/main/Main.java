package bewareofthetruth.main;

import com.badlogic.gdx.Game;

import bewareofthetruth.screens.MainGameScreen;

public class Main extends Game {

	private static MainGameScreen _mainGameScreen;
	
	@Override
	public void create() {
		_mainGameScreen = new MainGameScreen(this);
		setScreen(_mainGameScreen);
	}

	@Override
	public void dispose() {
		_mainGameScreen.dispose();
	}
}


