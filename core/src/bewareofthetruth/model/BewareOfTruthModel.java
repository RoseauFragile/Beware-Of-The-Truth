package bewareofthetruth.model;

import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import bewareofthetruth.model.dao.BewareOfTheTruthDAO;
import bewareofthetruth.model.dao.PlayerSql;
import bewareofthetruth.model.gameMechanics.Chapter;
import bewareofthetruth.model.gameMechanics.mobile.Player;

public class BewareOfTruthModel implements IBewareOfTruthModel {

	private IChapter					chapter;
	private IHud						hud;
	private IGameMenu					gameMenu;
	private IOptions					options;
	private IMainMenu					mainMenu;
	private BewareOfTheTruthDAO			dao;
	private IModelFacade				modelFacade;
	private Box2DDebugRenderer			debugRenderer;
	private ICamera						cam;
	private static float				XPLAYER		= 10;
	private static float				YPLAYER		= 0;
	private SpriteBatch					batch;
	private ILevel						level;
	private int							indexLevel	= 1;

	public BewareOfTruthModel() throws SQLException {
		
		this.initializeModel();

		this.setChapter(new Chapter(1));
		this.setPlayer(indexLevel, this.getChapter().getLevels().get(indexLevel).getWorld());
		//this.initializeFirstLevelOfChapter();
		this.setLevel(this.getChapter().getLevels().get(this.indexLevel));
		this.setDebugRenderer(new Box2DDebugRenderer());
		this.setBatch(new SpriteBatch());

	}

	@Override
	public IChapter getChapter() {
		return this.chapter;
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
		this.getChapter().getLevels().get(playerSql.getIdLevel()).setPlayer(new Player(playerSql.getIdPlayer(), playerSql.getNom(), playerSql.getIdLevel(),
				playerSql.getIdInventaire(), playerSql.getIdChapter(), world, XPLAYER, YPLAYER, false));
		this.getChapter().getLevels().get(playerSql.getIdLevel()).getPlayer().setBewareOfTruthModel(this);
	}

	@Override
	public void setChapter(IChapter chapter) throws SQLException {
		this.chapter = chapter;
		this.getChapter().setBewareOfTruthModel(this);
		this.getChapter().setLevel();
	}

	public void setDao(BewareOfTheTruthDAO dao) {
		this.dao = dao;
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

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public ILevel getLevel() {
		return level;
	}

	public void setLevel(ILevel level) {
		this.level = level;
	}

	public void nextLevel() {
		this.indexLevel += 1;
		this.setLevel(this.getChapter().getLevels().get(indexLevel));
	}

	public void initializeFirstLevelOfChapter() throws SQLException {
		this.setLevel(this.getChapter().getLevels().get(this.indexLevel));
	}
	
	public void initializeModel() throws SQLException {
		this.setDao(new BewareOfTheTruthDAO());
		this.setMainMenu(new MainMenu());
		this.setGameMenu(new GameMenu());
		this.setHud(new Hud());
		this.setOptions(new Options());
		this.setCam(new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}
}
