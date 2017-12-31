package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Library {
	
	
	public static void main(String[] args) throws SQLException {

		Connection conn = connect();
		String query = "SELECT * FROM book ORDER BY book_publisher;";

		try {
			
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			PrintColumnTypes.printColTypes(rsmd);
			System.out.println("");

			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnName = rsmd.getColumnName(i);
				System.out.print(columnName);
			}
			System.out.println("");

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue);
				}
				System.out.println("");
			}

			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
	}
}

class PrintColumnTypes {
	public static void printColTypes(ResultSetMetaData rsmd) throws SQLException {
		int columns = rsmd.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			int jdbcType = rsmd.getColumnType(i);
			String name = rsmd.getColumnTypeName(i);
			System.out.print("Column " + i + " is JDBC type " + jdbcType);
			System.out.println(", which the DBMS calls " + name);
		}
	}

	/*
	 * // Statement stmt = null; String query =
	 * "SELECT* FROM book ORDER BY book_title;"; System.out.println(query);
	 * 
	 * try { login(); Statement stmt = conn.createStatement();
	 * 
	 * ResultSet rs = stmt.executeQuery(query); System.out.println(rs);
	 * 
	 * ResultSetMetaData rsmd = rs.getMetaData(); System.out.println(rsmd);
	 * 
	 * System.out.println("SELECT* FROM book ORDER BY book_title"); int
	 * columnsNumber = rsmd.getColumnCount(); while (rs.next()) { for (int i = 1; i
	 * <= columnsNumber; i++) { if (i > 1) System.out.print(",  "); String
	 * columnValue = rs.getString(i); System.out.print(columnValue + " " +
	 * rsmd.getColumnName(i)); conn.close(); } System.out.println(""); } } catch
	 * (SQLException e) {
	 * 
	 * }
	 */ // }

}
