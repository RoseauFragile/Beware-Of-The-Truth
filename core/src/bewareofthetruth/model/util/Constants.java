package bewareofthetruth.model.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.data.ICamera;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;

public final class Constants {

	private IModelFacade modelFacade;
	public static final float PPM = 32;
	public static final float SCALE = 2.0f;
	public ICamera CAMERA;
	public TiledMapRenderer TMR;
	public SpriteBatch BATCH;
	public IPlayer PLAYER;
	public Box2DDebugRenderer DEBUG_RENDERER;
	public World WORLD;
	public TiledMap TILEDMAP;
	public ILevel LEVEL;
	
	public Constants(IModelFacade modelFacade) {
	this.setModelFacade(modelFacade);
	this.CAMERA = this.getModelFacade().getBewareOfTruthModel().getCam();
	this.TMR = this.modelFacade.getBewareOfTruthModel().getLevel().getTmr();
	this.BATCH = this.modelFacade.getBewareOfTruthModel().getBatch();
	this.PLAYER = this.modelFacade.getBewareOfTruthModel().getLevel().getPlayer();
	this.DEBUG_RENDERER = this.modelFacade.getBewareOfTruthModel().getDebugRenderer();
	this.WORLD = this.modelFacade.getBewareOfTruthModel().getLevel().getWorld();
	this.TILEDMAP = this.modelFacade.getBewareOfTruthModel().getLevel().getMap().getTiledMap();
	this.LEVEL = this.modelFacade.getBewareOfTruthModel().getLevel();
	}

	public IModelFacade getModelFacade() {
		return this.modelFacade;
	}

	public void setModelFacade(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}
	
	
}
