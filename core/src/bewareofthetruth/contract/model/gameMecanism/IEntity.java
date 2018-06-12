package bewareofthetruth.contract.model.gameMecanism;

import com.badlogic.gdx.math.Vector2;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IBounceStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IDodgeStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IMoveStrategy;
import bewareofthetruth.contract.model.utils.IAudio;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.IPosition;
import bewareofthetruth.contract.model.utils.ISprite;

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

	public ISprite getSprite();

	public void setSprite(ISprite sprite);
}
