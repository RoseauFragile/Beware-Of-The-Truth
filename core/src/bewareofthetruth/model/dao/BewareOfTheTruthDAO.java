package bewareofthetruth.model.dao;

public class BewareOfTheTruthDAO extends AbstractDAO {

	private ChapterDAO chapterDAO;
	private LevelDAO levelDAO;
	private MainMenuDAO mainMenuDAO;
	private PlayerDAO playerDao;
	private OptionsDAO optionsDAO;

	public BewareOfTheTruthDAO() {
		super();
		System.out.println("DAO créer");
	}

	public ChapterDAO getChapterDAO() {
		return chapterDAO;
	}

	public void setChapterDAO(ChapterDAO chapterDAO) {
		this.chapterDAO = chapterDAO;
	}

	public LevelDAO getLevelDAO() {
		return levelDAO;
	}

	public void setLevelDAO(LevelDAO levelDAO) {
		this.levelDAO = levelDAO;
	}

	public MainMenuDAO getMainMenuDAO() {
		return mainMenuDAO;
	}

	public void setMainMenuDAO(MainMenuDAO mainMenuDAO) {
		this.mainMenuDAO = mainMenuDAO;
	}

	public PlayerDAO getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	public OptionsDAO getOptionsDAO() {
		return optionsDAO;
	}

	public void setOptionsDAO(OptionsDAO optionsDAO) {
		this.optionsDAO = optionsDAO;
	}

}
