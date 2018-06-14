package bewareofthetruth.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO extends AbstractDAO {

	private static int	idLevelColumn = 2;
	private static int idPlayerColumn = 6;
	private static int idChapterColum = 1;
	private static int namePlayerColumn = 4;
	private static int idInventoryColumn = 5;
	private int idLevel;

	public PlayerDAO() {
	}

	
	public PlayerSql getPlayerByIdLevel(final int idLevel) throws SQLException {
		  
	  this.setIdLevel(idLevel);
		  
	  String	getPlayerByIdLevel	= ("SELECT Chapter.ID_Chapter, Level.ID_Level,Player.ID_Level AS ID_Level_Player, Player.Name_Player, Player.ID_Inventory, Player.ID_Player\r\n" + 
	  		"FROM Chapter, Level, Player, comporte\r\n" + 
	  		"WHERE Chapter.ID_Chapter = comporte.ID_Chapter AND Level.ID_Level = comporte.ID_Level AND Player.ID_Level = Level.ID_Level AND Player.ID_Player =  " + this.getIdLevel());
      ResultSet rs = executeQuery(getPlayerByIdLevel);
      PlayerSql playerSql = new PlayerSql(rs.getInt(idPlayerColumn), rs.getString(namePlayerColumn), rs.getInt(idLevelColumn), rs.getInt(idInventoryColumn), rs.getInt(idChapterColum));
	  return playerSql;
	  }
	 
	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel( int idLevel) {
		this.idLevel = idLevel;
	}
}
