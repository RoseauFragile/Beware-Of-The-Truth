package bewareofthetruth.model.gameMechanics.mobile;

import java.util.ArrayList;

import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.gameMecanism.ICharacter;
import bewareofthetruth.contract.model.gameMecanism.IEquipment;
import bewareofthetruth.contract.model.utils.IDimension;
import bewareofthetruth.contract.model.utils.ISound;

public class Character implements ICharacter {

	private int speed;

	private String name;

	private int health;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void equip() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAmmo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ISound getAudio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDimension getDimension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<IEquipment> getEquipments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getIsPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSprite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWater() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAmmo(int ammo) {
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
	public void setEnergy(int energy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEquipment(ArrayList<IEquipment> equipment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLevel(ILevel level) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSprite(int sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWater(int water) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public int getHealth() {
		return this.health;
	}
}
