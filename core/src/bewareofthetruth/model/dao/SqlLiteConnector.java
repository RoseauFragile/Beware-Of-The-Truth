package bewareofthetruth.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlLiteConnector {

	private static SqlLiteConnector	instance;
	private static String			url			= "jdbc:sqlite:BewareOfTheTruth.db";
	private Connection				connection;
	private Statement				statement;

	private SqlLiteConnector() {

		this.open();
	}

	public static SqlLiteConnector getInstance() {

		if (instance == null) {
			setInstance(new SqlLiteConnector());
		}
		return instance;
	}

	private static void setInstance(final SqlLiteConnector instance) {

		SqlLiteConnector.instance = instance;
	}

	private boolean open() {

		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(url);
			this.setConnection(c);
			this.statement = this.getConnection().createStatement();
		}
		catch (final Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
		return true;
	}

	public ResultSet executeQuery(final String query) {

		try {
			return this.getStatement().executeQuery(query);
		}
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public java.sql.CallableStatement prepareCall(final String query) {

		try {
			return this.getConnection().prepareCall(query);
		}
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdate(final String query) {

		try {
			return this.statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		}
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Connection getConnection() {

		return this.connection;
	}

	public void setConnection(final Connection connection) {

		this.connection = connection;
	}

	public Statement getStatement() {

		return this.statement;
	}

	public void setStatement(final Statement statement) {

		this.statement = statement;
	}
}
