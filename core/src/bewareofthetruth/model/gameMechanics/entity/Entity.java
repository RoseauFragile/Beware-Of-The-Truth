package bewareofthetruth.model.gameMechanics.entity;

import com.badlogic.gdx.math.Vector2;

import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IBounceStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IDodgeStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IMoveStrategy;
import bewareofthetruth.contract.model.utils.IAudio;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.IPosition;
import bewareofthetruth.contract.model.utils.ISprite;
import bewareofthetruth.model.util.Position;

public class Entity implements IEntity {

	private IMoveStrategy moveStrategy;

	private IDodgeStrategy dodgeStrategy;

	private IBounceStrategy bounceStrategy;

	private IDimension dimension;

	private IPosition position;

	private IAudio audio;

	private ISprite sprite;

	public Entity() {
		this.position = new Position();
	}

	@Override
	public IMoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	@Override
	public void setMoveStrategy(IMoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	@Override
	public IDodgeStrategy getDodgeStrategy() {
		return dodgeStrategy;
	}

	@Override
	public void setDodgeStrategy(IDodgeStrategy dodgeStrategy) {
		this.dodgeStrategy = dodgeStrategy;
	}

	@Override
	public IBounceStrategy getBounceStrategy() {
		return bounceStrategy;
	}

	public void setDodgeStrategy(IBounceStrategy bounceStrategy) {
		this.bounceStrategy = bounceStrategy;
	}

	@Override
	public IDimension getDimension() {
		return dimension;
	}

	@Override
	public void setDimension(IDimension dimension) {
		this.dimension = dimension;
	}

	@Override
	public IPosition getPosition() {
		return position;
	}

	@Override
	public void setPosition(IPosition position) {
		this.position = position;
	}

	public void setPosition(Vector2 vectorPosition) {
		this.position.setVector();
		this.position.setX(vectorPosition.x);
		this.position.setY(vectorPosition.y);
	}

	@Override
	public void addVector(Vector2 vectorToadd) {

	}

	@Override
	public IAudio getAudio() {
		return audio;
	}

	@Override
	public void setAudio(IAudio audio) {
		this.audio = audio;
	}

	@Override
	public ISprite getSprite() {
		return sprite;
	}

	public void setSprite() {
		this.sprite = sprite;
	}

	@Override
	public void setBounceStrategy(IBounceStrategy bounceStrategy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSprite(ISprite sprite) {
		// TODO Auto-generated method stub

	}

}
