package bewareofthetruth.model.dao;

import java.sql.SQLException;

public class BewareOfTheTruthDAO extends AbstractDAO {

	private ChapterDAO	chapterDAO;
	private LevelDAO	levelDAO;
	private MainMenuDAO	mainMenuDAO;
	private PlayerDAO	playerDao;
	private OptionsDAO	optionsDAO;
	private TeleporterDAO teleporterDAO;
	private static int	idColumnIndex	= 1;

	public BewareOfTheTruthDAO() throws SQLException {

		super();
		System.out.println("DAO créer");
		this.setChapterDAO(new ChapterDAO());
		this.getChapterDAO().setBewareOfTheTruthDAO(this);
		this.setLevelDAO(new LevelDAO());
		this.setMainMenuDAO(new MainMenuDAO());
		this.setPlayerDao(new PlayerDAO());
		this.setOptionsDAO(new OptionsDAO());
		this.setTeleporterDAO(new TeleporterDAO());
	}

	public ChapterDAO getChapterDAO() {

		return this.chapterDAO;
	}

	public void setChapterDAO(final ChapterDAO chapterDAO) {

		this.chapterDAO = chapterDAO;
	}

	public LevelDAO getLevelDAO() {

		return this.levelDAO;
	}

	public void setLevelDAO(final LevelDAO levelDAO) {

		this.levelDAO = levelDAO;
	}

	public MainMenuDAO getMainMenuDAO() {

		return this.mainMenuDAO;
	}

	public void setMainMenuDAO(final MainMenuDAO mainMenuDAO) {

		this.mainMenuDAO = mainMenuDAO;
	}

	public PlayerDAO getPlayerDao() {

		return this.playerDao;
	}

	public void setPlayerDao(final PlayerDAO playerDao) {

		this.playerDao = playerDao;
	}

	public OptionsDAO getOptionsDAO() {

		return this.optionsDAO;
	}

	public void setOptionsDAO(final OptionsDAO optionsDAO) {

		this.optionsDAO = optionsDAO;
	}

	public static int getIdColumnIndex() {

		return idColumnIndex;
	}

	public TeleporterDAO getTeleporterDAO() {
		return this.teleporterDAO;
	}

	public void setTeleporterDAO(TeleporterDAO teleporterDAO) {
		this.teleporterDAO = teleporterDAO;
	}
}
