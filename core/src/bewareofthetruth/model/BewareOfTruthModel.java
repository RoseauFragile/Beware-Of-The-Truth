package bewareofthetruth.model;

import java.sql.SQLException;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
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
	private int idChapter;

	public BewareOfTruthModel() throws SQLException {
		this.setIdChapter(1);
		System.out.println("Model créer");
		this.setDao(new BewareOfTheTruthDAO());
		this.setPlayer(1);
		this.getPlayer().setBewareOfTruthModel(this);
		this.getPlayer().setChapter(this.getIdChapter());
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
		this.getChapter().setIdChapter(this.getPlayer().getChapter());
		this.getChapter().setLevel();
	}

	@Override
	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
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
	public void setPlayer(int idLevel) throws SQLException {
		PlayerSql playerSql = this.getDao().getPlayerDao().getPlayerByIdLevel(idLevel);
		this.player = new Player(playerSql.getIdPlayer(), playerSql.getNom(), playerSql.getIdLevel(), playerSql.getIdInventaire());
	}

	@Override
	public void setChapter(IChapter chapter) {
		this.chapter = chapter;
	}

	public void setDao(BewareOfTheTruthDAO dao) {
		this.dao = dao;
	}

	@Override
	public int getIdChapter() {
		return this.idChapter;
	}

}
