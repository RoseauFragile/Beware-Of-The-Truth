package bewareofthetruth.contract.model.gameMecanism;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.gameMecanism.behaviors.IBounceStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IDodgeStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IMoveStrategy;
import bewareofthetruth.contract.model.utils.IAudio;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.IPosition;
import com.badlogic.gdx.graphics.g2d.Animation;

public interface IEntity {

	public IMoveStrategy getMoveStrategy();

	public void setMoveStrategy(IMoveStrategy moveStrategy);

	public IDodgeStrategy getDodgeStrategy();

	public void setDodgeStrategy(IDodgeStrategy dodgeStrategy);

	public IBounceStrategy getBounceStrategy();

	public void setBounceStrategy(IBounceStrategy bounceStrategy);

	public IDimension getDimension();

	public void setDimension(IDimension dimension);

	public IPosition getPosition();

	public void setPosition(IPosition position);

	public void addVector(Vector2 vectorToAdd);

	public IAudio getAudio();

	public void setAudio(IAudio audio);

	public Sprite getSprite();

	public void setSprite(Sprite sprite);
	
	public Texture getTexture();

	public void setTexture(Texture texture);
	
	public Animation getAnimation();

	public void setAnimation(Animation animation);
	
	public TextureRegion[][] getRegions();

	public void setRegions(TextureRegion[][] regions);
	
	public World getWorld();

	public void setWorld(World world);
}
