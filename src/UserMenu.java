import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserMenu extends JFrame {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new UserMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserMenu() {
		setVisible(true);
		
		frame = new JFrame();
		setTitle("USER MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChcekIn = new JButton("CHECK-IN");
		btnChcekIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckInGUI ckeckin = new CheckInGUI();
				ckeckin.setVisible(true);
				setVisible(false);
			}
		});
		btnChcekIn.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btnChcekIn.setBounds(28, 87, 175, 30);
		contentPane.add(btnChcekIn);
		
		JButton btnCheckOut = new JButton("CHECK-OUT");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckOutGUI ckeckout = new CheckOutGUI();
				ckeckout.setVisible(true);
				setVisible(false);
				setLocationRelativeTo(null);
				setResizable(false);
			}
		});
		btnCheckOut.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btnCheckOut.setBounds(235, 87, 175, 30);
		contentPane.add(btnCheckOut);
		
		JButton btnGBTM = new JButton("GO BACK TO MAIN");
		btnGBTM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main = new MainGUI();
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnGBTM.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btnGBTM.setBounds(235, 142, 175, 30);
		contentPane.add(btnGBTM);
		
		JLabel lblNewLabel = new JLabel("USER MENU");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 24));
		lblNewLabel.setBounds(132, 26, 175, 38);
		contentPane.add(lblNewLabel);
	}

}
