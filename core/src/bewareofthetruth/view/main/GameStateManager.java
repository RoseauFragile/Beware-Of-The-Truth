package bewareofthetruth.view.main;

import java.util.Stack;

import bewareofthetruth.view.gameState.GameState;
import bewareofthetruth.view.gameState.SplashState;
import bewareofthetruth.view.gameState.State;

public class GameStateManager {

	// Application Reference
	private final BewareOfTruth	game;

	private Stack <GameState>	states;

	public GameStateManager(final BewareOfTruth game) {
		this.game = game;
		this.states = new Stack <GameState>();
		this.setState(State.SPLASHSCREEN);
	}

	public BewareOfTruth game() {
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
		default:
			break;
		}
		return null;
	}
}
