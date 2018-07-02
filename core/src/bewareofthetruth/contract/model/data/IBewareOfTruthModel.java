package bewareofthetruth.contract.model.data;

import java.sql.SQLException;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.utils.ISound;
import bewareofthetruth.model.dao.BewareOfTheTruthDAO;

public interface IBewareOfTruthModel {

	IChapter getChapter();

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

	public void setChapter(IChapter chapter) throws SQLException;

	public void setDao(BewareOfTheTruthDAO dao);

	public BewareOfTheTruthDAO getDao();
	
	public Box2DDebugRenderer getDebugRenderer();

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer);
	
	public ICamera getCam();

	public void setCam(ICamera cam);

	public SpriteBatch getBatch();

	public void setBatch(SpriteBatch batch);
	
	public ILevel getLevel();

	public void setLevel(ILevel level);
	
	public void nextLevel();

	public void initializeFirstLevelOfChapter() throws SQLException;

	void setPlayer(int idLevel, World world) throws SQLException;
	
	public float getStateTime();

	public void setStateTime(float stateTime);
	
	public void drawBatch();

	public ISound getSoundReader();

}
