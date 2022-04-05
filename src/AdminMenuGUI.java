import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class AdminMenuGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AdminMenuGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMenuGUI() {
		setVisible(true);
		
		frame = new JFrame();
		setTitle("ADMIN MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		// °ü¸®ÀÚ ¸Þ´º ¹öÆ°
		JButton btnRoomMan = new JButton("ROOM MANAGEMENT");
		btnRoomMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagementGUI manage = new ManagementGUI();
				manage.setVisible(true);
				setVisible(false);
			}
		});
		btnRoomMan.setFont(new Font("±¼¸²", Font.BOLD, 16));
		btnRoomMan.setBounds(55, 108, 217, 47);
		contentPane.add(btnRoomMan);
		
		// ¼öÀÔ È®ÀÎ ¹öÆ°
		JButton btnCheckIncome = new JButton("CHECK INCOME");
		btnCheckIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckIncomeGUI income = new CheckIncomeGUI();
				income.setVisible(true);
				setVisible(false);
			}
		});
		btnCheckIncome.setFont(new Font("±¼¸²", Font.BOLD, 16));
		btnCheckIncome.setBounds(310, 108, 217, 47);
		contentPane.add(btnCheckIncome);
		
		JButton btnNChangePassword = new JButton("CHANGE PASSWORD");
		btnNChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword changepassword = new ChangePassword();
				changepassword.setVisible(true);
				setVisible(false);
			}
		});
		btnNChangePassword.setFont(new Font("±¼¸²", Font.BOLD, 16));
		btnNChangePassword.setBounds(55, 194, 217, 45);
		contentPane.add(btnNChangePassword);
		
		JButton btnGBTM = new JButton("GO BACK TO MAIN");
		btnGBTM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main = new MainGUI();
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnGBTM.setFont(new Font("±¼¸²", Font.BOLD, 16));
		btnGBTM.setBounds(310, 194, 217, 45);
		contentPane.add(btnGBTM);
		
		JLabel lblNewLabel = new JLabel("ADMIN MENU");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 30));
		lblNewLabel.setBounds(197, 36, 204, 38);
		contentPane.add(lblNewLabel);
	}

}
