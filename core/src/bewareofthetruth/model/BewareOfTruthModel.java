package bewareofthetruth.model;

import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.ICamera;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.IGameMenu;
import bewareofthetruth.contract.model.data.IHud;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IMainMenu;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.model.data.IOptions;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.model.dao.BewareOfTheTruthDAO;
import bewareofthetruth.model.dao.PlayerSql;
import bewareofthetruth.model.gameMechanics.Chapter;
import bewareofthetruth.model.gameMechanics.mobile.Player;
import static bewareofthetruth.model.util.Constants.PPM;

public class BewareOfTruthModel implements IBewareOfTruthModel {

	private IPlayer player;
	private IChapter chapter;
	private IHud hud;
	private IGameMenu gameMenu;
	private IOptions options;
	private IMainMenu mainMenu;
	private BewareOfTheTruthDAO dao;
	private IModelFacade modelFacade;
	private Box2DDebugRenderer debugRenderer;
	private ICamera cam;
	private float widthLevel;
	private float heightLevel;
	private static float XPLAYER = 10;
	private static float YPLAYER = 0;
	private IEntity zombie;
	private IEntity zombie2;
	private SpriteBatch batch;
	private OrthogonalTiledMapRenderer  tmr;
	private ILevel level;
	private int indexLevel = 1;

	public BewareOfTruthModel() throws SQLException {
		this.setDao(new BewareOfTheTruthDAO());
		this.setMainMenu(new MainMenu());
		this.setGameMenu(new GameMenu());
		this.setHud(new Hud());
		this.setOptions(new Options());
		this.setCam(new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
		this.setChapter(new Chapter());
		this.getChapter().setIdChapter(1);
		this.getChapter().setWorlds();
		this.getChapter().setLevel();
		this.setPlayer(2, this.getChapter().getWorldByIdLevel(2));
		this.setChapterByIdPlayerChapter();	
		this.initializeFirstLevelOfChapter(this.getChapter());
		this.setDebugRenderer(new Box2DDebugRenderer());
		this.setBatch(new SpriteBatch());
		
	}

	@Override
	public IChapter getChapter() {
		return this.chapter;
	}

	@Override
	public IPlayer getPlayer() {
		return this.player;
	}

	@Override
	public IOptions getOptions() {
		return this.options;
	}

	@Override
	public void setOptions(IOptions options) {
		this.options = options;
		this.getOptions().setBewareOfTruthModel(this);
	}

	@Override
	public IMainMenu getMainMenu() {
		return this.mainMenu;
	}

	@Override
	public void setMainMenu(IMainMenu mainMenu) {
		this.mainMenu = mainMenu;
		this.getMainMenu().setBewareOfTruthModel(this);
	}

	public BewareOfTheTruthDAO getDao() {
		return this.dao;
	}

	@Override
	public IHud getHud() {
		return this.hud;
	}

	@Override
	public void setHud(IHud hud) {
		this.hud = hud;
		this.getHud().setBewareOfTruthModel(this);
	}

	@Override
	public IGameMenu getGameMenu() {
		return this.gameMenu;
	}

	@Override
	public void setGameMenu(IGameMenu gameMenu) {
		this.gameMenu = gameMenu;
		this.getGameMenu().setBewareOfTruthModel(this);
	}

	@Override
	public IModelFacade getModelFacade() {
		return modelFacade;
	}

	@Override
	public void setModelFacade(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public void setPlayer(int idLevel, World world) throws SQLException {
		PlayerSql playerSql = this.getDao().getPlayerDao().getPlayerByIdLevel(idLevel);
		this.player = new Player(playerSql.getIdPlayer(), playerSql.getNom(), playerSql.getIdLevel(), playerSql.getIdInventaire(), playerSql.getIdChapter(), world, XPLAYER, YPLAYER, false);
		this.getPlayer().setBewareOfTruthModel(this);
		System.out.println("player attribué");
	}

	@Override
	public void setChapter(IChapter chapter) {
		this.chapter = chapter;
		this.getChapter().setBewareOfTruthModel(this);
	}

	public void setDao(BewareOfTheTruthDAO dao) {
		this.dao = dao;
	}

	@Override
	public void setChapterByIdPlayerChapter() throws SQLException {
		this.getChapter().setIdChapter(this.getPlayer().getChapter());
		this.getChapter().setLevel();
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
		this.debugRenderer = debugRenderer;
	}

	public ICamera getCam() {
		return cam;
	}

	public void setCam(ICamera cam) {
		this.cam = cam;
		this.getCam().setBewareOfTruthModel(this);
	}

	public float getWidthLevel() {
		return widthLevel;
	}

	public void setWidthLevel(float widthLevel) {
		this.widthLevel = widthLevel;
	}

	public float getHeightLevel() {
		return heightLevel;
	}

	public void setHeightLevel(float heightLevel) {
		this.heightLevel = heightLevel;
	}

	public IEntity getZombie() {
		return zombie;
	}

	public void setZombie(IEntity zombie) {
		this.zombie = zombie;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public IEntity getZombie2() {
		return zombie2;
	}

	public void setZombie2(IEntity zombie2) {
		this.zombie2 = zombie2;
	}

	public OrthogonalTiledMapRenderer getTmr() {
		return tmr;
	}

	public void setTmr(TiledMap map) {
		this.tmr = new OrthogonalTiledMapRenderer(map);
		//this.getTmr().setView(this.getCam().getCamera().combined,0,0,Gdx.graphics.getWidth() / PPM,Gdx.graphics.getHeight() / 128);
	}

	public ILevel getLevel() {
		return level;
	}

	public void setLevel(ILevel level) {
		this.level = level;
	}
	
	public void nextLevel() {
		this.indexLevel +=1;
		this.level = this.getChapter().getLevels().get(indexLevel);
		this.setTmr(this.getLevel().getMap().getTiledMap());
		//this.getTmr().setView(this.getCam().getCamera().combined,0,0,Gdx.graphics.getWidth() /PPM,Gdx.graphics.getHeight()/128);
	}

	public void initializeFirstLevelOfChapter(IChapter chapter) {
		this.setChapter(chapter);
		this.level = this.getChapter().getLevels().get(0);
		this.setTmr(this.getLevel().getMap().getTiledMap());
		//this.getTmr().setView(this.getCam().getCamera().combined,0,0,Gdx.graphics.getWidth() /PPM,Gdx.graphics.getHeight()/128);
	}
}
