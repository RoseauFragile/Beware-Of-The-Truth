package bewareofthetruth.model.gameMechanics.entity.mobile;

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
		

	}

	@Override
	public int getAmmo() {
		
		return 0;
	}

	@Override
	public ISound getAudio() {
		
		return null;
	}

	@Override
	public IDimension getDimension() {
		
		return null;
	}

	@Override
	public int getEnergy() {
		
		return 0;
	}

	@Override
	public ArrayList<IEquipment> getEquipments() {
		
		return null;
	}

	@Override
	public boolean getIsPlayer() {
		
		return false;
	}

	@Override
	public int getSprite() {
		
		return 0;
	}

	@Override
	public int getWater() {
		
		return 0;
	}

	@Override
	public void setAmmo(int ammo) {
	

	}

	@Override
	public void setAudio(ISound audio) {
		

	}

	@Override
	public void setDimension(IDimension dimension) {
		

	}

	@Override
	public void setEnergy(int energy) {
		

	}

	@Override
	public void setEquipment(ArrayList<IEquipment> equipment) {
		

	}

	@Override
	public void setLevel(ILevel level) {
		

	}

	@Override
	public void setSprite(int sprite) {
		

	}

	@Override
	public void setWater(int water) {
	

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
