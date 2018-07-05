package bewareofthetruth.model.gameMechanics.level;

import java.sql.SQLException;
import java.util.ArrayList;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IMap;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.gameMecanism.ITeleporter;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;
import bewareofthetruth.model.dao.MobileSql;
import bewareofthetruth.model.dao.TeleporterSql;
import bewareofthetruth.model.gameMechanics.entity.handlers.MyContactListener;
import bewareofthetruth.model.gameMechanics.mobile.CharacterFactory;
import bewareofthetruth.model.gameMechanics.tiles.Teleporter;
import bewareofthetruth.model.util.Dimension;
import bewareofthetruth.model.util.box2d.TiledObjectUtil;
import box2dLight.RayHandler;

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
	private TiledMapRenderer tmr;
	private int[] layerBackground = {0,1,2,3,4,5};
	private int[] layerAfterBackground = {6,7,8};
	private RayHandler rayHandler;
	private ArrayList<String> musicsPath;
	private ArrayList<ITeleporter> teleporter = new ArrayList<ITeleporter>();

	public Level(float id, String levelName, float height, float width, String sourceMap) throws SQLException {
		System.out.println(sourceMap);
		this.setLevelName(levelName);
		this.setId(id);
		this.setDimension(height, width);
		this.setMap(new Map(sourceMap));
		this.getMap().setLevel(this);
		this.setWorld(new World(new Vector2(0,0), true));
		this.getWorld().setContactListener(new MyContactListener());
		this.setTmr(new OrthogonalTiledMapRenderer(this.getMap().getTiledMap()));
		this.rayHandler = new RayHandler(this.getWorld());
		this.getRayHandler().setAmbientLight(0.5f);
		ArrayList<String> list = new ArrayList<String>();
        list.add("bar2.mp3");
		this.setMusicsPath(list);
		TiledObjectUtil.buildShapes(this.getWorld(), this.getMap().getTiledMap().getLayers().get("collision").getObjects());
		TiledObjectUtil.parseLightObjectLayer(this.getWorld(), this.getMap().getTiledMap().getLayers().get("objets-lumiere").getObjects(), this.getRayHandler());

	}

	public RayHandler getRayHandler() {
		return this.rayHandler;
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
	
	public TiledMapRenderer getTmr() {
		return this.tmr;
	}

	public void setTmr(TiledMapRenderer tmr) {
		this.tmr = tmr;
		
	}

	public int[] getLayerBackground() {
		return layerBackground;
	}

	public int[] getLayerAfterBackground() {
		return layerAfterBackground;
	}
	
	public void updateEnnemiesMovement() {
		for(int i = 0; i< this.getMobiles().size(); i++) {
			this.getMobiles().get(i).getBody().setLinearVelocity(
			(this.getPlayer().getBody().getPosition().x - this.getMobiles().get(i).getBody().getPosition().x) ,
			(this.getPlayer().getBody().getPosition().y - this.getMobiles().get(i).getBody().getPosition().y));
		}
	}

	public ArrayList<String> getMusicsPath() {
		return musicsPath;
	}

	public void setMusicsPath(ArrayList<String> musicsPath) {
		this.musicsPath = musicsPath;
	}

	public ArrayList<ITeleporter> getTeleporter() {
		return teleporter;
	}

	public void setTeleporter() throws SQLException {
		ArrayList<TeleporterSql> teleporterSql = this.getChapter().getBewareOfTruthModel().getDao().getTeleporterDAO().getTeleportersByIdLevel((int) this.getId());
		this.teleporter = new ArrayList<>();
		for( TeleporterSql temp : teleporterSql) {
			this.teleporter.add(new Teleporter(temp.getIdTeleporter(), temp.getIdLevel(), temp.getIdNextLevel(), temp.getX(), temp.getY(), this.getWorld()));
		}
	}
}
