package bewareofthetruth.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public abstract class AbstractDAO {

	protected static ResultSet executeQuery(final String query) {
		return SqlLiteConnector.getInstance().executeQuery(query);
	}

	protected static int executeUpdate(final String query) {
		return SqlLiteConnector.getInstance().executeUpdate(query);
	}

	protected static CallableStatement prepareCall(final String query) {
		return SqlLiteConnector.getInstance().prepareCall(query);
	}
}
