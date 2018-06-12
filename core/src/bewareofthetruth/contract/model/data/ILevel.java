package bewareofthetruth.contract.model.data;

import java.util.ArrayList;

import bewareofthetruth.contract.model.gameMecanism.ICharacter;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.gameMecanism.IProjectile;
import bewareofthetruth.contract.model.gameMecanism.ITile;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;

/* **** POUR BEN **** INTERFACE QUI SE TROUVERA DANS LE GAME CONTAINER ACTUELLEMENT LE BEWWAREOFTRUTHMODEL, LE LEVEL CONTIENT LA MAP ET PLUSIEURS ARRAYLIST */

public interface ILevel {

	public void addCharacters(final ICharacter character);

	public void addProjectiles(final IProjectile projectile);

	public void addSpecials(final ITile special);

	public ISound getAudio();

	public ArrayList<ICharacter> getCharacters();

	public IDimension getDimension();

	public String getLevelName();

	public IMap getMap();

	public IPlayer getPlayer();

	public ArrayList<IProjectile> getProjectiles();

	public ArrayList<ITile> getSpecials();

	public ArrayList<ITile> getTiles();

	public void removeCharacters(final ICharacter character);

	public void removeProjectiles(final IProjectile projectile);

	public void removeSpecials(final ITile special);

	public void setAudio(ISound audio);

	public void setCharacters(ArrayList<ICharacter> arrayListCharacter);

	public void setDimension(IDimension dimension);

	public void setGame(final IGame game);

	public void setLevelName(String levelName);

	public void setMap(IMap map);

	public void setPlayer(IPlayer player);

	public void setProjectiles(ArrayList<IProjectile> projectiles);

	public void setSpecials(ArrayList<ITile> specials);

	public void setTiles(ArrayList<ITile> arrayListTiles);

	public IChapter getChapter();

	public void setChapter(IChapter chapter);

}
