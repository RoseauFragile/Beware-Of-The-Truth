package bewareofthetruth.model.gameMechanics.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IBounceStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IDodgeStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IMoveStrategy;
import bewareofthetruth.contract.model.utils.IAudio;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.IPosition;
import bewareofthetruth.model.util.Position;

public class Entity implements IEntity {

	private IMoveStrategy moveStrategy;

	private IDodgeStrategy dodgeStrategy;

	private IBounceStrategy bounceStrategy;

	private IDimension dimension;

	private IPosition position;

	private IAudio audio;

	private Sprite sprite;
	
	private Texture texture;
	
	private Animation animation;
	
	private TextureRegion[][] regions;

	public Entity(String sourceTexture) {
		this.position = new Position();
		this.setTexture(new Texture("assets/sprite/"+sourceTexture));
		this.setRegions(TextureRegion.split(this.getTexture(), 64, 64));
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
	public Sprite getSprite() {
		return sprite;
	}
/*
	public void setSprite() {
		this.sprite = sprite;
	}*/

	@Override
	public void setBounceStrategy(IBounceStrategy bounceStrategy) {


	}

	@Override
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Animation getAnimation() {
		return this.animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public TextureRegion[][] getRegions() {
		return regions;
	}

	public void setRegions(TextureRegion[][] regions) {
		this.regions = regions;
	}
}
