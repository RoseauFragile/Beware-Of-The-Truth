package bewareofthetruth.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

import bewareofthetruth.screens.GameOverScreen;
import bewareofthetruth.screens.LoadGameScreen;
import bewareofthetruth.screens.MainGameScreen;
import bewareofthetruth.screens.MainMenuScreen;
import bewareofthetruth.screens.MainOptionsScreen;
import bewareofthetruth.screens.NewGameScreen;

public class Main extends Game {
	private static MainGameScreen _mainGameScreen;
	private static MainMenuScreen _mainMenuScreen;
	private static LoadGameScreen _loadGameScreen;
	private static NewGameScreen _newGameScreen;
	private static GameOverScreen _gameOverScreen;
	//private static CutSceneScreen _cutSceneScreen;
	//private static CreditScreen _creditScreen;
	private static MainOptionsScreen _mainOptionsScreen;
	
	public static enum ScreenType{
		MainMenu,
		MainGame,
		LoadGame,
		NewGame,
		GameOver,
		WatchIntro,
		Credits, MainOptions
	}

	private Cursor customCursor;

	public Screen getScreenType(ScreenType screenType){
		switch(screenType){
			case MainMenu:
				return _mainMenuScreen;
			case MainGame:
				return _mainGameScreen;
			case LoadGame:
				return _loadGameScreen;
			case NewGame:
				return _newGameScreen;
			case MainOptions:
				return _mainOptionsScreen;
			case GameOver:
				return _gameOverScreen;
			//case WatchIntro:
			//	return _cutSceneScreen;
			//case Credits:
			//	return _creditScreen;
			default:
				return _mainGameScreen;
		}

	}

	@Override
	public void create(){
		 customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("sprites/hud/hud.cursor/Cursor.png")), 48, 26);
		Gdx.graphics.setCursor(customCursor);
		_mainGameScreen = new MainGameScreen(this);
		_mainMenuScreen = new MainMenuScreen(this);
		_loadGameScreen = new LoadGameScreen(this);
		_newGameScreen = new NewGameScreen(this);
		_gameOverScreen = new GameOverScreen(this);
		//_cutSceneScreen = new CutSceneScreen(this);
		//_creditScreen = new CreditScreen(this);
		_mainOptionsScreen = new MainOptionsScreen(this);
		setScreen(_mainMenuScreen);
	}

	@Override
	public void dispose(){
		_mainGameScreen.dispose();
		_mainMenuScreen.dispose();
		_loadGameScreen.dispose();
		_newGameScreen.dispose();
		_gameOverScreen.dispose();
		//_creditScreen.dispose();
		_mainOptionsScreen.dispose();
		customCursor.dispose();

	}

}

