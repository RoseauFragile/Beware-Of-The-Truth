package bewareofthetruth.model.gameMechanics.level;

import java.sql.SQLException;
import java.util.ArrayList;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IMap;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;
import bewareofthetruth.model.dao.MobileSql;
import bewareofthetruth.model.gameMechanics.mobile.CharacterFactory;
import bewareofthetruth.model.util.Dimension;

public class Level implements ILevel {

	private IMap map;
	private ArrayList<IEntity> mobiles = new ArrayList<IEntity>();
	private ISound audio;
	private IDimension dimension;
	private String levelName;
	private IChapter chapter;
	private float id; 
	private String sourceMap;
	private World world;
	private IPlayer player;
	private OrthogonalTiledMapRenderer	tmr;

	public Level(float id, String levelName, float height, float width, String sourceMap) throws SQLException {
		this.setLevelName(levelName);
		this.setId(id);
		this.setDimension(height, width);
		this.setMap(new Map(sourceMap));
		this.getMap().setLevel(this);
		this.setWorld(new World(new Vector2(0,0), true));
		this.setTmr();
	}

	@Override
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public IPlayer getPlayer() {
		return this.player;
	}

	@Override
	public IDimension getDimension() {
		return dimension;
	}

	public void setDimension(float height, float width) {
		this.dimension = new Dimension(height, width);
	}

	@Override
	public ISound getAudio() {
		return this.audio;
	}

	@Override
	public IMap getMap() {
		return this.map;
	}

	@Override
	public void setAudio(ISound audio) {
		this.audio = audio;
	}

	@Override
	public void setMap(IMap map) {
		this.map = map;
	}

	@Override
	public IChapter getChapter() {
		return chapter;
	}

	@Override
	public void setChapter(IChapter chapter) {
		this.chapter = chapter;
	}

	public float getId() {
		return this.id;
	}

	public void setId(float id) {
		this.id = id;
	}

	public String getSourceMap() {
		return sourceMap;
	}

	public void setSourceMap(String sourceMap) {
		this.sourceMap = sourceMap;
	}

	public ArrayList<IEntity> getMobiles() {
		return mobiles;
	}

	public void setMobiles() throws SQLException {
		ArrayList<MobileSql> mobileSql = this.getChapter().getBewareOfTruthModel().getDao().getLevelDAO().getMobilesByLevelId((int) this.getId());
		this.mobiles = new ArrayList<>();
		for( MobileSql temp : mobileSql) {
			this.mobiles.add(CharacterFactory.createEntity(temp, this.getWorld()));
		}
	}

	public World getWorld() {
		return this.world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public void setPlayer(IPlayer player) {
		this.player = player;
	}
	
	public OrthogonalTiledMapRenderer getTmr() {
		return this.tmr;
	}

	public void setTmr() {
		this.tmr = new OrthogonalTiledMapRenderer(this.getMap().getTiledMap(), 1 / 2f);
	}
}
