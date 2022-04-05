import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CheckOutGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfphoneNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CheckOutGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public CheckOutGUI() {
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
		setTitle("CHECK-OUT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Phone Number:");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 21));
		lblNewLabel.setBounds(51, 54, 165, 22);
		contentPane.add(lblNewLabel);
		
		tfphoneNo = new JTextField();
		tfphoneNo.setBounds(227, 54, 201, 25);
		contentPane.add(tfphoneNo);
		tfphoneNo.setColumns(10);
		
		// üũ�ƿ�
		JButton btnNewButton = new JButton("CHECK-OUT");
		btnNewButton.setFont(new Font("����", Font.BOLD, 16));
		System.out.println("üũ�ƿ� ��ư ����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String phoneNo = tfphoneNo.getText();
				if(phoneNo.equals("")){
					JOptionPane.showMessageDialog(null,"�ڵ��� ��ȣ�� �Է����ּ���!.","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println(tfphoneNo);
					User user = new User(phoneNo);
					System.out.println(user);
					try {
						int checkOut = roomMan.checkOut(user);
						//int payBill = roomMan.checkOut(user);
						roomMan.total_income += checkOut;
						roomMan.setIncome(checkOut);
						JOptionPane.showMessageDialog(null, "üũ�ƿ� �Ǿ����ϴ�");
						//int payment = roomMan.getPayment(user);
						
						System.out.println("üũ�ƿ��Ϸ�");
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
						DataOutputStream output2 = new DataOutputStream(new FileOutputStream(file2));
						DataOutputStream output3 = new DataOutputStream(new FileOutputStream(file3));
						roomMan.writeRoomInfos(output,output2,output3);
						output.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(159, 111, 150, 32);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("GO BACK");
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMenu userMenu = new UserMenu();
				userMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(365, 171, 109, 32);
		contentPane.add(btnNewButton_1);
	}

}
