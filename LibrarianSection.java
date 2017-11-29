package package1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibrarianSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianSection frame = new LibrarianSection();
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
	public LibrarianSection() {
		setTitle("Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibrarianSection = new JLabel("Librarian Section");
		lblLibrarianSection.setToolTipText("");
		lblLibrarianSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibrarianSection.setFont(new Font("Sitka Display", Font.BOLD, 27));
		lblLibrarianSection.setBounds(12, 13, 504, 34);
		contentPane.add(lblLibrarianSection);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserForm.main(new String [] {}); // edit the UserForm Class
				
			}
		});
		btnAddUser.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnAddUser.setBounds(50, 78, 412, 47);
		contentPane.add(btnAddUser);
		
		JButton btnRemoveUser = new JButton("View User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveUser.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnRemoveUser.setBounds(50, 158, 412, 47);
		contentPane.add(btnRemoveUser);
		
		JButton btnViewUser = new JButton("Remove User");
		btnViewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewUser.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnViewUser.setBounds(50, 239, 412, 47);
		contentPane.add(btnViewUser);
		
		JButton button_2 = new JButton("");
		button_2.setFont(new Font("Sitka Display", Font.BOLD, 30));
		button_2.setBounds(50, 323, 412, 47);
		contentPane.add(button_2);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogout.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnLogout.setBounds(50, 411, 412, 47);
		contentPane.add(btnLogout);
	}
}
