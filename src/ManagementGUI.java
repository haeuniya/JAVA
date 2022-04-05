import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import java.awt.Font;

// 추가
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;

public class ManagementGUI extends JFrame {
	
	private Button ExitButton = new Button("button1");
	
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField tfRoomNo;
	private JTextField tfCapacity;
	private JTextField tfBPH;
	private JTextField tfBPD;
	private JTextField tfSearch;
	
	//추가
	private JPanel panel;
	private int roomNum,capacity,billPerHour,billPerDay;
	Object arr[]=new Object[5];
	private JTable table_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ManagementGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagementGUI() {

		setVisible(true);
		
		//파일 생성
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
		
		setTitle("ROOM MANAGEMENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// 방정보 레이블
		JLabel labelRoomNo = new JLabel("ROOM NO.");
		labelRoomNo.setFont(new Font("굴림", Font.PLAIN, 15));
		labelRoomNo.setBounds(24, 22, 115, 15);
		contentPane.add(labelRoomNo);
		
		tfRoomNo = new JTextField();
		tfRoomNo.setBounds(147, 22, 119, 21);
		contentPane.add(tfRoomNo);
		tfRoomNo.setColumns(10);
		
		JLabel labelCapacity = new JLabel("CAPACITY");
		labelCapacity.setFont(new Font("굴림", Font.PLAIN, 15));
		labelCapacity.setBounds(24, 50, 115, 15);
		contentPane.add(labelCapacity);
		
		tfCapacity = new JTextField();
		tfCapacity.setColumns(10);
		tfCapacity.setBounds(147, 50, 119, 21);
		contentPane.add(tfCapacity);
		
		JLabel labelBPH = new JLabel("BILL PER HOUR");
		labelBPH.setFont(new Font("굴림", Font.PLAIN, 15));
		labelBPH.setBounds(24, 81, 115, 15);
		contentPane.add(labelBPH);
		
		tfBPH = new JTextField();
		tfBPH.setColumns(10);
		tfBPH.setBounds(147, 81, 119, 21);
		contentPane.add(tfBPH);
		
		JLabel labelBHD = new JLabel("BILL PER DAY");
		labelBHD.setFont(new Font("굴림", Font.PLAIN, 15));
		labelBHD.setBounds(24, 111, 115, 15);
		contentPane.add(labelBHD);
		
		tfBPD = new JTextField();
		tfBPD.setColumns(10);
		tfBPD.setBounds(147, 111, 119, 21);
		contentPane.add(tfBPD);
	
		
		// 테이블 리셋 버튼
		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("굴림", Font.BOLD, 11));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
			}
		});
		btnReset.setBounds(548, 169, 90, 23);
		contentPane.add(btnReset);
		
		
		// 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 192, 617, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"INDEX", "ROOM NO", "CAPACITY", "BILL PER HOUR", "BILL PER DAY"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		// 버튼
		// 방 추가 버튼
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(tfRoomNo.getText().equals("")||tfCapacity.getText().equals("")||tfBPH.getText().equals("")||tfBPD.getText().equals("")) {
					if(tfRoomNo.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"방번호를 입력해주세요!.","Warning",JOptionPane.WARNING_MESSAGE);
					} else if (tfCapacity.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"방인원을 입력해주세요!.","Warning",JOptionPane.WARNING_MESSAGE);
					} else if (tfBPH.getText().equals("")||tfBPD.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"요금을 입력해주세요!.","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					roomNum = Integer.parseInt(tfRoomNo.getText());
					capacity = Integer.parseInt(tfCapacity.getText());
					billPerHour = Integer.parseInt(tfBPH.getText());
					billPerDay = Integer.parseInt(tfBPD.getText());
					
					arr[0]=Integer.toString(table.getRowCount()+1);
					arr[1]=roomNum;
					arr[2]=capacity;
					arr[3]=billPerHour;
					arr[4]=billPerDay;
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					//model.addRow(new Object [] {tfRoomNo.getText(), Integer.parseInt(tfCapacity.getText()),Integer.parseInt(tfBPH.getText()),Integer.parseInt(tfBPD.getText())});
					model.addRow(arr);
					Room tmpRoom = new Room(roomNum, capacity, billPerHour,billPerDay);
					roomMan.makeRoom(tmpRoom);
				}
			}
		});
		btnAdd.setFont(new Font("굴림", Font.BOLD, 14));
		btnAdd.setBounds(297, 33, 161, 30);
		contentPane.add(btnAdd);
		
		// 방 현황 보기 버튼
		JButton btnEdit = new JButton("STATUS");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomStatus roomStatus = new RoomStatus();
				roomStatus.setVisible(true);
				frame.dispose();
			}
		});
		btnEdit.setFont(new Font("굴림", Font.BOLD, 14));
		btnEdit.setBounds(297, 92, 161, 30);
		contentPane.add(btnEdit);
		
		// 모든 방 보기 버튼
		JButton btnVAR = new JButton("VIEW ALL ROOMS");
		btnVAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					//model.setNumRows(0); //초기화
					ArrayList<Room> Room = roomMan.searchRoom();
					for (int i = 0; i < Room.size(); i++) {
						Room room = Room.get(i);
						if (room == null)
							break;
						else {
							System.out.println(
									(i + 1) + "|\t" + room.getRoomNum() + "\t" + room.getCapacity()
											+ "\t\t" + room.getBillPerHour()+ "\t\t" + room.getBillPerDay() + "\t\t" + room.isRoomUsed()+ "\t\t" + room.getUser());
							
							
							arr[0]=Integer.toString(table.getRowCount()+1);
							arr[1]=room.getRoomNum();
							arr[2]=room.getCapacity();
							arr[3]=room.getBillPerHour();
							arr[4]=room.getBillPerDay();
							model.addRow(arr);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVAR.setFont(new Font("굴림", Font.BOLD, 14));
		btnVAR.setBounds(488, 92, 162, 30);
		contentPane.add(btnVAR);
		
		
		// 방 삭제 버튼
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setFont(new Font("굴림", Font.BOLD, 14));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//마우스로 선택한 줄 번호
				int row = table.getSelectedRow();
				//등록된 정보 있을시 선택안하고 누르면 경고창, 등록된 정보가 없을때 삭제누르면 경고창
				if(row == -1&&!(table.getRowCount()==0)){ 
					JOptionPane.showMessageDialog(null,"삭제할 방을 선택해주세요","Warning",JOptionPane.WARNING_MESSAGE);
				}else if(table.getRowCount()==0){
					JOptionPane.showMessageDialog(null,"선택된 방이 없습니다. 방을 선택해주세요","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//선택하고 누를시 삭제
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						System.out.println("여기10");
						System.out.println("row: " + row);
						int value = (int) model.getValueAt(row, 1);
						roomMan.removeRoom(value);
						JOptionPane.showMessageDialog(null, "방 " + value + "가 삭제되었습니다");
						
						model.removeRow(row);
						
						System.out.println("값: " + value);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnRemove.setBounds(488, 32, 162, 31);
		contentPane.add(btnRemove);
		
		// 하단 버튼 - 메뉴로 돌아 가기
		JButton btnGBTM = new JButton("GO BACK");
		btnGBTM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenuGUI adminMenu = new AdminMenuGUI();
				adminMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnGBTM.setFont(new Font("굴림", Font.BOLD, 14));
		btnGBTM.setBounds(529, 372, 106, 31);
		contentPane.add(btnGBTM);
		
		// 하단 버튼 - 저장하기 버튼
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnSave.setFont(new Font("굴림", Font.BOLD, 14));
		btnSave.setBounds(421, 372, 96, 31);
		contentPane.add(btnSave);	
	}
	
	private void clearField() {
		//텍스트상자 비우기
		tfRoomNo.setText("");
		tfCapacity.setText("");
		tfBPH.setText("");
		tfBPD.setText("");	
	}
	/*
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}*/
}

