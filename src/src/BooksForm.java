package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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



public class BooksForm extends JFrame {

	static BooksForm frame;
	private JPanel contentPane;
	private JTextField textFieldTitle;
	private JTextField textFieldPublisher;
	private JTextField textFieldAvailability;
	private JTextField textFieldAuthorFirstname;
	private JTextField textFieldAuthorSurname;
	private JTextField textFieldID;
	private JTextField textFieldShelfNumber;
	private JTextField textFieldGenre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksForm frame = new BooksForm();
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
	public BooksForm() {
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddUser = new JLabel("Add Book");
		lblAddUser.setToolTipText("");
		lblAddUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddUser.setFont(new Font("Sitka Display", Font.BOLD, 27));
		lblAddUser.setBounds(23, 13, 457, 34);
		contentPane.add(lblAddUser);
		
		JLabel lblName = new JLabel("ID:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblName.setBounds(51, 89, 141, 29);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("Title:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblId.setBounds(51, 131, 141, 29);
		contentPane.add(lblId);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublisher.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblPublisher.setBounds(51, 173, 141, 29);
		contentPane.add(lblPublisher);
		
		JLabel lblPhoneNumber = new JLabel("Availability:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblPhoneNumber.setBounds(51, 215, 141, 29);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblE = new JLabel("Author firstname");
		lblE.setHorizontalAlignment(SwingConstants.RIGHT);
		lblE.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblE.setBounds(23, 257, 169, 29);
		contentPane.add(lblE);
		
		JLabel lblAccumulatedFines = new JLabel("Author surname:");
		lblAccumulatedFines.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccumulatedFines.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblAccumulatedFines.setBounds(12, 299, 180, 29);
		contentPane.add(lblAccumulatedFines);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setColumns(10);
		textFieldTitle.setBounds(204, 131, 257, 29);
		contentPane.add(textFieldTitle);
		
		textFieldPublisher = new JTextField();
		textFieldPublisher.setColumns(10);
		textFieldPublisher.setBounds(204, 177, 257, 29);
		contentPane.add(textFieldPublisher);
		
		textFieldAvailability = new JTextField();
		textFieldAvailability.setColumns(10);
		textFieldAvailability.setBounds(204, 219, 257, 29);
		contentPane.add(textFieldAvailability);
		
		textFieldAuthorFirstname = new JTextField();
		textFieldAuthorFirstname.setColumns(10);
		textFieldAuthorFirstname.setBounds(204, 261, 257, 29);
		contentPane.add(textFieldAuthorFirstname);
		
		textFieldAuthorSurname = new JTextField();
		textFieldAuthorSurname.setColumns(10);
		textFieldAuthorSurname.setBounds(204, 303, 257, 29);
		contentPane.add(textFieldAuthorSurname);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianSection.main(new String [] {});
				dispose();
				
			}
		});
		btnBack.setBackground(SystemColor.info);
		btnBack.setForeground(SystemColor.textText);
		btnBack.setBounds(395, 524, 85, 25);
		contentPane.add(btnBack);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(204, 89, 257, 29);
		contentPane.add(textFieldID);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = textFieldID.getText();
				String Title = textFieldTitle.getText();
				String Publisher = textFieldPublisher.getText();
				String Availability = textFieldAvailability.getText();
				String AuthorFirstname = textFieldAuthorFirstname.getText();
				String AuthorSurname = textFieldAuthorSurname.getText();
				String ShelfNumber = textFieldShelfNumber.getText();
				String Genre = textFieldGenre.getText();
				
				int i = BookDao.save(ID, Title, Publisher, Availability, AuthorFirstname, AuthorSurname, ShelfNumber, Genre);  
				//This is the SQL related code. It will be put in a class called BookDao. The class will be created by Filip ad Majed. 
					
				if (i>0) {
					JOptionPane.showMessageDialog(BooksForm.this, "book added successfully!");
					LibrarianSection.main(new String [] {});
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(BooksForm.this,"Sorry, unable to save!");
				}
				}
				
		});
		btnAdd.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnAdd.setBounds(160, 456, 180, 47);
		contentPane.add(btnAdd);
		
		JLabel label = new JLabel("Shelf number: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Sitka Display", Font.BOLD, 22));
		label.setBounds(12, 341, 180, 29);
		contentPane.add(label);
		
		textFieldShelfNumber = new JTextField();
		textFieldShelfNumber.setColumns(10);
		textFieldShelfNumber.setBounds(204, 345, 257, 29);
		contentPane.add(textFieldShelfNumber);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenre.setFont(new Font("Sitka Display", Font.BOLD, 22));
		lblGenre.setBounds(12, 383, 180, 29);
		contentPane.add(lblGenre);
		
		textFieldGenre = new JTextField();
		textFieldGenre.setColumns(10);
		textFieldGenre.setBounds(204, 387, 257, 29);
		contentPane.add(textFieldGenre);
	}

}
