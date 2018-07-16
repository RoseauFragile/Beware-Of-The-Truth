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

	private float attackDelta;

	private TextureAtlas atlas;

	private Animation<TextureRegion> animationCurrent;

	private Direction lastDirection;

	private Direction direction;

	public Entity(final World world, final float x, final float y, final boolean isStatic) {
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

	@Override
	public void initAnimation(final String textureAtlasPath, final Direction lastDirection, final float walkDelta,
			final float idleDelta, final float attackDelta) {
		this.setAtlas(new TextureAtlas(textureAtlasPath));
		System.out.println("done");
		this.setLastDirection(lastDirection);
		this.setWalkDelta(walkDelta);
		this.setIdleDelta(idleDelta);
		this.setAttackDelta(attackDelta);
		this.defineAnimationCurrentByLastDirection();
	}

	private void defineAnimationCurrentByLastDirection() {
		switch (this.lastDirection) {
		case UP:
			this.setAnimationCurrent(this.getAnimationIdleUp());
			break;
		case DOWN:
			this.setAnimationCurrent(this.getAnimationIdleDown());
			break;
		case RIGHT:
			this.setAnimationCurrent(this.getAnimationIdleRight());
			break;
		case LEFT:
			this.setAnimationCurrent(this.getAnimationIdleLeft());
			break;
		}
	}

	@Override
	public IMoveStrategy getMoveStrategy() {
		return this.moveStrategy;
	}

	@Override
	public void setMoveStrategy(final IMoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	@Override
	public IDodgeStrategy getDodgeStrategy() {
		return this.dodgeStrategy;
	}

	@Override
	public void setDodgeStrategy(final IDodgeStrategy dodgeStrategy) {
		this.dodgeStrategy = dodgeStrategy;
	}

	@Override
	public IBounceStrategy getBounceStrategy() {
		return this.bounceStrategy;
	}

	public void setDodgeStrategy(final IBounceStrategy bounceStrategy) {
		this.bounceStrategy = bounceStrategy;
	}

	@Override
	public IDimension getDimension() {
		return this.dimension;
	}

	@Override
	public void setDimension(final IDimension dimension) {
		this.dimension = dimension;
	}

	@Override
	public IPosition getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(final IPosition position) {
		this.position = position;
	}

	public void setPosition(final Vector2 vectorPosition) {
		this.position.setVector();
		this.position.setX(vectorPosition.x);
		this.position.setY(vectorPosition.y);
	}

	@Override
	public void addVector(final Vector2 vectorToadd) {

	}

	@Override
	public ISound getAudio() {
		return this.audio;
	}

	@Override
	public void setAudio(final ISound audio) {
		this.audio = audio;
	}

	@Override
	public void setBounceStrategy(final IBounceStrategy bounceStrategy) {

	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void setBody(final Body body) {
		this.body = body;
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public void setWorld(final World world) {
		this.world = world;
	}

	public void updateEntity() {
		this.body.getPosition().x = this.getPosition().getX();
		this.body.getPosition().y = this.getPosition().getY();
	}

	public boolean isStatic() {
		return this.isStatic;
	}

	public void setStatic(final boolean isStatic) {
		this.isStatic = isStatic;
	}

	@Override
	public TextureRegion getCurrentTextureRegion(final float stateTime) {
		return this.getAnimationCurrent().getKeyFrame(stateTime, true);
	}

	@Override
	public void disposeAtlas() {
		this.getAtlas().dispose();
	}

	private Animation<TextureRegion> instantiateNewAnimation(final float delta, final String regionsName) {
		return new Animation<TextureRegion>(delta, this.atlas.findRegions(regionsName), PlayMode.LOOP_PINGPONG);
	}

	@Override
	public Animation<TextureRegion> getAnimationIdleUp() {
		return this.instantiateNewAnimation(this.idleDelta, "idleUp");
	}

	@Override
	public Animation<TextureRegion> getAnimationIdleDown() {
		return this.instantiateNewAnimation(this.idleDelta, "idleDown");
	}

	@Override
	public Animation<TextureRegion> getAnimationIdleRight() {
		return this.instantiateNewAnimation(this.idleDelta, "idleRight");
	}

	@Override
	public Animation<TextureRegion> getAnimationIdleLeft() {
		return this.instantiateNewAnimation(this.idleDelta, "idleLeft");
	}

	@Override
	public Animation<TextureRegion> getAnimationWalkUp() {
		return this.instantiateNewAnimation(this.walkDelta, "walkUp");
	}

	@Override
	public Animation<TextureRegion> getAnimationWalkDown() {
		return this.instantiateNewAnimation(this.walkDelta, "walkDown");
	}

	@Override
	public Animation<TextureRegion> getAnimationWalkRight() {
		return this.instantiateNewAnimation(this.walkDelta, "walkRight");
	}

	@Override
	public Animation<TextureRegion> getAnimationWalkLeft() {
		return this.instantiateNewAnimation(this.walkDelta, "walkLeft");
	}

	@Override
	public void moveUp() {
		this.setAnimationCurrent(this.getAnimationWalkUp());
	}

	@Override
	public void moveDown() {
		this.setAnimationCurrent(this.getAnimationWalkDown());
	}

	@Override
	public void moveRight() {
		this.setAnimationCurrent(this.getAnimationWalkRight());
	}

	@Override
	public void moveLeft() {
		this.setAnimationCurrent(this.getAnimationWalkLeft());
	}

	@Override
	public void idleUp() {
		this.setAnimationCurrent(this.getAnimationIdleUp());
	}

	@Override
	public void idleDown() {
		this.setAnimationCurrent(this.getAnimationIdleDown());
	}

	@Override
	public void idleRight() {
		this.setAnimationCurrent(this.getAnimationIdleRight());
	}

	@Override
	public void idleLeft() {
		this.setAnimationCurrent(this.getAnimationIdleLeft());
	}

	@Override
	public Animation<TextureRegion> getAnimationCurrent() {
		return this.animationCurrent;
	}

	@Override
	public void setAnimationCurrent(final Animation<TextureRegion> animationCurrent) {
		this.animationCurrent = animationCurrent;
	}

	@Override
	public float getIdleDelta() {
		return this.idleDelta;
	}

	@Override
	public void setIdleDelta(final float idleDelta) {
		this.idleDelta = idleDelta;
	}

	@Override
	public float getWalkDelta() {
		return this.walkDelta;
	}

	@Override
	public void setWalkDelta(final float walkDelta) {
		this.walkDelta = walkDelta;
	}

	@Override
	public float getAttackDelta() {
		return this.attackDelta;
	}

	@Override
	public void setAttackDelta(final float attackDelta) {
		this.attackDelta = attackDelta;
	}

	@Override
	public TextureAtlas getAtlas() {
		return this.atlas;
	}

	@Override
	public void setAtlas(final TextureAtlas atlas) {
		this.atlas = atlas;
	}

	@Override
	public Direction getLastDirection() {
		return this.lastDirection;
	}

	@Override
	public void setLastDirection(final Direction lastDirection) {
		this.lastDirection = lastDirection;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(final Direction direction) {
		this.direction = direction;
	}
}
