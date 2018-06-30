package bewareofthetruth.view.main;

public abstract class GameState /*implements IGameState */{

	//protected GameStateManager gsm;
	// protected ModelFacade modelFacade;

	public GameState(/*GameStateManager gsm*/) {
		//this.gsm = gsm;
	}

	public void init() {
		/*
		 * try { this.modelFacade = new ModelFacade(); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */

	}

	public void update(float dt) {
	}

	public void draw() {

	}

	public void handleInput() {
	}

	public void dispose() {
	}

}
