package bewareofthetruth.model.gameMechanics.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
	
	private Body body;
	
	private World world;
	
	public Entity(String sourceTexture, World world) {
		this.position = new Position();
		this.setWorld(world);
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

	public Body getBody() {
		return this.body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	public Body createDynamicBody() {
		Body pBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(0,0);
		def.fixedRotation = true;
		pBody = this.getWorld().createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32 / 2, 32 / 2);
		
		pBody.createFixture(shape, 1.0f);
		shape.dispose();
		return pBody;
	}

	public World getWorld() {
		return this.world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
