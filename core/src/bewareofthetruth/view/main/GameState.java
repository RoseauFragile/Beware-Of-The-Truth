package bewareofthetruth.view.main;

public abstract class GameState implements IGameState {

	//protected GameStateManager gsm;
	// protected ModelFacade modelFacade;

	public GameState(/*GameStateManager gsm*/) {
		//this.gsm = gsm;
	}

	@Override
	public void init() {
		/*
		 * try { this.modelFacade = new ModelFacade(); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */

	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void draw() {

	}

	@Override
	public void handleInput() {
	}

	@Override
	public void dispose() {
	}

}
