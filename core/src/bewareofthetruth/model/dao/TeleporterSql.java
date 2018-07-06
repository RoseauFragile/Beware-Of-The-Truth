package bewareofthetruth.model.dao;

public class TeleporterSql {

	private int idTeleporter;
	private int idLevel;
	private int idNextLevel;
	private int x;
	private int y;
	private int xSpawn;
	private int ySpawn;
	
	public TeleporterSql(int idTeleporter, int idLevel, int idNextLevel, int x, int y, int xSpawn, int ySpawn) {
		this.setIdTeleporter(idTeleporter);
		this.setIdLevel(idLevel);
		this.setIdNextLevel(idNextLevel);
		this.setX(x);
		this.setY(y);
		this.setxSpawn(xSpawn);
		this.setySpawn(ySpawn);
	}

	public int getIdTeleporter() {
		return this.idTeleporter;
	}

	public void setIdTeleporter(int idTeleporter) {
		this.idTeleporter = idTeleporter;
	}

	public int getIdNextLevel() {
		return this.idNextLevel;
	}

	public void setIdNextLevel(int idNextLevel) {
		this.idNextLevel = idNextLevel;
	}

	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSpawn() {
		return xSpawn;
	}

	public void setxSpawn(int xSpawn) {
		this.xSpawn = xSpawn;
	}

	public int getySpawn() {
		return ySpawn;
	}

	public void setySpawn(int ySpawn) {
		this.ySpawn = ySpawn;
	}

}
