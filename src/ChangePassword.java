import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfNewPassword;
	private JTextField tfConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ChangePassword();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangePassword() {
		setVisible(true);
		
		// ���� 
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
		
		// ������
		frame = new JFrame();
		setTitle("CHANGE PASSWORD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// ���̺�
		JLabel NEWPASSWORD = new JLabel("NEW PASSWORD:");
		NEWPASSWORD.setFont(new Font("����", Font.BOLD, 17));
		NEWPASSWORD.setBounds(56, 119, 218, 24);
		contentPane.add(NEWPASSWORD);
		
		JLabel LOGINID = new JLabel("LOGIN ID:");
		LOGINID.setFont(new Font("����", Font.BOLD, 17));
		LOGINID.setBounds(56, 85, 162, 24);
		contentPane.add(LOGINID);
		
		JLabel lblAdmin = new JLabel("admin");
		lblAdmin.setForeground(new Color(153, 153, 153));
		lblAdmin.setFont(new Font("����", Font.BOLD, 18));
		lblAdmin.setBounds(286, 85, 145, 24);
		contentPane.add(lblAdmin);
		
		JLabel lblNewLabel = new JLabel("CHANGE ADMIN'S PASSWORD");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(128, 27, 318, 31);
		contentPane.add(lblNewLabel);
		
		// �ʵ�
		tfNewPassword = new JTextField();
		tfNewPassword.setColumns(10);
		tfNewPassword.setBounds(286, 121, 194, 24);
		contentPane.add(tfNewPassword);
		
		// ���� ��ư
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNewPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"���ο� ��й�ȣ�� �Է����ּ���!","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					// ��ư Ŭ�� �� ������ ��й�ȣ �ٲ�
					String newPassword = tfNewPassword.getText();
					roomMan.setManagerPW(newPassword);
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ���������� �ٲ�����ϴ�.");
					try {
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
						DataOutputStream output2 = new DataOutputStream(new FileOutputStream(file2));
						DataOutputStream output3 = new DataOutputStream(new FileOutputStream(file3));
						roomMan.writeRoomInfos(output,output2,output3);
						output.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setFont(new Font("����", Font.BOLD, 14));
		btnSave.setBounds(264, 180, 95, 23);
		contentPane.add(btnSave);
		
		// �޴��� ���ư��� ��ư
		JButton btnNewButton = new JButton("GO BACK TO MENU");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenuGUI adminMenu = new AdminMenuGUI();
				adminMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 12));
		btnNewButton.setBounds(371, 180, 153, 23);
		contentPane.add(btnNewButton);
		
		
	}

}
