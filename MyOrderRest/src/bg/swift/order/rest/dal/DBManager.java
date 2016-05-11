package bg.swift.order.rest.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager implements AutoCloseable {
	
	final String DB_USERNAME = "sid";
	final String DB_PASSWORD = "123456";
	final String DB_URI = "jdbc:mysql://localhost:3306/web_shop";
	
	private Connection con;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBManager(boolean autoOpenConnection) throws SQLException {
        if (autoOpenConnection) {
            open();
        }
    }

    public void open() throws SQLException {

		if (con != null) {
			close();
		}
		con = DriverManager.getConnection(DB_URI, DB_USERNAME, DB_PASSWORD);
	}
	
	public void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
	
	private Map<String, Object> getColumnsAndValuesData(ResultSet rs) throws SQLException {
		Map<String, Object> row = new HashMap<>();

		ResultSetMetaData metaData = rs.getMetaData();

		for (int columnIndex = 1; columnIndex < metaData.getColumnCount(); columnIndex++) {
            Object columnValue = rs.getObject(columnIndex);
            if (columnValue instanceof java.sql.Date) {
                row.put(metaData.getColumnLabel(columnIndex),
                        new java.util.Date(((java.sql.Date) columnValue).getTime()));
            } else {
                row.put(metaData.getColumnLabel(columnIndex), columnValue);
            }
		}

		return row;
	}

	public boolean executeQuery(String query) {
		try (Statement stmt = con.createStatement()) {
			return stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

    public TableRow selectOne(String query) {
        TableRow row = null;

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                Map<String, Object> rowData = getColumnsAndValuesData(rs);
                row = new TableRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

	public int executeUpdate(String query) {
		try (Statement stmt = con.createStatement()) {
			return stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<TableRow> selectMany(String query) {
		List<TableRow> rowList = new ArrayList<>();
		try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Map<String, Object> rowData = getColumnsAndValuesData(rs);
				rowList.add(new TableRow(rowData));	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowList;
	}
}
