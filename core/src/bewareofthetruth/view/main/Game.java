package bewareofthetruth.view.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Game implements ApplicationListener {

	GameStateManager gsm;

	@Override
	public void create() {
		gsm = new GameStateManager();
		gsm.init();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
