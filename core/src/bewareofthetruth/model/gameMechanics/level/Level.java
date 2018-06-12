package bewareofthetruth.model.gameMechanics.level;

import java.util.ArrayList;

import bewareofthetruth.contract.model.data.IChapter;
import bewareofthetruth.contract.model.data.IGame;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IMap;
import bewareofthetruth.contract.model.gameMecanism.ICharacter;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.gameMecanism.IProjectile;
import bewareofthetruth.contract.model.gameMecanism.ISpecial;
import bewareofthetruth.contract.model.gameMecanism.ITile;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;

public class Level implements ILevel {

	private IMap map;

	ArrayList<ITile> specials = new ArrayList<ITile>();

	ArrayList<ICharacter> characters = new ArrayList<ICharacter>();

	private ISound audio;

	private IDimension dimension;

	private String levelName;

	private IChapter chapter;

	public Level(int idChapter) {
		System.out.println("Level  1 créer");
	}

	@Override
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName() {
		// this.getChapter().getBewareOfTruthModel().getDao().getLevelDAO()
	}

	@Override
	public IPlayer getPlayer() {
		return null;
	}

	@Override
	public IDimension getDimension() {
		return dimension;
	}

	public void setDimension() {
		this.dimension = dimension;
	}

	public void removeSpecials(ISpecial special) {

	}

	public void addSpecials(ISpecial special) {

	}

	@Override
	public void removeCharacters(ICharacter character) {

	}

	@Override
	public void addCharacters(ICharacter character) {

	}

	@Override
	public ArrayList<ITile> getTiles() {
		return this.specials;
	}

	@Override
	public void setTiles(ArrayList<ITile> arrayListTiles) {

	}

	@Override
	public ArrayList<ICharacter> getCharacters() {
		return this.characters;
	}

	@Override
	public void setCharacters(ArrayList<ICharacter> arrayListCharacter) {
	}

	@Override
	public void addProjectiles(IProjectile projectile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSpecials(ITile special) {
		// TODO Auto-generated method stub

	}

	@Override
	public ISound getAudio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IProjectile> getProjectiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ITile> getSpecials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProjectiles(IProjectile projectile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSpecials(ITile special) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAudio(ISound audio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDimension(IDimension dimension) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGame(IGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLevelName(String levelName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMap(IMap map) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProjectiles(ArrayList<IProjectile> projectiles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSpecials(ArrayList<ITile> specials) {
		// TODO Auto-generated method stub

	}

	@Override
	public IChapter getChapter() {
		return chapter;
	}

	@Override
	public void setChapter(IChapter chapter) {
		this.chapter = chapter;
	}

}
