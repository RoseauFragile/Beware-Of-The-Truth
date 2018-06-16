package bewareofthetruth.view.main;

public interface IGraphicsBuilder {
	public void init(GameStateManager gsm);

	public void applyModelToGraphics();

	public int getGlobalWidth();

	public int getGlobalHeight();

}
