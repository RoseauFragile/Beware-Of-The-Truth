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
import bewareofthetruth.model.gameMechanics.entity.mobile.CharacterFactory;
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
	private int[] layerBackground = { 0, 1, 2, 3, 4, 5 };
	private int[] layerAfterBackground = { 6, 7, 8 };
	private RayHandler rayHandler;
	private ArrayList<String> musicsPath;
	private ArrayList<ITeleporter> teleporter = new ArrayList<ITeleporter>();

	public Level(final float id, final String levelName, final float height, final float width, final String sourceMap)
			throws SQLException {
		this.setLevelName(levelName);
		this.setId(id);
		System.out.println("Création du level : " + this.getId());
		this.setDimension(height, width);
		this.setMap(new Map(sourceMap));
		this.getMap().setLevel(this);
		this.setWorld(new World(new Vector2(0, 0), true));
		this.getWorld().setContactListener(new MyContactListener());
		this.setTmr(new OrthogonalTiledMapRenderer(this.getMap().getTiledMap()));
		this.rayHandler = new RayHandler(this.getWorld());
		this.getRayHandler().setAmbientLight(0.5f);
		ArrayList<String> list = new ArrayList<String>();
		list.add("bar2.mp3");
		this.setMusicsPath(list);
		TiledObjectUtil.buildShapes(this.getWorld(),
				this.getMap().getTiledMap().getLayers().get("collision").getObjects());
		TiledObjectUtil.parseLightObjectLayer(this.getWorld(),
				this.getMap().getTiledMap().getLayers().get("objets-lumiere").getObjects(), this.getRayHandler());

	}

	@Override
	public RayHandler getRayHandler() {
		return this.rayHandler;
	}

	@Override
	public String getLevelName() {
		return this.levelName;
	}

	@Override
	public void setLevelName(final String levelName) {
		this.levelName = levelName;
	}

	@Override
	public IPlayer getPlayer() {
		return this.player;
	}

	@Override
	public IDimension getDimension() {
		return this.dimension;
	}

	public void setDimension(final float height, final float width) {
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
	public void setAudio(final ISound audio) {
		this.audio = audio;
	}

	@Override
	public void setMap(final IMap map) {
		this.map = map;
	}

	@Override
	public IChapter getChapter() {
		return this.chapter;
	}

	@Override
	public void setChapter(final IChapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public float getId() {
		return this.id;
	}

	@Override
	public void setId(final float id) {
		this.id = id;
	}

	@Override
	public String getSourceMap() {
		return this.sourceMap;
	}

	@Override
	public void setSourceMap(final String sourceMap) {
		this.sourceMap = sourceMap;
	}

	@Override
	public ArrayList<IEntity> getMobiles() {
		return this.mobiles;
	}

	@Override
	public void setMobiles() throws SQLException { // TODO Pour RYO ici on instancie l'array List de Mobiles
		ArrayList<MobileSql> mobileSql = this.getChapter().getBewareOfTruthModel().getDao().getLevelDAO()
				.getMobilesByLevelId((int) this.getId());
		this.mobiles = new ArrayList<>();
		for (MobileSql temp : mobileSql) {
			this.mobiles.add(CharacterFactory.createEntity(temp, this.getWorld()));
		}
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public void setWorld(final World world) {
		this.world = world;
	}

	@Override
	public void setPlayer(final IPlayer player) {
		this.player = player;
	}

	@Override
	public TiledMapRenderer getTmr() {
		return this.tmr;
	}

	@Override
	public void setTmr(final TiledMapRenderer tmr) {
		this.tmr = tmr;

	}

	@Override
	public int[] getLayerBackground() {
		return this.layerBackground;
	}

	@Override
	public int[] getLayerAfterBackground() {
		return this.layerAfterBackground;
	}

	@Override
	public void updateEnemiesMovement() {
		for (IEntity entity : this.getMobiles()) {
			if (!entity.isAttacking()) {
				Vector2 movement = new Vector2(
						(this.getPlayer().getBody().getPosition().x - entity.getBody().getPosition().x),
						(this.getPlayer().getBody().getPosition().y - entity.getBody().getPosition().y));
				movement.nor();
				movement.scl(3.00f); // TODO POUR BEN : LIMITE VECTEUR, MAGIC NUMBER << NEED ATTRIBUT
				entity.getBody().setLinearVelocity(movement);
			}
			entity.update();
		}
	}

	@Override
	public ArrayList<String> getMusicsPath() {
		return this.musicsPath;
	}

	public void setMusicsPath(final ArrayList<String> musicsPath) {
		this.musicsPath = musicsPath;
	}

	@Override
	public ArrayList<ITeleporter> getTeleporter() {
		return this.teleporter;
	}

	@Override
	public void setTeleporter() throws SQLException {
		ArrayList<TeleporterSql> teleporterSql = this.getChapter().getBewareOfTruthModel().getDao().getTeleporterDAO()
				.getTeleportersByIdLevel((int) this.getId());
		this.teleporter = new ArrayList<>();

		for (TeleporterSql temp : teleporterSql) {
			System.out.println("DEBUG1 DEBUG1 x = " + temp.getX() + " y  = " + temp.getY());

		}
		for (TeleporterSql temp : teleporterSql) {
			this.teleporter.add(new Teleporter(temp.getIdTeleporter(), temp.getIdLevel(), temp.getIdNextLevel(),
					temp.getX(), temp.getY(), this.getWorld(), temp.getxSpawn(), temp.getySpawn()));
			System.out.println("porte : " + temp.getX() + " " + temp.getY());
		}

		for (int i = 0; i < this.teleporter.size(); i++) {
			System.out.println("DEBUG2 DEBUG2 x = " + this.teleporter.get(i).getBody().getPosition().x + " y  = "
					+ this.teleporter.get(i).getBody().getPosition().y);

		}
	}
}
