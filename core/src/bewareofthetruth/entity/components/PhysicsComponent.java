package bewareofthetruth.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapManager;
import bewareofthetruth.utility.BodyBuilder;

public abstract class PhysicsComponent extends ComponentSubject implements Component, ContactListener{
	private static final String TAG = PhysicsComponent.class.getSimpleName();

	public abstract void update(Entity entity, MapManager mapMgr, float delta);

	protected Vector2 _nextEntityPosition;
	protected Vector2 _currentEntityPosition;
	protected Entity.Direction _currentDirection;
	protected Json _json;
	protected Vector2 _velocity;

	protected Body body;
	Vector2 vel;



	private Entity _entity;
	protected Array<Entity> _tempEntities;

	public Rectangle _boundingBox;
	protected BoundingBoxLocation _boundingBoxLocation;
	protected Ray _selectionRay;
	protected final float _selectRayMaximumDistance = 128.0f;

	public static enum BoundingBoxLocation {
		BOTTOM_LEFT, BOTTOM_CENTER, CENTER,
	}

	public PhysicsComponent() {
		_nextEntityPosition = new Vector2(0, 0);
		_currentEntityPosition = new Vector2(0, 0);
		_velocity = new Vector2(2f, 2f);
		final Byte categoryOfEntity = 4;
		//---------WITH BODY -----------------
		//body =;
		//TODO Réussir à récupérer le x et y de départ et le world
		//TODO Ajouter dans le JSON le nombre de byte correspondant à la catégorie de l'entité pour les collisions + LARGEUR et HAUTEUR
		//TODO Lier la position du body à la vrai position

		//---------WITH RECTANGLE-------------
		_boundingBox = new Rectangle();

		//------------------------------------
		_json = new Json();
		_tempEntities = new Array<Entity>();
		_boundingBoxLocation = BoundingBoxLocation.BOTTOM_LEFT;
		_selectionRay = new Ray(new Vector3(), new Vector3());
	}

	public Body getBody() {
		return body;
	}

	public void setBody(final World world, Vector2 position, short cBits, short mBits, short gIndex) {
		body = BodyBuilder.createBox(world, position, Entity.FRAME_WIDTH * Map.UNIT_SCALE, Entity.FRAME_WIDTH * Map.UNIT_SCALE, false, true, cBits, mBits, gIndex);
	}

	/*protected boolean isCollisionWithMapEntities(Entity entity, MapManager mapMgr) {
		_tempEntities.clear();
		_tempEntities.addAll(mapMgr.getCurrentMapEntities());
		_tempEntities.addAll(mapMgr.getCurrentMapQuestEntities());
		boolean isCollisionWithMapEntities = false;

		for (final Entity mapEntity : _tempEntities) {
			// Check for testing against self
			if (mapEntity.equals(entity)) {
				continue;
			}

			final Rectangle targetRectp = mapEntity.getCurrentBoundingBox();
			if (_boundingBox.overlaps(targetRectp)) {
				// Collision
				entity.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
				isCollisionWithMapEntities = true;
				break;
			}
		}
		_tempEntities.clear();
		return isCollisionWithMapEntities;
	}

	protected boolean isCollision(Entity entitySource, Entity entityTarget) {
		boolean isCollisionWithMapEntities = false;

		if (entitySource.equals(entityTarget)) {
			return false;
		}

		if (entitySource.getCurrentBoundingBox().overlaps(entityTarget.getCurrentBoundingBox())) {
			// Collision
			entitySource.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
			isCollisionWithMapEntities = true;
		}

		return isCollisionWithMapEntities;
	}

	protected boolean isCollisionWithMapLayer(Entity entity, MapManager mapMgr) {
		final MapLayer mapCollisionLayer = mapMgr.getCollisionLayer();

		if (mapCollisionLayer == null) {
			return false;
		}

		Rectangle rectangle = null;

		for (final MapObject object : mapCollisionLayer.getObjects()) {
			if (object instanceof RectangleMapObject) {
				rectangle = ((RectangleMapObject) object).getRectangle();
				if (_boundingBox.overlaps(rectangle)) {
					// Collision
					entity.sendMessage(MESSAGE.COLLISION_WITH_MAP);
					return true;
				}
			}
		}

		return false;
	}*/

	protected void setNextPositionToCurrent(Entity entity) {
		_currentEntityPosition.x = _nextEntityPosition.x;
		_currentEntityPosition.y = _nextEntityPosition.y;



		// Gdx.app.debug(TAG, "SETTING Current Position " +
		// entity.getEntityConfig().getEntityID() + ": (" + _currentEntityPosition.x +
		// "," + _currentEntityPosition.y + ")");
		entity.sendMessage(MESSAGE.CURRENT_POSITION, _json.toJson(_currentEntityPosition));
	}

	protected void calculateNextPosition(float deltaTime) {
		float desiredVelX = 0;
		float desiredVelY = 0;
		vel = body.getLinearVelocity();
		System.out.println(this.get_entity().toString());
		if(this.get_entity().get_graphicsComponent()._currentState == Entity.State.IDLE){
			final float velChangeX = desiredVelX - vel.x;
			final float forceX = this.getBody().getMass() * velChangeX / (1/60.0f); //f = mv/t
			final float velChangeY = desiredVelY - vel.y;
			final float forceY = this.getBody().getMass() * velChangeY / (1/60.0f); //f = mv/t
			this.getBody().applyForce( new Vector2(forceX, forceY), body.getWorldCenter(), true);
			return;
		}
		if(this.get_entity() != null && this.get_entity().get_graphicsComponent()._currentState == Entity.State.ROLL) {
			if (_currentDirection == null) {
				return;
			}

			if (deltaTime > .7) {
				return;
			}
			final double coefficientRoulade = 0.05;
			float testX;
			float testY;

			testX = _currentEntityPosition.x;
			testY = _currentEntityPosition.y;

			_velocity.scl(deltaTime);

			switch (_currentDirection) {
			case LEFT:
				testX -= _velocity.x + coefficientRoulade;
				break;
			case RIGHT:
				testX += _velocity.x + coefficientRoulade;
				break;
			case UP:
				testY += _velocity.y + coefficientRoulade;
				break;
			case DOWN:
				testY -= _velocity.y + coefficientRoulade;
				break;
			case UP_RIGHT:
				testX += _velocity.x + coefficientRoulade;
				testY += _velocity.y + coefficientRoulade;
				break;
			case UP_LEFT:
				testX -= _velocity.x + coefficientRoulade;
				testY += _velocity.y + coefficientRoulade;
				break;
			case DOWN_RIGHT :
				testX += _velocity.x + coefficientRoulade;
				testY -= _velocity.y + coefficientRoulade;
				break;
			case DOWN_LEFT :
				testX -= _velocity.x + coefficientRoulade;
				testY -= _velocity.y + coefficientRoulade;
				break;
			default:
				break;
			}

			_nextEntityPosition.x = testX;
			_nextEntityPosition.y = testY;

			// velocity
			_velocity.scl(1 / deltaTime);
		}else {

			if (_currentDirection == null) {
				return;
			}

			if (deltaTime > .7) {
				return;
			}

			float testX;
			float testY;

			testX = _currentEntityPosition.x;
			testY = _currentEntityPosition.y;


			_velocity.scl(deltaTime);
			System.out.println(_currentDirection);
			switch (_currentDirection) {
			case LEFT:
				desiredVelX = -2;
				testX -= _velocity.x;
				break;
			case RIGHT:
				desiredVelX = 2;
				testX += _velocity.x;
				break;
			case UP:
				desiredVelY = 2;
				testY += _velocity.y;
				break;
			case DOWN:
				desiredVelY = -2;
				testY -= _velocity.y;
				break;
			case UP_RIGHT:
				desiredVelX = 2;
				desiredVelY = 2;
				testX += _velocity.x;
				testY += _velocity.y;
				break;
			case UP_LEFT:
				desiredVelX = -2;
				desiredVelY = 2;
				testX -= _velocity.x;
				testY += _velocity.y;
				break;
			case DOWN_RIGHT :
				desiredVelX = 2;
				desiredVelY = -2;
				testX += _velocity.x;
				testY -= _velocity.y;
				break;
			case DOWN_LEFT :
				desiredVelX = -2;
				desiredVelY = -2;
				testX -= _velocity.x;
				testY -= _velocity.y;
				break;
			default:
				break;
			}
			final float velChangeX = desiredVelX - vel.x;
			final float forceX = this.getBody().getMass() * velChangeX / (1/60.0f); //f = mv/t
			final float velChangeY = desiredVelY - vel.y;
			final float forceY = this.getBody().getMass() * velChangeY / (1/60.0f); //f = mv/t
			this.getBody().applyForce( new Vector2(forceX, forceY), body.getWorldCenter(), true);
			_nextEntityPosition.x = testX;
			_nextEntityPosition.y = testY;

			// velocity
			_velocity.scl(1 / deltaTime);
		}
	}

	protected void initBoundingBox(float percentageWidthReduced, float percentageHeightReduced) {
		// Update the current bounding box
		float width;
		float height;

		final float origWidth = Entity.FRAME_WIDTH;
		final float origHeight = Entity.FRAME_HEIGHT;

		final float widthReductionAmount = 1.0f - percentageWidthReduced; // .8f for 20% (1 - .20)
		final float heightReductionAmount = 1.0f - percentageHeightReduced; // .8f for 20% 1 - .20)

		if (widthReductionAmount > 0 && widthReductionAmount < 1) {
			width = Entity.FRAME_WIDTH * widthReductionAmount;
		} else {
			width = Entity.FRAME_WIDTH;
		}

		if (heightReductionAmount > 0 && heightReductionAmount < 1) {
			height = Entity.FRAME_HEIGHT * heightReductionAmount;
		} else {
			height = Entity.FRAME_HEIGHT;
		}

		if (width == 0 || height == 0) {
			Gdx.app.debug(TAG, "Width and Height are 0!! " + width + ":" + height);
		}

		// Need to account for the unitscale, since the map coordinates will be in
		// pixels
		float minX;
		float minY;

		if (Map.UNIT_SCALE > 0) {
			minX = _nextEntityPosition.x / Map.UNIT_SCALE;
			minY = _nextEntityPosition.y / Map.UNIT_SCALE;
		} else {
			minX = _nextEntityPosition.x;
			minY = _nextEntityPosition.y;
		}

		_boundingBox.setWidth(width);
		_boundingBox.setHeight(height);

		switch (_boundingBoxLocation) {
		case BOTTOM_LEFT:
			_boundingBox.set(minX, minY, width, height);
			break;
		case BOTTOM_CENTER:
			_boundingBox.setCenter(minX + origWidth / 2, minY + origHeight / 4);
			break;
		case CENTER:
			_boundingBox.setCenter(minX + origWidth / 2, minY + origHeight / 2);
			break;
		}

		// Gdx.app.debug(TAG, "SETTING Bounding Box for " +
		// entity.getEntityConfig().getEntityID() + ": (" + minX + "," + minY + ")
		// width: " + width + " height: " + height);
	}

	protected void updateBoundingBoxPosition(Vector2 position) {
		// Need to account for the unitscale, since the map coordinates will be in
		// pixels
		float minX;
		float minY;

		if (Map.UNIT_SCALE > 0) {
			minX = position.x / Map.UNIT_SCALE;
			minY = position.y / Map.UNIT_SCALE;
		} else {
			minX = position.x;
			minY = position.y;
		}

		switch (_boundingBoxLocation) {
		case BOTTOM_LEFT:
			_boundingBox.set(minX, minY, _boundingBox.getWidth(), _boundingBox.getHeight());
			break;
		case BOTTOM_CENTER:
			_boundingBox.setCenter(minX + Entity.FRAME_WIDTH / 42, minY + Entity.FRAME_HEIGHT / 4);
			break;
		case CENTER:
			_boundingBox.setCenter(minX + Entity.FRAME_WIDTH / 2, minY + Entity.FRAME_HEIGHT / 2);
			break;
		}

		// Gdx.app.debug(TAG, "SETTING Bounding Box for " +
		// entity.getEntityConfig().getEntityID() + ": (" + minX + "," + minY + ")
		// width: " + width + " height: " + height);
	}

	public Entity get_entity() {
		return _entity;
	}

	public void set_entity(Entity _entity) {
		this._entity = _entity;
	}
}