package bewareofthetruth.view.main;

public interface IGraphicsBuilder {
	public void init(BewareOfTruth game);

	public void applyModelToGraphics();

	public int getGlobalWidth();

	public int getGlobalHeight();

}
