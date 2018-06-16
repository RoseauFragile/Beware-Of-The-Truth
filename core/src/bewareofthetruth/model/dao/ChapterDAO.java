package bewareofthetruth.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChapterDAO extends AbstractDAO {

	private BewareOfTheTruthDAO bewareOfTheTruthDAO;
	
	
	public ChapterDAO() {
		super();
	}

	public ArrayList<LevelSql> getLevelByChapter(int id) throws SQLException {
		return this.getBewareOfTheTruthDAO().getLevelDAO().getLevelById(id);
	}

	public BewareOfTheTruthDAO getBewareOfTheTruthDAO() {
		return this.bewareOfTheTruthDAO;
	}

	public void setBewareOfTheTruthDAO(BewareOfTheTruthDAO bewareOfTheTruthDAO) {
		this.bewareOfTheTruthDAO = bewareOfTheTruthDAO;
	}
	
	
}
