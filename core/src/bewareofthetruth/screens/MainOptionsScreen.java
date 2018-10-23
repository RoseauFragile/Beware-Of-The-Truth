package bewareofthetruth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.main.Main;
import bewareofthetruth.main.Main.ScreenType;
import bewareofthetruth.utility.Utility;

public class MainOptionsScreen extends GameScreen{
	private Stage _stage;
	private Main _game;
	
	public MainOptionsScreen(Main game){
		_game = game;

		//creation
		_stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);


		TextButton demoButton = new TextButton("Options en constructions", Utility.STATUSUI_SKIN);
		TextButton optionsButton = new TextButton("Options", Utility.STATUSUI_SKIN);
		TextButton returnButton = new TextButton("Exit",Utility.STATUSUI_SKIN);


		//Layout
		table.add(demoButton).spaceBottom(75).row();
		table.add(optionsButton).spaceBottom(10).row();
		table.add(returnButton).spaceBottom(10).row();

		_stage.addActor(table);

		//Listeners
		demoButton.addListener(new ClickListener() {
									  @Override
									  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										  return true;
									  }

									  @Override
									  public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										  
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

		returnButton.addListener(new ClickListener() {

								   @Override
								   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
									   return true;
								   }

								   @Override
								   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
									   _game.setScreen(_game.getScreenType(ScreenType.MainMenu));
								   }

							   }
		);


		notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().setScreenSize(width, height);
	}

	@Override
	public void show() {
		notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
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
		_stage.dispose();
	}

}
