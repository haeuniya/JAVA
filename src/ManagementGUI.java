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

// �߰�
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
	
	//�߰�
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
		
		setTitle("ROOM MANAGEMENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// ������ ���̺�
		JLabel labelRoomNo = new JLabel("ROOM NO.");
		labelRoomNo.setFont(new Font("����", Font.PLAIN, 15));
		labelRoomNo.setBounds(24, 22, 115, 15);
		contentPane.add(labelRoomNo);
		
		tfRoomNo = new JTextField();
		tfRoomNo.setBounds(147, 22, 119, 21);
		contentPane.add(tfRoomNo);
		tfRoomNo.setColumns(10);
		
		JLabel labelCapacity = new JLabel("CAPACITY");
		labelCapacity.setFont(new Font("����", Font.PLAIN, 15));
		labelCapacity.setBounds(24, 50, 115, 15);
		contentPane.add(labelCapacity);
		
		tfCapacity = new JTextField();
		tfCapacity.setColumns(10);
		tfCapacity.setBounds(147, 50, 119, 21);
		contentPane.add(tfCapacity);
		
		JLabel labelBPH = new JLabel("BILL PER HOUR");
		labelBPH.setFont(new Font("����", Font.PLAIN, 15));
		labelBPH.setBounds(24, 81, 115, 15);
		contentPane.add(labelBPH);
		
		tfBPH = new JTextField();
		tfBPH.setColumns(10);
		tfBPH.setBounds(147, 81, 119, 21);
		contentPane.add(tfBPH);
		
		JLabel labelBHD = new JLabel("BILL PER DAY");
		labelBHD.setFont(new Font("����", Font.PLAIN, 15));
		labelBHD.setBounds(24, 111, 115, 15);
		contentPane.add(labelBHD);
		
		tfBPD = new JTextField();
		tfBPD.setColumns(10);
		tfBPD.setBounds(147, 111, 119, 21);
		contentPane.add(tfBPD);
	
		
		// ���̺� ���� ��ư
		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("����", Font.BOLD, 11));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
			}
		});
		btnReset.setBounds(548, 169, 90, 23);
		contentPane.add(btnReset);
		
		
		// ���̺�
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
		
		// ��ư
		// �� �߰� ��ư
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(tfRoomNo.getText().equals("")||tfCapacity.getText().equals("")||tfBPH.getText().equals("")||tfBPD.getText().equals("")) {
					if(tfRoomNo.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"���ȣ�� �Է����ּ���!.","Warning",JOptionPane.WARNING_MESSAGE);
					} else if (tfCapacity.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"���ο��� �Է����ּ���!.","Warning",JOptionPane.WARNING_MESSAGE);
					} else if (tfBPH.getText().equals("")||tfBPD.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"����� �Է����ּ���!.","Warning",JOptionPane.WARNING_MESSAGE);
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
		btnAdd.setFont(new Font("����", Font.BOLD, 14));
		btnAdd.setBounds(297, 33, 161, 30);
		contentPane.add(btnAdd);
		
		// �� ��Ȳ ���� ��ư
		JButton btnEdit = new JButton("STATUS");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomStatus roomStatus = new RoomStatus();
				roomStatus.setVisible(true);
				frame.dispose();
			}
		});
		btnEdit.setFont(new Font("����", Font.BOLD, 14));
		btnEdit.setBounds(297, 92, 161, 30);
		contentPane.add(btnEdit);
		
		// ��� �� ���� ��ư
		JButton btnVAR = new JButton("VIEW ALL ROOMS");
		btnVAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					//model.setNumRows(0); //�ʱ�ȭ
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
		btnVAR.setFont(new Font("����", Font.BOLD, 14));
		btnVAR.setBounds(488, 92, 162, 30);
		contentPane.add(btnVAR);
		
		
		// �� ���� ��ư
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setFont(new Font("����", Font.BOLD, 14));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���콺�� ������ �� ��ȣ
				int row = table.getSelectedRow();
				//��ϵ� ���� ������ ���þ��ϰ� ������ ���â, ��ϵ� ������ ������ ���������� ���â
				if(row == -1&&!(table.getRowCount()==0)){ 
					JOptionPane.showMessageDialog(null,"������ ���� �������ּ���","Warning",JOptionPane.WARNING_MESSAGE);
				}else if(table.getRowCount()==0){
					JOptionPane.showMessageDialog(null,"���õ� ���� �����ϴ�. ���� �������ּ���","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						//�����ϰ� ������ ����
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						System.out.println("����10");
						System.out.println("row: " + row);
						int value = (int) model.getValueAt(row, 1);
						roomMan.removeRoom(value);
						JOptionPane.showMessageDialog(null, "�� " + value + "�� �����Ǿ����ϴ�");
						
						model.removeRow(row);
						
						System.out.println("��: " + value);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnRemove.setBounds(488, 32, 162, 31);
		contentPane.add(btnRemove);
		
		// �ϴ� ��ư - �޴��� ���� ����
		JButton btnGBTM = new JButton("GO BACK");
		btnGBTM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenuGUI adminMenu = new AdminMenuGUI();
				adminMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnGBTM.setFont(new Font("����", Font.BOLD, 14));
		btnGBTM.setBounds(529, 372, 106, 31);
		contentPane.add(btnGBTM);
		
		// �ϴ� ��ư - �����ϱ� ��ư
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
		btnSave.setFont(new Font("����", Font.BOLD, 14));
		btnSave.setBounds(421, 372, 96, 31);
		contentPane.add(btnSave);	
	}
	
	private void clearField() {
		//�ؽ�Ʈ���� ����
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

