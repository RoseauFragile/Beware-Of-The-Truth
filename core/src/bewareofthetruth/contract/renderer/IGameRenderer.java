package bewareofthetruth.contract.renderer;

import com.badlogic.gdx.utils.Disposable;

public interface IGameRenderer  extends Disposable {
	  public void init ();
	  public void render ();
	  public void resize (int width, int height);
	  public void dispose ();
}
