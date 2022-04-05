import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

    private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setVisible(true);
		
		frame = new JFrame();
		setTitle("STUDY ROOM - MAIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		JLabel labelWelcome = new JLabel("WELCOME TO STUDY ROOM  :)");
		labelWelcome.setFont(new Font("굴림", Font.BOLD, 25));
		labelWelcome.setBounds(74, 83, 406, 62);
		contentPane.add(labelWelcome);
		
		// 사용자 버튼
		JButton btnUser = new JButton("USER");
		btnUser.setFont(new Font("굴림", Font.BOLD, 20));
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMenu userMenu = new UserMenu();
				userMenu.setVisible(true);
				setVisible(false);
				/*UserLoginGUI userLogin = new UserLoginGUI();
				userLogin.setVisible(true);
				frame.dispose();*/
			}
		});
		btnUser.setBounds(25, 199, 164, 43);
		contentPane.add(btnUser);
		
		// 관리자 버튼
		JButton btnAdmin = new JButton("ADMIN");
		btnAdmin.setFont(new Font("굴림", Font.BOLD, 20));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminLogin();
				setVisible(false);
			}
		});
		btnAdmin.setBounds(210, 199, 164, 43);
		contentPane.add(btnAdmin);
		
		// 종료 버튼
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("굴림", Font.BOLD, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Goodbye");
				System.exit(0);
			}
		});
		btnExit.setBounds(396, 199, 165, 43);
		contentPane.add(btnExit);
	}

}
