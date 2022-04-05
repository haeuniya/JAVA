import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class CheckIncomeGUI extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfDailyMonth;
	private JTextField tfDailyDay;
	private JTextField tfMonthlyMonth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CheckIncomeGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckIncomeGUI() {
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
		setTitle("CHECK-INCOME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("DAILY INCOME:");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 57, 166, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblMonthlyIncome = new JLabel("MONTHLY INCOME:");
		lblMonthlyIncome.setFont(new Font("굴림", Font.BOLD, 20));
		lblMonthlyIncome.setBounds(40, 111, 198, 35);
		contentPane.add(lblMonthlyIncome);
		
		JLabel lblNewLabel_1_1 = new JLabel("TOTAL INCOME:");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(40, 168, 166, 35);
		contentPane.add(lblNewLabel_1_1);
		
		// 메뉴로 되돌아가기 버튼
		JButton btnGBTM = new JButton("GO BACK TO MENU");
		btnGBTM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenuGUI adminMenu = new AdminMenuGUI();
				adminMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnGBTM.setFont(new Font("굴림", Font.BOLD, 12));
		btnGBTM.setBounds(336, 236, 150, 23);
		contentPane.add(btnGBTM);
		
		// 일별수입 - 달 기입 텍스트필드
		tfDailyMonth = new JTextField();
		tfDailyMonth.setBounds(258, 66, 49, 21);
		contentPane.add(tfDailyMonth);
		tfDailyMonth.setColumns(10);
		
		// 일별수입 - 일 기입 텍스트필드
		tfDailyDay = new JTextField();
		tfDailyDay.setColumns(10);
		tfDailyDay.setBounds(319, 66, 49, 21);
		contentPane.add(tfDailyDay);
		
		// 달별수입 - 달 기입 텍스트필드
		tfMonthlyMonth = new JTextField();
		tfMonthlyMonth.setColumns(10);
		tfMonthlyMonth.setBounds(258, 120, 49, 21);
		contentPane.add(tfMonthlyMonth);
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setBounds(258, 46, 52, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Day");
		lblNewLabel_1_2.setBounds(319, 46, 52, 15);
		contentPane.add(lblNewLabel_1_2);
		
		// 일별 수입 보기 버튼
		JButton btnDailyIncome = new JButton("VIEW");
		btnDailyIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfDailyMonth.getText().equals("")||tfDailyDay.getText().equals("")){
					JOptionPane.showMessageDialog(null,"날짜를 입력해주세요!.","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					int month = Integer.parseInt(tfDailyMonth.getText());
					int date = Integer.parseInt(tfDailyDay.getText());
					int dailyIncome = roomMan.getDailyIncome(month, date);
					JOptionPane.showMessageDialog(null, "[ " + month + "월 " + date + "일 매출 : " + dailyIncome + "원 ]");	
				}
			}
		});
		
		btnDailyIncome.setBounds(380, 65, 95, 23);
		contentPane.add(btnDailyIncome);
		
		// 달별 수입 보기 버튼
		JButton btnMonthlyIncome = new JButton("VIEW");
		btnMonthlyIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfMonthlyMonth.getText().equals("")){
					JOptionPane.showMessageDialog(null,"달을 입력해주세요!.","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					int month = Integer.parseInt(tfMonthlyMonth.getText());
					int monthIncome = roomMan.getMonthIncome(month);
					JOptionPane.showMessageDialog(null, "[ " + month + "월 매출 : " + monthIncome + "원 ]");

				}
				
			}
		});
		btnMonthlyIncome.setBounds(380, 119, 95, 23);
		contentPane.add(btnMonthlyIncome);
		
		JButton btnTotal = new JButton("VIEW");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int totalIncome = roomMan.total_income;
				//System.out.println(totalIncome);
				JOptionPane.showMessageDialog(null,"총 수입은: " + roomMan.total_income + "입니다.","TOTAL INCOME",JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		btnTotal.setBounds(240, 176, 95, 23);
		contentPane.add(btnTotal);


	}
}
