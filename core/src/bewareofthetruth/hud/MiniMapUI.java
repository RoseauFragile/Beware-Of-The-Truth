package bewareofthetruth.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Window;

import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.utility.Utility;
import bewareofthetruth.inventory.StatusSubject;

public class MiniMapUI extends Window implements StatusSubject{

	public MiniMapUI() {
		super("", Utility.STATUSUI_SKIN, "minimap");
		
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
