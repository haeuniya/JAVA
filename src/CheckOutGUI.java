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
		
		File file = new File("./Room.dat"); // File클래스는 파일 이름을 매개변수로 하는 생성자로 객체를 생성할 수 있다.
		try {
			file.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectInputStream inputFile = null; // 객체 단위로 읽는 파일 선언
		try {
			inputFile = new ObjectInputStream(new FileInputStream(file)); // 객체 단위로 읽는 파일에  file을 변수로 하는 생성자를 만듦
		} catch (Exception e1) {
		} 
		
		// management 필드를 저장하기 위한 파일 생성
		File file2 = new File("./Manager.dat"); // File클래스는 파일 이름을 매개변수로 하는 생성자로 객체를 생성할 수 있다.
		try {
			file2.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DataInputStream inputFile2 = null; // 객체 단위로 읽는 파일 선언
		try {
			inputFile2 = new DataInputStream(new FileInputStream(file2));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 수입
		File file3 = new File("./ManagerIncome.dat"); // File클래스는 파일 이름을 매개변수로 하는 생성자로 객체를 생성할 수 있다.
		try {
			file3.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DataInputStream inputFile3 = null; // 객체 단위로 읽는 파일 선언
		try {
			inputFile3 = new DataInputStream(new FileInputStream(file3));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		RoomManagement roomMan = new RoomManagement(inputFile3,inputFile2,inputFile); //RoomManagement클래스 생성자를 roomMan으로 선언
		
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
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 21));
		lblNewLabel.setBounds(51, 54, 165, 22);
		contentPane.add(lblNewLabel);
		
		tfphoneNo = new JTextField();
		tfphoneNo.setBounds(227, 54, 201, 25);
		contentPane.add(tfphoneNo);
		tfphoneNo.setColumns(10);
		
		// 체크아웃
		JButton btnNewButton = new JButton("CHECK-OUT");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 16));
		System.out.println("체크아웃 버튼 눌림");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String phoneNo = tfphoneNo.getText();
				if(phoneNo.equals("")){
					JOptionPane.showMessageDialog(null,"핸드폰 번호를 입력해주세요!.","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println(tfphoneNo);
					User user = new User(phoneNo);
					System.out.println(user);
					try {
						int checkOut = roomMan.checkOut(user);
						//int payBill = roomMan.checkOut(user);
						roomMan.total_income += checkOut;
						roomMan.setIncome(checkOut);
						JOptionPane.showMessageDialog(null, "체크아웃 되었습니다");
						//int payment = roomMan.getPayment(user);
						
						System.out.println("체크아웃완료");
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
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 14));
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
