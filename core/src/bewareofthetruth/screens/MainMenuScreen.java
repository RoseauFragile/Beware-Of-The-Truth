package bewareofthetruth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.main.Main;
import bewareofthetruth.main.Main.ScreenType;
import bewareofthetruth.utility.Utility;

public class MainMenuScreen extends GameScreen {

	private Stage _stage;
	private Main _game;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private SpriteBatch spriteBatch;

	
	public MainMenuScreen(Main game){
		_game = game;


		_stage = new Stage();
		Table table = new Table();

		ImageButton newGameButton = new ImageButton( Utility.STATUSUI_SKIN,"main-menu-new-game");
		ImageButton loadGameButton = new ImageButton( Utility.STATUSUI_SKIN,"main-menu-load-game");
		ImageButton optionsButton = new ImageButton( Utility.STATUSUI_SKIN,"main-menu-options");
		ImageButton exitButton = new ImageButton( Utility.STATUSUI_SKIN,"main-menu-exit");
		ImageButton watchIntroButton = new ImageButton( Utility.STATUSUI_SKIN,"main-menu-intro");

		spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("beware.png");
        backgroundSprite =new Sprite(backgroundTexture);
        backgroundSprite.setSize(1920, 1080);
		
		table.add(newGameButton);
		table.add(loadGameButton).row();
		table.add(optionsButton);
		table.add(exitButton);
		table.add(watchIntroButton);
		table.setPosition(300, 250);

		_stage.addActor(table);

		//Listeners
		newGameButton.addListener(new ClickListener() {
									  @Override
									  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										  return true;
									  }

									  @Override
									  public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										  _game.setScreen(_game.getScreenType(ScreenType.NewGame));
									  }
								  }
		);
		
		optionsButton.addListener(new ClickListener() {
			  @Override
			  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				  return true;
			  }

			  @Override
			  public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				  _game.setScreen(_game.getScreenType(ScreenType.MainOptions));
			  }
		  }
);
		
		loadGameButton.addListener(new ClickListener() {

									   @Override
									   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										   return true;
									   }

									   @Override
									   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										   _game.setScreen(_game.getScreenType(ScreenType.LoadGame));
									   }
								   }
		);

		exitButton.addListener(new ClickListener() {

								   @Override
								   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
									   return true;
								   }

								   @Override
								   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
									   Gdx.app.exit();
								   }

							   }
		);

		watchIntroButton.addListener(new ClickListener() {

										 @Override
										 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
											 return true;
										 }

										 @Override
										 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
											 MainMenuScreen.this.notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_INTRO);
											 _game.setScreen(_game.getScreenType(ScreenType.WatchIntro));
										 }
									 }
		);

		notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_INTRO);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		 backgroundSprite.draw(spriteBatch);
		 spriteBatch.end();
		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().setScreenSize(width, height);
	}

	@Override
	public void show() {
		notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_INTRO);
		Gdx.input.setInputProcessor(_stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		backgroundTexture.dispose();
		_stage.dispose();
	}
	


}

