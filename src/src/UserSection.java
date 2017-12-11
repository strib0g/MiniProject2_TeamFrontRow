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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSection frame = new UserSection();
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
	public UserSection() {
		setTitle("Library system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserSection = new JLabel("User Section");
		lblUserSection.setToolTipText("");
		lblUserSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserSection.setFont(new Font("Sitka Display", Font.BOLD, 27));
		lblUserSection.setBounds(-17, 0, 504, 34);
		contentPane.add(lblUserSection);
		
		JButton btnBorrowABook = new JButton("Borrow a book");
		btnBorrowABook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			BorrowABook.main(new String [] {});
			dispose();
			}
		});
		btnBorrowABook.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnBorrowABook.setBounds(21, 65, 412, 47);
		contentPane.add(btnBorrowABook);
		
		JButton btnReturnABook = new JButton("Return a book");
		btnReturnABook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnABook.main(new String [] {});
				dispose();
			}
		});
		btnReturnABook.setFont(new Font("Sitka Display", Font.BOLD, 30));
		btnReturnABook.setBounds(21, 125, 412, 47);
		contentPane.add(btnReturnABook);
		
		JButton button_6 = new JButton("Logout");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Library.main(new String [] {});
				dispose();
			}
		});
		button_6.setFont(new Font("Sitka Display", Font.BOLD, 30));
		button_6.setBounds(55, 214, 339, 47);
		contentPane.add(button_6);
	}

}
