import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class RoomStatus extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private int roomNum,capacity,billPerHour,billPerDay;
	Object arr[]=new Object[7];
	private JTable table_1;
	private JTable table;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new RoomStatus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RoomStatus() {
		setVisible(true);
		
		setTitle("ROON STATUS & OCCUPACY");
		//���� �б����� 
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
		setBounds(100, 100, 730, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 89, 694, 221);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"INDEX", "ROOM NO", "CAPACITY", "BILL PER HOUR", "BILL PER DAY","USING","USER"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Integer.class, Integer.class, Integer.class,Object.class,Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnGB = new JButton("GO BACK");
		btnGB.setFont(new Font("����", Font.BOLD, 12));
		btnGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagementGUI manage = new ManagementGUI();
				manage.setVisible(true);
				setVisible(false);
			}
		});
		btnGB.setBounds(601, 320, 105, 33);
		contentPane.add(btnGB);
		
		lblNewLabel = new JLabel("ROOM STATUS & OQUPACY");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setBounds(177, 31, 367, 33);
		contentPane.add(lblNewLabel);
		
		
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
					arr[6]=room.getUser();
					model.addRow(arr);
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
