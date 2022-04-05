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
				
		setTitle("ADMIN LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelAdminLogin = new JLabel("ADMIN LOGIN");
		labelAdminLogin.setFont(new Font("굴림", Font.BOLD, 28));
		labelAdminLogin.setBounds(115, 26, 213, 72);
		contentPane.add(labelAdminLogin);
		
		JLabel labelPassword = new JLabel("PASSWORD: ");
		labelPassword.setFont(new Font("굴림", Font.PLAIN, 20));
		labelPassword.setBounds(80, 101, 129, 30);
		contentPane.add(labelPassword);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(221, 101, 165, 30);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("굴림", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼 눌러짐");
				System.out.println(tfPassword.getText());
				if(tfPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				} else if(!roomMan.matchMangerID(tfPassword.getText())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다");
				}
				else {
					JOptionPane.showMessageDialog(null, "로그인 되었습니다");
					AdminMenuGUI adminMenu = new AdminMenuGUI();
					adminMenu.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnLogin.setBounds(164, 156, 116, 35);
		contentPane.add(btnLogin);
		
		JButton btnGB = new JButton("GO BACK TO MAIN");
		btnGB.setFont(new Font("굴림", Font.BOLD, 12));
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
