import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import Button1Listener.AddButtonActionListener;
//import Button1Listener.SaveButtonActionListener;

public class CheckInGUI extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfRoomNo;
	private JTextField tfCapacity;
	private JTextField tfBPH;
	private JTextField tfBPD;
	private JTextField tfSearch;
	
	//�߰�
	private JPanel panel;
	private String roomNum;
	private int capacity,billPerHour,billPerDay;
	Object arr[]=new Object[6];
	private JTable table_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CheckInGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckInGUI() {
		setVisible(true);
		
		setTitle("CHCEK-IN");
		// ���� try-catch
		//���� ����
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// �����ο� �� �˻� ���̺�, �ʵ�
		JLabel lblNewLabel = new JLabel("Capacity.");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setBounds(421, 49, 64, 15);
		contentPane.add(lblNewLabel);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(483, 43, 64, 29);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		// �����ο� �� �˻� ��ư
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("����", Font.BOLD, 14));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfSearch.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"�ο����� �Է����ּ���.","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int capacity = Integer.parseInt(tfSearch.getText());
						int roomSize = capacity;
						ArrayList<Room> Room = roomMan.searchEmptyRoom(roomSize);
						System.out.println("Available Rooms are");
						System.out.println("list\tRoomNum\tCapacity\tBillPerHour\tBillPerDay");
						for (int i = 0; i < Room.size(); i++) {
							Room room = Room.get(i);
							if (room == null)
								break;
							else {
								System.out.println((i + 1) + "|\t" + room.getRoomNum() + "\t"
										+ room.getCapacity() + "\t\t" + room.getBillPerHour()+ "\t\t" + room.getBillPerDay() + "\t\t" + room.isRoomUsed()+ "\t\t" + room.getUser());
								DefaultTableModel model=(DefaultTableModel) table.getModel();
								//model.setNumRows(0); //�ʱ�ȭ
								arr[0]=Integer.toString(table.getRowCount()+1);
								arr[1]=room.getRoomNum();
								arr[2]=room.getCapacity();
								arr[3]=room.getBillPerHour();
								arr[4]=room.getBillPerDay();
								arr[5]=room.isRoomUsed();
								model.addRow(arr);
								//clearField();
			
							}
						}
					}  catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnSearch.setBounds(550, 42, 106, 30);
		contentPane.add(btnSearch);
		
		
		// ��� �� ���� ��ư
		JButton btnVAR = new JButton("VIEW ALL ROOMS");
		btnVAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Room> Room = roomMan.searchRoom();
					for (int i = 0; i < Room.size(); i++) {
						Room room = Room.get(i);
						if (room == null)
							break;
						else {
							System.out.println(
									(i + 1) + "|\t" + room.getRoomNum() + "\t" + room.getCapacity()
											+ "\t\t" + room.getBillPerHour()+ "\t\t" + room.getBillPerDay() + "\t\t" + room.isRoomUsed()+ "\t\t" + room.getUser());
							
							DefaultTableModel model=(DefaultTableModel) table.getModel();
							//model.setNumRows(0); //�ʱ�ȭ
							arr[0]=Integer.toString(table.getRowCount()+1);
							arr[1]=room.getRoomNum();
							arr[2]=room.getCapacity();
							arr[3]=room.getBillPerHour();
							arr[4]=room.getBillPerDay();
							arr[5]=room.isRoomUsed();
							model.addRow(arr);
							//clearField();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVAR.setFont(new Font("����", Font.BOLD, 14));
		btnVAR.setBounds(25, 42, 183, 25);
		contentPane.add(btnVAR);
				
		// ���̺� ���� ��ư
		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("����", Font.BOLD, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
			}
		});
		btnReset.setBounds(210, 41, 94, 26);
		contentPane.add(btnReset);
				
		// ���̺�
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 76, 631, 199);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"INDEX", "ROOM NO", "CAPACITY", "BILL PER HOUR", "BILL PER DAY", "USING"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Integer.class, Integer.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		
		// üũ�� ��ư
		JButton btnNewButton = new JButton("CHECK-IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowNum = table.getSelectedRow();
				//��ϵ� ���� ������ ���þ��ϰ� ������ ���â, ��ϵ� ������ ������ ���������� ���â
				if(rowNum == -1&&!(table.getRowCount()==0)){ 
					JOptionPane.showMessageDialog(null,"���� �������ּ���","Warning",JOptionPane.WARNING_MESSAGE);
				}else if(table.getRowCount()==0){
					JOptionPane.showMessageDialog(null,"���� �������ּ���","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					// ���̺��� ���� üũ�� ����
					int row = table.getSelectedRow();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					int roomNum = (int) model.getValueAt(row, 1);
					
					JFrame frame = new JFrame("CHECK-IN");				//frame ����
					frame.setPreferredSize(new Dimension(350, 200));	//frame ũ�� ����
					frame.setLocation(600, 300);						//frame ��ġ ����
					
					Container contentPane = frame.getContentPane();
					
					JPanel panel1 = new JPanel();					//�г�1 ����
					panel1.setLayout(new GridLayout(5, 2));			//�г�1 ���̾ƿ� ����	
					JTextField tNum = new JTextField();
					JTextField tPhoneNum = new JTextField();
					panel1.add(new JLabel("phoneNo"));
					panel1.add(tPhoneNum);
					contentPane.add(panel1, BorderLayout.NORTH);	//�г�1�� content pane NORTH ��ġ�� �߰�
					
					JPanel panel2 = new JPanel();					//�г�2 ����
					panel2.setLayout(new GridLayout(1, 2));			//�г�2 ���̾ƿ� ����
					JButton btnCheckInButton = new JButton("CHECK-IN");
					panel2.add(btnCheckInButton);
					contentPane.add(panel2, BorderLayout.SOUTH);	//�г�2�� content pane SOUTH ��ġ�� �߰�
				
					JLabel message = new JLabel(); 					//�˸� ����
					contentPane.add(message, BorderLayout.CENTER);	//�˸������� content pane CENTER ��ġ�� �߰�
					
					
					btnCheckInButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("üũ�� ��ư ������");
							String phoneNo = tPhoneNum.getText();
							User user = new User(phoneNo);
							if(phoneNo.equals("")) {
								JOptionPane.showMessageDialog(null,"��ȣ�� �Է����ּ���!.","Warning",JOptionPane.WARNING_MESSAGE);
							} else {
								try {
									roomMan.checkIn(roomNum, user);
									JOptionPane.showMessageDialog(null,user +"��" + roomNum + "�� �濡 üũ�� �Ǿ����ϴ�");
									ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
									DataOutputStream output2 = new DataOutputStream(new FileOutputStream(file2));
									DataOutputStream output3 = new DataOutputStream(new FileOutputStream(file3));
									roomMan.writeRoomInfos(output,output2,output3);
									output.close();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									return;
								}
							}		
						
					}
				});
				
				frame.pack();
				frame.setVisible(true);
			}
			}});
		btnNewButton.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton.setBounds(249, 292, 139, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("GO BACK TO MENU");
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMenu userMenu = new UserMenu();
				userMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(485, 322, 171, 31);
		contentPane.add(btnNewButton_1);
	}
	
	private void clearField() {
		//�ؽ�Ʈ���� ����
		tfRoomNo.setText("");
		tfCapacity.setText("");
		tfBPH.setText("");
		tfBPD.setText("");	
	}
	
	
	
	

}
