package bewareofthetruth.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Window;

import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.inventory.StatusSubject;
import bewareofthetruth.utility.Utility;

public class PauseUI extends Window implements StatusSubject{

	public PauseUI() {
		super("", Utility.STATUSUI_SKIN, "pauseMenu");
	}

	@Override
	public void addObserver(StatusObserver statusObserver) {
	}

	@Override
	public void removeObserver(StatusObserver statusObserver) {
	}

	@Override
	public void removeAllObservers() {
	}

	@Override
	public void notify(int value, StatusEvent event) {
	}

}
