package bewareofthetruth.model.gameMechanics.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IBounceStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IDodgeStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IMoveStrategy;
import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.IPosition;
import bewareofthetruth.contract.model.utils.ISound;
import bewareofthetruth.model.util.Position;

public class Entity implements IEntity {

	private IMoveStrategy moveStrategy;

	private IDodgeStrategy dodgeStrategy;

	private IBounceStrategy bounceStrategy;

	private IDimension dimension;

	private IPosition position;

	private ISound audio;
	
	private Body body;
	
	private World world;
	
	private boolean isStatic;
	
	private float idleDelta;
	
	private float walkDelta;
	
	private TextureAtlas atlas;
	
	private Animation<TextureRegion> animationCurrent;
	
	private Direction lastDirection;
	
	private float Direction;
	
	public Entity(String sourceTexture, World world, float x, float y, boolean isStatic) {
		this.setPosition(new Position());
		this.setStatic(isStatic);
		this.getPosition().setX(x);
		this.getPosition().setY(y);
		this.setWorld(world);
		this.setIdleDelta(0f);
		this.setWalkDelta(0f);
		this.setAtlas(null);
		this.setAnimationCurrent(null);
	}
	
	public Entity(World world, float x, float y, boolean isStatic) {
		this.setPosition(new Position());
		this.setStatic(isStatic);
		this.getPosition().setX(x);
		this.getPosition().setY(y);
		this.setWorld(world);
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
	public ISound getAudio() {
		return audio;
	}

	@Override
	public void setAudio(ISound audio) {
		this.audio = audio;
	}

	@Override
	public void setBounceStrategy(IBounceStrategy bounceStrategy) {

	}

	public Body getBody() {
		return this.body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public World getWorld() {
		return this.world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void updateEntity() {
		this.body.getPosition().x = this.getPosition().getX();
		this.body.getPosition().y = this.getPosition().getY();
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public TextureRegion getCurrentTextureRegion(final float stateTime) {
		return this.getAnimationCurrent().getKeyFrame(stateTime, true);
	}

	public void disposeAtlas() {
		this.getAtlas().dispose();
	}

	private Animation<TextureRegion> instantiateNewAnimation(final float delta, final String regionsName) {
		return new Animation<TextureRegion>(delta, this.atlas.findRegions(regionsName), PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationIdleUp() {
		return this.instantiateNewAnimation(this.idleDelta, "idleUp");
	}

	public Animation<TextureRegion> getAnimationIdleDown() {
		return this.instantiateNewAnimation(this.idleDelta, "idleDown");
	}

	public Animation<TextureRegion> getAnimationIdleRight() {
		return this.instantiateNewAnimation(this.idleDelta, "idleRight");
	}

	public Animation<TextureRegion> getAnimationIdleLeft() {
		return this.instantiateNewAnimation(this.idleDelta, "idleLeft");
	}

	public Animation<TextureRegion> getAnimationWalkUp() {
		return this.instantiateNewAnimation(this.walkDelta, "walkUp");
	}

	public Animation<TextureRegion> getAnimationWalkDown() {
		return this.instantiateNewAnimation(this.walkDelta, "walkDown");
	}

	public Animation<TextureRegion> getAnimationWalkRight() {
		return this.instantiateNewAnimation(this.walkDelta, "walkRight");
	}

	public Animation<TextureRegion> getAnimationWalkLeft() {
		return this.instantiateNewAnimation(this.walkDelta, "walkLeft");
	}

	public void moveUp() {
		this.setAnimationCurrent(this.getAnimationWalkUp());
	}

	public void moveDown() {
		this.setAnimationCurrent(this.getAnimationWalkDown());
	}

	public void moveRight() {
		this.setAnimationCurrent(this.getAnimationWalkRight());
	}

	public void moveLeft() {
		this.setAnimationCurrent(this.getAnimationWalkLeft());
	}

	public void idleUp() {
		this.setAnimationCurrent(this.getAnimationIdleUp());
	}

	public void idleDown() {
		this.setAnimationCurrent(this.getAnimationIdleDown());
	}

	public void idleRight() {
		this.setAnimationCurrent(this.getAnimationIdleRight());
	}

	public void idleLeft() {
		this.setAnimationCurrent(this.getAnimationIdleLeft());
	}

	public Animation<TextureRegion> getAnimationCurrent() {
		return this.animationCurrent;
	}

	public void setAnimationCurrent(final Animation<TextureRegion> animationCurrent) {
		this.animationCurrent = animationCurrent;
	}

	public float getIdleDelta() {
		return this.idleDelta;
	}

	public void setIdleDelta(final float idleDelta) {
		this.idleDelta = idleDelta;
	}

	public float getWalkDelta() {
		return this.walkDelta;
	}

	public void setWalkDelta(final float walkDelta) {
		this.walkDelta = walkDelta;
	}

	public TextureAtlas getAtlas() {
		return this.atlas;
	}

	public void setAtlas(final TextureAtlas atlas) {
		this.atlas = atlas;
	}

	public Direction getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(Direction lastDirection) {
		this.lastDirection = lastDirection;
	}

	public float getDirection() {
		return Direction;
	}

	public void setDirection(float direction) {
		this.Direction = direction;
	}
}
