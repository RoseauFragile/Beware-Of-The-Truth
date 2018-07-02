package bewareofthetruth.contract.model.data;

import java.sql.SQLException;
import java.util.ArrayList;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;
import box2dLight.RayHandler;

public interface ILevel {

	public ISound getAudio();

	public IDimension getDimension();

	public String getLevelName();

	public IMap getMap();

	public IPlayer getPlayer();

	public void setAudio(ISound audio);

	public void setLevelName(String levelName);

	public void setMap(IMap map);

	public IChapter getChapter();

	public void setChapter(IChapter chapter);
	
	public float getId();

	public void setId(float id);
	
	public String getSourceMap();

	public void setSourceMap(String sourceMap);
	
	public ArrayList<IEntity> getMobiles();

	public void setMobiles() throws SQLException;
	
	public World getWorld();

	public void setWorld(World world);

	public void setPlayer(IPlayer player);

	public TiledMapRenderer getTmr();

	public void setTmr(TiledMapRenderer tmr);
	
	public int[] getLayerBackground();

	public int[] getLayerAfterBackground();
	
	public void updateEnnemiesMovement();
	
	public RayHandler getRayHandler();
	
	public ArrayList<String> getMusicsPath();

}
