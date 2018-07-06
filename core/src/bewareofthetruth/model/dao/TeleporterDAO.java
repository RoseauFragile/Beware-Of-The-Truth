package bewareofthetruth.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeleporterDAO extends AbstractDAO{

	private static int	idLevelColumn = 2;
	private static int idNextLevelColumn = 3;
	private static int idTeleporterColum = 1;
	private static int xColumn = 4;
	private static int yColumn = 5;
	private static int xSpawnColumn = 6;
	private static int ySpawnColumn = 7;
	private int idLevel;
	
	public TeleporterDAO() {	
	}
	
	public ArrayList<TeleporterSql> getTeleportersByIdLevel(final int idLevel) throws SQLException{
	String getTeleportersByIdLevel = "SELECT Teleporter.ID_Teleporter, Teleporter.ID_Level,Teleporter.Teleporter_Level_Correponding AS NextLevel, Position.X AS x, Position.Y AS y, SpawnPoint.x AS xSpawn, SpawnPoint.y As ySpawn\r\n" + 
			"FROM Teleporter, Level, situe, Position, SpawnPoint\r\n" + 
			"WHERE Teleporter.ID_Teleporter = situe.ID_Teleporter AND Teleporter.ID_SpawnPoint = SpawnPoint.ID_SpawnPoint AND Teleporter.ID_Level = Level.ID_Level AND situe.ID_Positon = Position.ID_Positon AND Level.ID_Level = " +idLevel;
	
	final ArrayList<TeleporterSql> teleporterSql = new ArrayList<TeleporterSql>();
	ResultSet rs = executeQuery(getTeleportersByIdLevel);

    while ( rs.next() ) {
    	teleporterSql.add(new TeleporterSql(rs.getInt(idTeleporterColum), rs.getInt(idLevelColumn), rs.getInt(idNextLevelColumn), rs.getInt(xColumn), rs.getInt(yColumn), rs.getInt(xSpawnColumn), rs.getInt(ySpawnColumn)));
    	}
	  return teleporterSql;
	  }
	
	
	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel( int idLevel) {
		this.idLevel = idLevel;
	}
}
