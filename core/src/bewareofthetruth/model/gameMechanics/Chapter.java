package bewareofthetruth.model.gameMechanics;

import java.sql.SQLException;
import java.util.ArrayList;
import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.model.dao.LevelSql;
import bewareofthetruth.model.gameMechanics.level.Level;

public class Chapter implements IChapter {

	private IBewareOfTruthModel bewareOfTruthModel;
	private ArrayList<ILevel> levels;
	private int idChapter;

	public Chapter(int idChapter) throws SQLException {
		this.setIdChapter(idChapter);
		this.levels = new ArrayList<ILevel>();
	}

	@Override
	public ArrayList<ILevel> getLevels() {
		return this.levels;
	}

	@Override
	public IBewareOfTruthModel getBewareOfTruthModel() {
		return this.bewareOfTruthModel;
	}

	@Override
	public void setBewareOfTruthModel(final IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	@Override
	public void setLevel() throws SQLException {
		ArrayList<LevelSql> levelSql = this.getBewareOfTruthModel().getDao().getChapterDAO()
				.getLevelByChapter(this.getIdChapter());
		;
		for (LevelSql temp : levelSql) {
			this.levels.add(new Level(temp.getId(), temp.getLevelName(), temp.getHeight(), temp.getWidth(),
					temp.getSourceMap()));
		}
		for (ILevel temp : this.levels) {
			temp.setChapter(this);
			temp.setMobiles();
		}
	}

	@Override
	public int getIdChapter() {
		return this.idChapter;
	}

	@Override
	public void setIdChapter( int idChapter) {
		this.idChapter = idChapter;
	}
}