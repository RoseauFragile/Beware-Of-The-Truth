package bewareofthetruth.view.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class BewareOfTruth extends Game {

	public static final int		MENU			= 1;
	public static final int		PLAY			= 2;
	public static final int		PAUSE			= 3;
	public static final int		SPLASHSCREEN	= 0;
	public static final int		WINDOWS_WIDTH	= 720;
	public static final int		WINDOWS_HEIGHT	= 480;
	public OrthographicCamera	camera;

	@Override
	public void create() {
		Gdx.graphics.setTitle("Beware of truth");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WINDOWS_WIDTH, WINDOWS_HEIGHT);
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
