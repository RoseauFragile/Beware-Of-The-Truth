package bewareofthetruth.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IGameMenu;

public class GameMenu implements IGameMenu {

	private IBewareOfTruthModel	bewareOfTruthModel;
	private Sprite				sprite;
	private Texture				texture;

	public GameMenu() {
		// this.setTexture(new Texture("assets/gameMenu.png"));
		// this.setSprite(new Sprite(this.getTexture()));
	}

	@Override
	public IBewareOfTruthModel getBewareOfTruthModel() {
		return this.bewareOfTruthModel;
	}

	@Override
	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
