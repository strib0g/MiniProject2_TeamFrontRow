package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveUser frame = new RemoveUser();
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
	public RemoveUser() {
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRemoveUser = new JLabel("Remove user");
		lblRemoveUser.setToolTipText("");
		lblRemoveUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveUser.setFont(new Font("Sitka Display", Font.BOLD, 28));
		lblRemoveUser.setBounds(30, 13, 334, 34);
		contentPane.add(lblRemoveUser);
		
		JLabel lblEnterId = new JLabel("Enter ID");
		lblEnterId.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblEnterId.setBounds(12, 95, 117, 29);
		contentPane.add(lblEnterId);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(119, 96, 176, 29);
		contentPane.add(textFieldID);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianSection.main(new String [] {});
				dispose();
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(SystemColor.info);
		button.setBounds(301, 228, 85, 25);
		contentPane.add(button);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String removeID = textFieldID.getText();
				if(removeID ==null || removeID.trim().equals("")) {
					JOptionPane.showMessageDialog(RemoveUser.this, "ID can't be blank!");
				}else{
					int id = Integer.parseInt(removeID);
					int i = UserDao.delete(id); //A class UserDao must be created with the code that would delete a user from the database through his ID. 
					if (i>0) {
						JOptionPane.showMessageDialog(RemoveUser.this,"Record deleted successfully!" );
					}else {					
					JOptionPane.showMessageDialog(RemoveUser.this, "Unable to delete given ID!");
					
					}
				}
			}
		});
		btnRemove.setFont(new Font("Sitka Display", Font.BOLD, 20));
		btnRemove.setBounds(145, 170, 117, 34);
		contentPane.add(btnRemove);
	}

}
