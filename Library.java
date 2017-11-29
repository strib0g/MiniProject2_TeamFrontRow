package package1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Library extends JFrame {
	static Library frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library frame = new Library();
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
	public Library() {
		setForeground(SystemColor.activeCaption);
		setTitle("Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLibrarian = new JButton("Librarian ");
		btnLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Librarian.main(new String[] {}); //calling the Librarian class 
				frame.dispose(); 
			}
		});
		btnLibrarian.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnLibrarian.setBounds(12, 25, 160, 79);
		contentPane.add(btnLibrarian);
		
		JButton btnUser = new JButton("User ");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User.main(new String[] {});
			}
		});
		btnUser.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnUser.setBounds(213, 25, 160, 79);
		contentPane.add(btnUser);
		
	
	}

}
