package bewareofthetruth.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bewareofthetruth.contract.controller.IGameController;
import bewareofthetruth.contract.renderer.IGameRenderer;

public class GameRenderer implements IGameRenderer {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private IGameController gameController;
	
	@Override
	public void init() {
	}

	@Override
	public void render() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
	}

}
