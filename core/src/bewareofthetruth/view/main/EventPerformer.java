package bewareofthetruth.view.main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.lwjgl.input.Mouse;

import bewareofthetruth.contract.maininterfaces.IOrderPerformer;

public class EventPerformer implements IEventPerformer {

	public EventPerformer(final IOrderPerformer orderPerformer) {

	}

	@Override
	public int getMousePosX() {
		// TODO Auto-generated method stub
		return Mouse.getX();
	}

	@Override
	public int getMousePosY() {
		// TODO Auto-generated method stub
		return Mouse.getY();
	}

	@Override
	public void keyBoardEventPerform(final KeyEvent keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEventPerform(final MouseEvent mouseCode) {
		// TODO Auto-generated method stub

	}

}
