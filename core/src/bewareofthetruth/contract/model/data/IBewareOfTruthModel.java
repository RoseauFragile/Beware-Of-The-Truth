package bewareofthetruth.contract.model.data;

import java.sql.SQLException;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

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

	void setPlayer(int idLevel) throws SQLException;
	
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

}
