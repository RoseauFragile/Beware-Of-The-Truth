package bewareofthetruth.model.dao;

import bewareofthetruth.contract.model.gameMecanism.MobType;

public class MobileSql {

	private float x;
	private float y;
	private String nameMob;
	private String permeability;
	private int level;
	private int mobType;
	private MobType mobtype;
	
	
	public MobileSql(float x, float y, String nameMob, String permeability, int level, int mobType) {
		super();
		this.setX(x);
		this.setY(y);
		this.setNameMob(nameMob);
		this.setLevel(level);
		this.setPermeability(permeability);
		this.setMobType(mobType);
	}
	
	public float getX() {
		return this.x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return this.y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getNameMob() {
		return this.nameMob;
	}
	public void setNameMob(String nameMob) {
		this.nameMob = nameMob;
	}
	public String getPermeability() {
		return this.permeability;
	}
	public void setPermeability(String permeability) {
		this.permeability = permeability;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public int getMobType() {
		return mobType;
	}

	public void setMobType(int mobType) {
		this.mobType = mobType;
	}

	public MobType getMobtype() {
		return mobtype;
	}

	public void setMobtype(MobType mobtype) {
		this.mobtype = mobtype;
	}
	
	public void convertIntTypeIntoMobType(int type) {
		switch(type){
		case 1 :
			this.setMobtype(MobType.ZOMBIE);	
			break;	
		};
	}
	
}
