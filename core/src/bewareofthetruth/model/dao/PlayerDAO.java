package bewareofthetruth.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerDAO extends AbstractDAO {

	private int	idLevel;

	public PlayerDAO() {
	}

	
	public PlayerSql getPlayerByIdLevel(final int idLevel) throws SQLException {
		  
	  this.setIdLevel(idLevel);
		  
	  String	getPlayerByIdLevel	= ("SELECT Player.ID_Player, Player.Name_Player, Player.ID_Level, Player.ID_Inventory\r\n" + 
	  		"FROM Player\r\n" + 
	  		"WHERE Player.ID_Level = " + this.getIdLevel());
      ResultSet rs = executeQuery(getPlayerByIdLevel);
      PlayerSql playerSql = new PlayerSql(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
	  return playerSql;
	  }
	 
	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel( int idLevel) {
		this.idLevel = idLevel;
	}
}
