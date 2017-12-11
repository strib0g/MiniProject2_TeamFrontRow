package src;

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
import javax.swing.JTextField;

public class LibrarianSection extends JFrame {

	static LibrarianSection frame;
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
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 591);
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
				UserForm.main(new String [] {});// edit the UserForm Class
				dispose();
			}
		});
		btnAddUser.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnAddUser.setBounds(50, 78, 412, 47);
		contentPane.add(btnAddUser);
		
		JButton btnViewUser = new JButton("View user");
		btnViewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUser.main(new String [] {});
				dispose();
			}
		});
		btnViewUser.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnViewUser.setBounds(50, 138, 412, 47);
		contentPane.add(btnViewUser);
		
		JButton btnRemoveUser = new JButton("Remove user");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveUser.main(new String[] {});
				dispose();
				
			}
		});
		btnRemoveUser.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnRemoveUser.setBounds(50, 198, 412, 47);
		contentPane.add(btnRemoveUser);
		
		JButton btnAddBook = new JButton("Add book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksForm.main(new String [] {});
				dispose();
				
			}
		});
		btnAddBook.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnAddBook.setBounds(50, 258, 412, 47);
		contentPane.add(btnAddBook);
		
		JButton btnViewBook = new JButton("View book");
		btnViewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooks.main(new String [] {} );
				dispose( );
				
			}
		});
		btnViewBook.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnViewBook.setBounds(50, 318, 412, 47);
		contentPane.add(btnViewBook);
		
		JButton btnRemoveBook = new JButton("Remove book");
		btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveBook.main(new String[] {});
				dispose();	
			}
		});
		btnRemoveBook.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnRemoveBook.setBounds(50, 378, 412, 47);
		contentPane.add(btnRemoveBook);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Library.main(new String [] {});
				dispose();
			}
		});
		btnLogout.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnLogout.setBounds(90, 468, 339, 47);
		contentPane.add(btnLogout);
	}
}
