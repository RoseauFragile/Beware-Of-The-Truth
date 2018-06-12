package bewareofthetruth.view.main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface IEventPerformer {

	public int getMousePosX();

	public int getMousePosY();

	public void keyBoardEventPerform(KeyEvent keyCode);

	public void mouseEventPerform(MouseEvent mouseCode);
}
