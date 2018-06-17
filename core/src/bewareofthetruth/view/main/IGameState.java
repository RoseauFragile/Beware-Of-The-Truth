package bewareofthetruth.view.main;

public interface IGameState {
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw();
	public abstract void handleInput();
	public abstract void dispose();

}
