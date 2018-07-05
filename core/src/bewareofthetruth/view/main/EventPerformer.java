package bewareofthetruth.view.main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import bewareofthetruth.contract.maininterfaces.IOrderPerformer;

public class EventPerformer implements IEventPerformer {

	public EventPerformer(final IOrderPerformer orderPerformer) {

	}

	@Override
	public int getMousePosX() {

		return 0;//Mouse.getX();
	}

	@Override
	public int getMousePosY() {

		return 0;//Mouse.getY();
	}

	@Override
	public void keyBoardEventPerform(final KeyEvent keyCode) {


	}

	@Override
	public void mouseEventPerform(final MouseEvent mouseCode) {


	}

}
