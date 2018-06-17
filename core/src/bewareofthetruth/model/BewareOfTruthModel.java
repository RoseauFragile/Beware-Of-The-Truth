package bewareofthetruth.model;

import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.ICamera;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.IGameMenu;
import bewareofthetruth.contract.model.data.IHud;
import bewareofthetruth.contract.model.data.IMainMenu;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.model.data.IOptions;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.model.dao.BewareOfTheTruthDAO;
import bewareofthetruth.model.dao.PlayerSql;
import bewareofthetruth.model.gameMechanics.Chapter;
import bewareofthetruth.model.gameMechanics.mobile.Player;

public class BewareOfTruthModel implements IBewareOfTruthModel {

	private IPlayer player;
	private IChapter chapter;
	private IHud hud;
	private IGameMenu gameMenu;
	private IOptions options;
	private IMainMenu mainMenu;
	private BewareOfTheTruthDAO dao;
	private IModelFacade modelFacade;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private ICamera cam;
	private float widthLevel;
	private float heightLevel;
	

	public BewareOfTruthModel() throws SQLException {
		System.out.println("Model créer");
		this.setWorld(new World(new Vector2(0, 0), true));	
		this.setDao(new BewareOfTheTruthDAO());
		this.setPlayer(1, this.getWorld());
		this.getPlayer().setBewareOfTruthModel(this);
		this.setMainMenu(new MainMenu());
		this.getMainMenu().setBewareOfTruthModel(this);
		this.setGameMenu(new GameMenu());
		this.getGameMenu().setBewareOfTruthModel(this);
		this.setHud(new Hud());
		this.getHud().setBewareOfTruthModel(this);
		this.setOptions(new Options());
		this.getOptions().setBewareOfTruthModel(this);
		this.setChapter(new Chapter());
		this.getChapter().setBewareOfTruthModel(this);
		this.setChapterByIdPlayerChapter();	
		System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
		this.setCam(new Camera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
		this.setDebugRenderer(new Box2DDebugRenderer());
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
	}

	@Override
	public IMainMenu getMainMenu() {
		return this.mainMenu;
	}

	@Override
	public void setMainMenu(IMainMenu mainMenu) {
		this.mainMenu = mainMenu;
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
	}

	@Override
	public IGameMenu getGameMenu() {
		return this.gameMenu;
	}

	@Override
	public void setGameMenu(IGameMenu gameMenu) {
		this.gameMenu = gameMenu;
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
		this.player = new Player(playerSql.getIdPlayer(), playerSql.getNom(), playerSql.getIdLevel(), playerSql.getIdInventaire(), playerSql.getIdChapter(), world);
	}

	@Override
	public void setChapter(IChapter chapter) {
		this.chapter = chapter;
	}

	public void setDao(BewareOfTheTruthDAO dao) {
		this.dao = dao;
	}

	@Override
	public void setChapterByIdPlayerChapter() throws SQLException {
		this.getChapter().setIdChapter(this.getPlayer().getChapter());
		this.getChapter().setLevel();
	}
	
	public void render(SpriteBatch sb) {
		
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
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

	public void loadTiledMap() {
		
	}

}
