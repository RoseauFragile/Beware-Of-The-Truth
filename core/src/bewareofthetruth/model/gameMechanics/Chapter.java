package bewareofthetruth.model.gameMechanics;

import java.sql.SQLException;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.model.dao.LevelSql;
import bewareofthetruth.model.gameMechanics.level.Level;

public class Chapter implements IChapter {

	private IBewareOfTruthModel bewareOfTruthModel;
	private ArrayList<ILevel> levels;
	private ArrayList<World> worlds;
	private int idChapter;

	public Chapter() throws SQLException {
		System.out.println("Chapitre créer");
		this.levels = new ArrayList<>();
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
	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	@Override
	public void setLevel() throws SQLException {
		ArrayList<LevelSql> levelSql = this.getBewareOfTruthModel().getDao().getChapterDAO().getLevelByChapter(this.getIdChapter());;
		for( LevelSql temp : levelSql) {
			this.levels.add(new Level(temp.getId(), temp.getLevelName(), temp.getHeight(), temp.getWidth(), temp.getSourceMap()));
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
	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}

	public ArrayList<World> getFirstWorldLevel() {
		return worlds;
	}

	public void setWorlds() throws SQLException {
		
		ArrayList<LevelSql> numberLevels= this.getBewareOfTruthModel().getDao().getChapterDAO().getLevelByChapter(this.getIdChapter());
		this.worlds = new ArrayList<World>();
		this.worlds.add(new World(new Vector2(0,0), true));
		
		for(LevelSql temp : numberLevels) {
			this.worlds.add(new World(new Vector2(0,0), true));
			this.worlds.set((int) temp.getId(), new World(new Vector2(0,0), true));
	}
	}
		
	public World getWorldByIdLevel(int idLevel){
		
		if(this.worlds.get(idLevel) != null) {
			return this.worlds.get(idLevel);
		}
		else return null;
	}
}