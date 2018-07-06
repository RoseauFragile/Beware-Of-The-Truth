package bewareofthetruth.contract.model.gameMecanism;

public interface ITeleporter extends IEntity{

	public int getIdLevel();

	public void setIdLevel(int idLevel);

	public int getIdNextLevel();

	public void setIdNextLevel(int idNextLevel);

	public int getIdTeleporter();

	public void setIdTeleporter(int idTeleporter);
	
	public int getxSpawn();

	public void setxSpawn(int xSpawn);

	public int getySpawn();

	public void setySpawn(int ySpawn);
}
