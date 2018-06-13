package bewareofthetruth.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BewareOfTheTruthDAO extends AbstractDAO {

	private ChapterDAO chapterDAO;
	private LevelDAO levelDAO;
	private MainMenuDAO mainMenuDAO;
	private PlayerDAO playerDao;
	private OptionsDAO optionsDAO;

	public BewareOfTheTruthDAO() {
		super();
		System.out.println("DAO créer");
		BewareOfTheTruthDAO.test();
		this.setChapterDAO(new ChapterDAO());
		this.setLevelDAO(new LevelDAO());
		this.setMainMenuDAO(new MainMenuDAO());
		this.setPlayerDao(new PlayerDAO());
		this.setOptionsDAO(new OptionsDAO());
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
	
	  public static void test() {
	      Connection c = null;
	      
	      try {
	    	  System.out.println("test create DB");
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:BewareOfTheTruth.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }

}
