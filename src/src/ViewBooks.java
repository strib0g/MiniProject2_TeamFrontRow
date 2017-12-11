package src;

import java.util.*;
import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewBooks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblUsersTable;
	private JButton button;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks frame = new ViewBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewBooks() {
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 971, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String data [][]=null;
		String column []=null;
		
		try {
			//TODO
			//Add code to receive data from the database 
			Connection con=DB.login();
			PreparedStatement ps=con.prepareStatement("select * from book",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			int cols=rsmd.getColumnCount();
			column=new String[cols];
			for(int i=1;i<=cols;i++){
				column[i-1]=rsmd.getColumnName(i);
			}
			
			rs.last();
			int rows=rs.getRow();
			rs.beforeFirst();

			data=new String[rows][cols];
			int count=0;
			while(rs.next()){
				for(int i=1;i<=cols;i++){
					data[count][i-1]=rs.getString(i);
				}
				count++;
			}
			con.close();
			
			
		}catch (Exception e){
			System.out.println(e);
		}
		contentPane.setLayout(null);
		
		table = new JTable(data,column);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(5, 5, 943, 402);
		contentPane.add(sp);
		
		lblUsersTable = new JLabel("Books table");
		lblUsersTable.setBounds(90, 436, 709, 86);
		lblUsersTable.setToolTipText("");
		lblUsersTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsersTable.setFont(new Font("Sitka Display", Font.BOLD, 50));
		contentPane.add(lblUsersTable);
		
		button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserForm.main(new String [] {});
				dispose();
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(SystemColor.info);
		button.setBounds(856, 509, 85, 25);
		contentPane.add(button);
	}

}
