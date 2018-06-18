package bewareofthetruth.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LevelDAO extends AbstractDAO {

	private static int	idLevelColumn = 1;
	private static int XMobileColumn = 5;
	private static int YMobileColumn = 6;
	private static int nameMobColumn = 3;
	private static int permabilityMobileColumn = 7;
	private static int mobileType = 4;
	private int	idChapter;
	private int idLevel;

	public LevelDAO() {
	}

	
	public ArrayList<LevelSql> getLevelById(final int idChapter) throws SQLException {
		  
	  this.setIdChapter(idChapter);
		  
	  String	getLevelById	= ("SELECT Level.ID_Level, Level.Name_Level, Level.Height, Level.Width, Level.Source_Map\r\n"
	+ "FROM Level, Chapter, comporte\r\n"
	+ "WHERE Level.ID_Level = comporte.ID_Level AND Chapter.ID_Chapter = comporte.ID_Chapter AND comporte.ID_Chapter = "
					+ this.getIdChapter());
			
	  final ArrayList<LevelSql> levelSql = new ArrayList<LevelSql>();
      ResultSet rs = executeQuery(getLevelById);
      
      while ( rs.next() ) {
    	  levelSql.add(new LevelSql(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
      }
	  return levelSql;
	  }
	
	public ArrayList<MobileSql> getMobilesByLevelId(final int idLevel) throws SQLException {
		
		this.setIdLevel(idLevel);
		
		final ArrayList<MobileSql> mobileSql = new ArrayList<MobileSql>();

		String getMobilesByLevelId = ( "SELECT Level.ID_Level, Mobile.ID_Mobile, Mobile.Name_Mobile, Mobile.ID_Type,"
				+ " Position.X, Position.Y, Permeability.Name_Permeability FROM Level, Permeability, Mobile, contient, Type, PositionWHERE level.ID_Level = contient.ID_Level AND "
				+ "contient.ID_Mobile = Mobile.ID_Mobile AND Mobile.ID_Positon = Position.ID_Positon AND Mobile.ID_Type = Type.ID_Type AND Permeability.ID_Permeability = Type.ID_Permeability AND Level.ID_Level = " 
				+ this.getIdLevel());
		
		ResultSet rs = executeQuery(getMobilesByLevelId);
		while (rs.next()) {
			mobileSql.add(new MobileSql(rs.getInt(XMobileColumn), rs.getInt(YMobileColumn), rs.getString(nameMobColumn), rs.getString(permabilityMobileColumn), rs.getInt(idLevelColumn), rs.getInt(mobileType)));
		}
		return mobileSql;	
	}
	
	
	
	 
	public int getIdChapter() {
		return this.idChapter;
	}

	public void setIdChapter( int idChapter) {
		this.idChapter = idChapter;
	}


	public int getIdLevel() {
		return idLevel;
	}


	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}
}
