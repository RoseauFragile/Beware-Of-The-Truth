package bewareofthetruth.view.main;

public class GameStateManager {
	
	private IGameState gameState;
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int PAUSE = 2;
	
	public GameStateManager() {
		this.setState(MENU);
		
	}
	
	public void setState(int state) {
		if(this.gameState != null) {this.gameState.dispose();}
		switch(state) {
		case MENU:
			gameState = new MenuState(this);
			break;
		case PLAY:
			gameState = new PlayState(this);
			break;
		case PAUSE:
			gameState = new PauseState(this);
		break;
		}
		
	}
	
	public void update(float dt) {
		gameState.update(dt);
	}
	
	public void draw() {
		gameState.draw();
	}
	

}
