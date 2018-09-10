package bewareofthetruth.model.gameMechanics.playerItems;

import bewareofthetruth.contract.model.gameMecanism.IItem;

public class Item implements IItem{

	private boolean isEquipable;
	
	private String name;
	
	public Item(boolean isEquipable) {
		
	}

	public boolean isEquipable() {
		return isEquipable;
	}

	public void setEquipable(boolean isEquipable) {
		this.isEquipable = isEquipable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
