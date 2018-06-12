package bewareofthetruth.model.gameMechanics;

import java.util.ArrayList;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.model.gameMechanics.level.Level;

public class Chapter implements IChapter {

	private IBewareOfTruthModel bewareOfTruthModel;
	private ArrayList<ILevel> levels;
	private int idChapter;

	public Chapter() {
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
	public void setLevel() {
		ILevel level = new Level(this.getIdChapter());
		level.setChapter(this);
		this.levels.add(level);
	}

	@Override
	public int getIdChapter() {
		return this.idChapter;
	}

	@Override
	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}
}