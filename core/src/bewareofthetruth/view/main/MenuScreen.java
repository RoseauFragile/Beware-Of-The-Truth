package bewareofthetruth.view.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MenuScreen implements Screen {

	private final IGraphicsBuilder	menuBuilder;
	BewareOfTruth					game;

	public MenuScreen(BewareOfTruth bewareOfTruth) {
		this.game = bewareOfTruth;
		this.menuBuilder = new MenuBuilder();

	}

	/*
	 * @Override public void init() { super.init(); this.menuBuilder.init(this.gsm);
	 * }
	 * 
	 * @Override public void update(float dt) {
	 * this.menuBuilder.applyModelToGraphics(); }
	 * 
	 * @Override public void draw() { System.out.println("Menu is drawing...");
	 * this.menuBuilder.applyModelToGraphics(); }
	 * 
	 * @Override public void handleInput() {
	 * 
	 * }
	 * 
	 * @Override public void dispose() { }
	 */

	public MenuScreen() {
		this.menuBuilder = new MenuBuilder();
	}

	@Override
	public void show() {
		Gdx.graphics.setTitle("Game Menu");
		this.menuBuilder.init(this.game);

	}

	@Override
	public void render(float delta) {
		this.menuBuilder.applyModelToGraphics();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
