package bewareofthetruth.contract.model.gameMecanism;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.gameMecanism.behaviors.IBounceStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IDodgeStrategy;
import bewareofthetruth.contract.model.gameMecanism.behaviors.IMoveStrategy;
import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.IPosition;
import bewareofthetruth.contract.model.utils.ISound;

public interface IEntity {

	public void initAnimation(final String textureAtlasPath, final Direction lastDirection, final float walkDelta,
			final float idleDelta, final float attackDelta);

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

	public ISound getAudio();

	public void setAudio(ISound audio);

	public World getWorld();

	public void setWorld(World world);

	public Body getBody();

	public void setBody(Body body);

	public TextureRegion getCurrentTextureRegion(final float stateTime);

	public void disposeAtlas();

	public Animation<TextureRegion> getAnimationIdleUp();

	public Animation<TextureRegion> getAnimationIdleDown();

	public Animation<TextureRegion> getAnimationIdleRight();

	public Animation<TextureRegion> getAnimationIdleLeft();

	public Animation<TextureRegion> getAnimationWalkUp();

	public Animation<TextureRegion> getAnimationWalkDown();

	public Animation<TextureRegion> getAnimationWalkRight();

	public Animation<TextureRegion> getAnimationWalkLeft();

	public void moveUp();

	public void moveDown();

	public void moveRight();

	public void moveLeft();

	public void idleUp();

	public void idleDown();

	public void idleRight();

	public void idleLeft();

	public TextureAtlas getAtlas();

	public void setAtlas(final TextureAtlas atlas);

	public Animation<TextureRegion> getAnimationCurrent();

	public void setAnimationCurrent(final Animation<TextureRegion> animationCurrent);

	public float getIdleDelta();

	public void setIdleDelta(final float idleDelta);

	public float getWalkDelta();

	public void setWalkDelta(final float walkDelta);

	public float getAttackDelta();

	public void setAttackDelta(float attackDelta);

	public Direction getLastDirection();

	public void setLastDirection(Direction lastDirection);

	public Direction getDirection();
}
