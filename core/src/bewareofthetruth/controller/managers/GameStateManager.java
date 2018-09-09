package bewareofthetruth.controller.managers;

import java.util.Stack;
import bewareofthetruth.controller.states.GameState;
import bewareofthetruth.controller.states.PlayState;
import bewareofthetruth.controller.states.SplashState;
import bewareofthetruth.controller.states.State;
import bewareofthetruth.main.Main;

public class GameStateManager {

	// Application Reference
	private final Main game;

	private Stack <GameState>	states;
	private boolean paused;

	public GameStateManager(final Main main) {
		this.game = main;
		this.states = new Stack <GameState>();
		this.setState(State.SPLASHSCREEN);
	}

	public Main game() {
		return game;
	}

	public void update(float delta) {
		states.peek().update(delta);
	}

	public void render() {
		states.peek().render();
	}

	public void dispose() {
		for (GameState gs : states) {
			gs.dispose();
		}
		states.clear();
	}

	public void setState(State state) {
		if (states.size() >= 1) {
			states.pop().dispose();
		}
		states.push(getState(state));
	}

	private GameState getState(State state) {
		switch (state) {
		case SPLASHSCREEN:
			return new SplashState(this);
		case MAINMENUSCREEN:
			return new SplashState(this);
		case PLAY:
			return new PlayState(this);
		default:
			break;
		}
		return null;
	}
	
	public void resize(int w, int h) {
		states.peek().resize(w,h);
	}

	public void pause() {
		states.peek().pause();
		this.paused = true;
	}

	public void resume() {
		states.peek().resume();
		this.paused = false;
	}
}
