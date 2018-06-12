package bewareofthetruth.contract.model.data;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;

public interface IGame {

	public void buildLevel(ILevel level);

	public String getGameName();

	public ILevel getLevel();

	public int getScreenHeight();

	public int getScreenWidth();

	public boolean hasFocus();

	public boolean isMouseGrabbed();

	public void setDefaultMouseCursor();

	public void setIcon(String arg0) throws SlickException;

	public void setIcons(String[] arg0) throws SlickException;

	public void setLevel(ILevel level);

	public void setLevelHaveChanged();

	public void setMouseCursor(Cursor arg0, int arg1, int arg2) throws SlickException;

	public void setMouseCursor(Image arg0, int arg1, int arg2) throws SlickException;

	public void setMouseCursor(ImageData arg0, int arg1, int arg2) throws SlickException;

	public void setMouseCursor(String arg0, int arg1, int arg2) throws SlickException;

	public void setMouseGrabbed(boolean arg0);

}
