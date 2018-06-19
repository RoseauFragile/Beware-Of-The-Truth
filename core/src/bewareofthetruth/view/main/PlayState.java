package bewareofthetruth.view.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends GameState {
	private SpriteBatch sb;

	public PlayState(/*GameStateManager gsm*/) {
		//super(gsm);
		init();
		Gdx.graphics.setTitle("Beware of truth");
	}

	@Override
	public void init() {
		super.init();
		sb = new SpriteBatch();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		System.out.println("Play is drawing...");
		sb.begin();
		sb.end();
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
