package bewareofthetruth.view.main;

import com.badlogic.gdx.Gdx;

public class MenuState extends GameState {

	private final IGraphicsBuilder	menuBuilder;
	GameStateManager				gsm;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
		Gdx.graphics.setTitle("Game Menu");
		this.menuBuilder = new MenuBuilder();

	}

	@Override
	public void init() {
		super.init();
		this.menuBuilder.init(this.gsm);
	}

	@Override
	public void update(float dt) {
		this.menuBuilder.applyModelToGraphics();
	}

	@Override
	public void draw() {
		this.menuBuilder.applyModelToGraphics();
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void dispose() {
	}

}
