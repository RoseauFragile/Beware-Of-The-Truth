package bewareofthetruth.main;

import com.badlogic.gdx.Game;

import bewareofthetruth.screens.MainGameScreen;

public class Main extends Game {

	public static final MainGameScreen MAIN_GAME_SCREEN = new MainGameScreen();
	
	@Override
	public void create() {
		setScreen(MAIN_GAME_SCREEN);
	}

	@Override
	public void dispose() {
		MAIN_GAME_SCREEN.dispose();
	}
}


