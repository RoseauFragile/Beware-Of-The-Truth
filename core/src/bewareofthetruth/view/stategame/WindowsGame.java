package bewareofthetruth.view.stategame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import bewareofthetruth.view.main.IEventPerformer;

public class WindowsGame extends StateBasedGame implements KeyListener, MouseListener {

	public WindowsGame(final String title, final IEventPerformer performer) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(final GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

	}

}
