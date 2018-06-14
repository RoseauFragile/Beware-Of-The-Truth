package bewareofthetruth.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LevelDAO extends AbstractDAO {

	private int	id;

	public LevelDAO() {
	}

	
	public ArrayList<LevelSql> getLevelById(final int id) throws SQLException {
		  
	  this.setId(id);
		  
	  String	getLevelById	= ("SELECT Level.ID_Level, Level.Name_Level, Level.Height, Level.Width, Level.Source_Map\r\n"
	+ "FROM Level, Chapter, comporte\r\n"
	+ "WHERE Level.ID_Level = comporte.ID_Level AND Chapter.ID_Chapter = comporte.ID_Chapter AND comporte.ID_Chapter = "
					+ this.getId());
			
	  final ArrayList<LevelSql> levelSql = new ArrayList<LevelSql>();
      ResultSet rs = executeQuery(getLevelById);
      
      while ( rs.next() ) {
    	  levelSql.add(new LevelSql(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
      }
	  return levelSql;
	  }
	 
	public int getId() {
		return this.id;
	}

	public void setId( int id) {
		this.id = id;
	}
}
