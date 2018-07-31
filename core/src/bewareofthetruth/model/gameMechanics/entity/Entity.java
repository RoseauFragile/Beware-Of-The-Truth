package bewareofthetruth.model.gameMechanics.entity;

import java.util.Random;

import com.badlogic.gdx.Gdx;
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

	private boolean isAttacking;

	private float stateTime;

	Random random;
	
	private static float SCALE = 0.5f;
	
	private static float VELOCITY_NULL = 0f;
	
	private static float VELOCITY_TRUE = 1.0f;

	public Entity(final World world, final float x, final float y, final boolean isStatic) {
		this.random = new Random();
		this.setPosition(new Position());
		this.setStatic(isStatic);
		this.getPosition().setX(x);
		this.getPosition().setY(y);
		this.setWorld(world);
		this.setIdleDelta(0f);
		this.setWalkDelta(0f);
		this.setAttackDelta(0f);
		this.setAttacking(false);
		this.setRandomStateTime();
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

	private void setRandomStateTime() {
		this.setStateTime(this.random.nextFloat());
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

	private Animation<TextureRegion> instantiateNewAnimation(final float delta, final String regionsName,
			final PlayMode playMode) {
		return new Animation<TextureRegion>(delta, this.atlas.findRegions(regionsName), playMode);
	}

	public Animation<TextureRegion> getAnimationIdleUp() {
		return this.instantiateNewAnimation(this.idleDelta, "idleUp", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationIdleDown() {
		return this.instantiateNewAnimation(this.idleDelta, "idleDown", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationIdleRight() {
		return this.instantiateNewAnimation(this.idleDelta, "idleRight", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationIdleLeft() {
		return this.instantiateNewAnimation(this.idleDelta, "idleLeft", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationWalkUp() {
		return this.instantiateNewAnimation(this.walkDelta, "walkUp", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationWalkDown() {
		return this.instantiateNewAnimation(this.walkDelta, "walkDown", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationWalkRight() {
		return this.instantiateNewAnimation(this.walkDelta, "walkRight", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationWalkLeft() {
		return this.instantiateNewAnimation(this.walkDelta, "walkLeft", PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationAttackUp() {
		return this.instantiateNewAnimation(this.attackDelta, "attackUp", PlayMode.NORMAL);
	}

	public Animation<TextureRegion> getAnimationAttackDown() {
		return this.instantiateNewAnimation(this.attackDelta, "attackDown", PlayMode.NORMAL);
	}

	public Animation<TextureRegion> getAnimationAttackRight() {
		return this.instantiateNewAnimation(this.attackDelta, "attackRight", PlayMode.NORMAL);
	}

	public Animation<TextureRegion> getAnimationAttackLeft() {
		return this.instantiateNewAnimation(this.attackDelta, "attackLeft", PlayMode.NORMAL);
	}

	private void walkUp() {
		this.setAnimationCurrent(this.getAnimationWalkUp());
	}

	private void walkDown() {
		this.setAnimationCurrent(this.getAnimationWalkDown());
	}

	private void walkRight() {
		this.setAnimationCurrent(this.getAnimationWalkRight());
	}

	private void walkLeft() {
		this.setAnimationCurrent(this.getAnimationWalkLeft());
	}

	private void idleUp() {
		this.setAnimationCurrent(this.getAnimationIdleUp());
	}

	private void idleDown() {
		this.setAnimationCurrent(this.getAnimationIdleDown());
	}

	private void idleRight() {
		this.setAnimationCurrent(this.getAnimationIdleRight());
	}

	private void idleLeft() {
		this.setAnimationCurrent(this.getAnimationIdleLeft());
	}

	private void attackUp() {
		this.setAnimationCurrent(this.getAnimationAttackUp());
	}

	private void attackDown() {
		this.setAnimationCurrent(this.getAnimationAttackDown());
	}

	private void attackRight() {
		this.setAnimationCurrent(this.getAnimationAttackRight());
	}

	private void attackLeft() {
		this.setAnimationCurrent(this.getAnimationAttackLeft());
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

	@Override
	public boolean isAttacking() {
		return this.isAttacking;
	}

	private void setAttacking(final boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	private float getStateTime() {
		return this.stateTime;
	}

	private void setStateTime(final float stateTime) {
		this.stateTime = stateTime;
	}

	private void resetStateTime() {
		this.setStateTime(0f);
	}

	@Override
	public void attack() {
		if (!this.isAttacking()) {
			this.setAttacking(true);
			this.resetStateTime();
			this.getBody().getLinearVelocity().scl(SCALE); 
			switch (this.getLastDirection()) {
			case UP:
				this.attackUp();
				break;
			case DOWN:
				this.attackDown();
				break;
			case RIGHT:
				this.attackRight();
				break;
			case LEFT:
				this.attackLeft();
				break;
			}
		}

	}

	private void defineDirectionByMovement() {
		Vector2 velocity = this.getBody().getLinearVelocity();

		if (velocity.y > VELOCITY_NULL) {
			this.setLastDirection(Direction.UP);
		} else if (velocity.y < 0f) {
			this.setLastDirection(Direction.DOWN);
		}

		if (velocity.x > VELOCITY_TRUE) { 
			this.setLastDirection(Direction.RIGHT);
		} else if (velocity.x < -1.0f) {
			this.setLastDirection(Direction.LEFT);
		}
	}

	@Override
	public void update() {
		this.setStateTime(this.getStateTime() + Gdx.graphics.getDeltaTime());

		if (this.isAttacking()) {
			if (this.animationCurrent.isAnimationFinished(this.getStateTime())) {
				this.setAttacking(false);
			}
		} else {
			if (this.getBody().getLinearVelocity().len() < 1.0f) {
				switch (this.getLastDirection()) {
				case UP:
					this.idleUp();
					break;
				case DOWN:
					this.idleDown();
					break;
				case RIGHT:
					this.idleRight();
					break;
				case LEFT:
					this.idleLeft();
					break;
				}
			} else {
				switch (this.getLastDirection()) {
				case UP:
					this.walkUp();
					break;
				case DOWN:
					this.walkDown();
					break;
				case RIGHT:
					this.walkRight();
					break;
				case LEFT:
					this.walkLeft();
					break;
				}
			}
		}

		this.defineDirectionByMovement();
	}
}
