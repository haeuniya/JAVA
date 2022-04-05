import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfPassword;
	private JButton btnLogin;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AdminLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		setVisible(true);
		
		File file = new File("./Room.dat"); // FileŬ������ ���� �̸��� �Ű������� �ϴ� �����ڷ� ��ü�� ������ �� �ִ�.
		try {
			file.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectInputStream inputFile = null; // ��ü ������ �д� ���� ����
		try {
			inputFile = new ObjectInputStream(new FileInputStream(file)); // ��ü ������ �д� ���Ͽ�  file�� ������ �ϴ� �����ڸ� ����
		} catch (Exception e1) {
		} 
		
		// management �ʵ带 �����ϱ� ���� ���� ����
		File file2 = new File("./Manager.dat"); // FileŬ������ ���� �̸��� �Ű������� �ϴ� �����ڷ� ��ü�� ������ �� �ִ�.
		try {
			file2.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DataInputStream inputFile2 = null; // ��ü ������ �д� ���� ����
		try {
			inputFile2 = new DataInputStream(new FileInputStream(file2));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// ����
		File file3 = new File("./ManagerIncome.dat"); // FileŬ������ ���� �̸��� �Ű������� �ϴ� �����ڷ� ��ü�� ������ �� �ִ�.
		try {
			file3.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DataInputStream inputFile3 = null; // ��ü ������ �д� ���� ����
		try {
			inputFile3 = new DataInputStream(new FileInputStream(file3));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		RoomManagement roomMan = new RoomManagement(inputFile3,inputFile2,inputFile); //RoomManagementŬ���� �����ڸ� roomMan���� ����
				
		frame = new JFrame();
				
		setTitle("ADMIN LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelAdminLogin = new JLabel("ADMIN LOGIN");
		labelAdminLogin.setFont(new Font("����", Font.BOLD, 28));
		labelAdminLogin.setBounds(115, 26, 213, 72);
		contentPane.add(labelAdminLogin);
		
		JLabel labelPassword = new JLabel("PASSWORD: ");
		labelPassword.setFont(new Font("����", Font.PLAIN, 20));
		labelPassword.setBounds(80, 101, 129, 30);
		contentPane.add(labelPassword);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(221, 101, 165, 30);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("����", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ư ������");
				System.out.println(tfPassword.getText());
				if(tfPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���");
				} else if(!roomMan.matchMangerID(tfPassword.getText())) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ���ϴ�");
				}
				else {
					JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�");
					AdminMenuGUI adminMenu = new AdminMenuGUI();
					adminMenu.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnLogin.setBounds(164, 156, 116, 35);
		contentPane.add(btnLogin);
		
		JButton btnGB = new JButton("GO BACK TO MAIN");
		btnGB.setFont(new Font("����", Font.BOLD, 12));
		btnGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main = new MainGUI();
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnGB.setBounds(272, 223, 152, 30);
		contentPane.add(btnGB);
		setVisible(true);
	}

}
