package bewareofthetruth.contract.model.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface IGameMenu {

	IBewareOfTruthModel getBewareOfTruthModel();

	void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel);
	
	public Sprite getSprite();
	

	public void setSprite(Sprite sprite);

	public Texture getTexture();

	public void setTexture(Texture texture);

}
