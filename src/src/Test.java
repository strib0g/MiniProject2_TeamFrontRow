package src;

import java.util.*;
import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;


public class Test {
		public static void main(String[] args) throws Exception {
		    // The Connection is obtained
			Statement stmt = null;
			String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
			String username = "teamone";
			String password = "HSaaD5vtp3K6QERq";
			
			Connection conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery("select * from book");

		    // It creates and displays the table
		    JTable table = new JTable(buildTableModel(rs));

		    // Closes the Connection

		    JOptionPane.showMessageDialog(null, new JScrollPane(table));
		    conn.close();

		}
		

public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

	}
}
