package bewareofthetruth.contract.model.gameMecanism;

import java.util.ArrayList;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;

/* **** ALEXIS **** INTERFACE UTILISE POUR LES METHODES PRINCIPALE DE CHARACTER A TOI DE VOIR SI TU VEUX QUE LA CLASSE CHARACTER SOIT ABSTRAITE OU NON,
 * SI TU L'AS MET ABSTRAITE TU SERAS OBLIGE DE REDEFINIR LES METHODES DANS CLASSE QUI EN HEREITE AU LIEU DE JUSTE CHARACTER*/

//19.05 Ryo : ajout des méthodes nécessaires au contrôleur + modification de la méthode setHealth()

public interface ICharacter {

	public void equip();

	public int getAmmo();

	public ISound getAudio();

	public IDimension getDimension();

	public int getEnergy();

	public ArrayList<IEquipment> getEquipments();

	public boolean getIsPlayer();

	public String getName();

	public int getSpeed();

	public int getSprite();

	public int getWater();

	public void setAmmo(final int ammo);

	public void setAudio(ISound audio);

	public void setDimension(IDimension dimension);

	public void setEnergy(final int energy);

	public void setEquipment(ArrayList<IEquipment> equipment);

	public void setHealth(
			final int health); /*
								 * 19.05 Ryo : J'ai modifé le pramètre de
								 * IHealth en int, c'est trop encombrant d'avoir
								 * des classes pour les attributs contenant un
								 * seul nombre
								 */

	public void setLevel(ILevel level);

	public void setName(String name);

	public void setSprite(int sprite);

	public void setWater(final int water);

	public int getHealth();
	
	public void setSpeed(int speed);

}
