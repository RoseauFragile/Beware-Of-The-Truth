package bewareofthetruth.view.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class BewareOfTruth extends Game {

	public static final int	MENU			= 1;
	public static final int	PLAY			= 2;
	public static final int	PAUSE			= 3;
	public static final int	SPLASHSCREEN	= 0;

	@Override
	public void create() {
		Gdx.graphics.setTitle("Beware of truth");
		this.setState(SPLASHSCREEN);
	}

	public void setState(int state) {
		switch (state) {
		case SPLASHSCREEN:
			this.setScreen(new SplashScreen(this));
			break;
		case MENU:
			this.setScreen(new MenuScreen(this));
		case PLAY:
			break;
		case PAUSE:
			break;
		}
	}

}
