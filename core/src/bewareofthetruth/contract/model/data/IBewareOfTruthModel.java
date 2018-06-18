package bewareofthetruth.contract.model.data;

import java.sql.SQLException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.model.dao.BewareOfTheTruthDAO;

public interface IBewareOfTruthModel {

	IChapter getChapter();

	IPlayer getPlayer();

	IOptions getOptions();

	void setOptions(IOptions options);

	IMainMenu getMainMenu();

	void setMainMenu(IMainMenu mainMenu);

	IHud getHud();

	void setHud(IHud hud);

	IGameMenu getGameMenu();

	void setGameMenu(IGameMenu gameMenu);

	public IModelFacade getModelFacade();

	public void setModelFacade(IModelFacade modelFacade);

	public void setChapter(IChapter chapter);
	
	public void setChapterByIdPlayerChapter() throws SQLException;

	public void setDao(BewareOfTheTruthDAO dao);

	public BewareOfTheTruthDAO getDao();
	
	public World getWorld();

	public void setWorld(World world);
	
	public Box2DDebugRenderer getDebugRenderer();

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer);
	
	public ICamera getCam();

	public void setCam(ICamera cam);
	
	public float getWidthLevel();

	public void setWidthLevel(float widthLevel);

	public float getHeightLevel();

	public void setHeightLevel(float heightLevel);

	void setPlayer(int idLevel, World world) throws SQLException;
	
	public IEntity getZombie();

	public void setZombie(IEntity zombie);

	public SpriteBatch getBatch();

	public void setBatch(SpriteBatch batch);
	
	public IEntity getZombie2();

	public void setZombie2(IEntity zombie2);
	
	public OrthogonalTiledMapRenderer getTmr();

	public void setTmr(TiledMap map);

}
