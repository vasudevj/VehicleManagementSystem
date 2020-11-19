import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class ManageBus extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBus frame = new ManageBus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	private JTextField bnum;
	private JTextField time;
	private JTextField tot;
	private JTextField rid;
	private JTextField day;
	
	private void clearFields()
	{
		bnum.setText(null);
		time.setText(null);
		tot.setText(null);
		rid.setText(null);
	}
	public void refresh()
	{
		try{
			String viewer="SELECT * FROM bus;";
			PreparedStatement pst=con.prepareStatement(viewer);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public ManageBus() {
		con=mysqlConnection.dbConnector();
		setTitle("Manage Buses");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 64, 633, 213);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnViewDetails = new JButton("View Details");
		Image img2= new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		btnViewDetails.setIcon(new ImageIcon(img2));
		btnViewDetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnViewDetails.setBounds(28, 11, 153, 42);
		contentPane.add(btnViewDetails);
		
		JLabel lblBusNumber = new JLabel("Bus Number");
		lblBusNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBusNumber.setBounds(38, 288, 89, 14);
		contentPane.add(lblBusNumber);
		
		JLabel lblNewLabel = new JLabel("Time");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(132, 288, 48, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total Seats");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(200, 288, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRouteId = new JLabel("Route ID");
		lblRouteId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRouteId.setBounds(277, 288, 67, 14);
		contentPane.add(lblRouteId);
		
		bnum = new JTextField();
		bnum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bnum.setBounds(38, 306, 75, 25);
		contentPane.add(bnum);
		bnum.setColumns(10);
		
		time = new JTextField();
		time.setFont(new Font("Tahoma", Font.PLAIN, 13));
		time.setBounds(132, 306, 50, 25);
		contentPane.add(time);
		time.setColumns(10);
		
		tot = new JTextField();
		tot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tot.setBounds(200, 306, 35, 25);
		contentPane.add(tot);
		tot.setColumns(10);
		
		rid = new JTextField();
		rid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rid.setBounds(277, 306, 50, 25);
		contentPane.add(rid);
		rid.setColumns(10);
		
		JButton btnAddNewBus = new JButton("Add New Bus");
		Image img3= new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAddNewBus.setIcon(new ImageIcon(img3));
		btnAddNewBus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddNewBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String adder="INSERT INTO bus VALUES(?,?,0,?,?,?);";
					PreparedStatement pst=con.prepareStatement(adder);
					pst.setString(1, bnum.getText());
					pst.setString(2, time.getText());
					pst.setString(3, tot.getText());
					pst.setString(4, rid.getText());
					pst.setString(5, day.getText());
					pst.execute();
					pst.close();
					clearFields();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				refresh();
			}
		});
		btnAddNewBus.setBounds(319, 347, 153, 42);
		contentPane.add(btnAddNewBus);
		
		JButton btnDeleteBus = new JButton("Delete Bus");
		Image img4= new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		btnDeleteBus.setIcon(new ImageIcon(img4));
		btnDeleteBus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String deleter="DELETE FROM bus WHERE b_num=?;";
					PreparedStatement pst=con.prepareStatement(deleter);
					pst.setString(1, bnum.getText());
					pst.execute();
					pst.close();
					clearFields();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				refresh();
			}
		});
		btnDeleteBus.setBounds(508, 347, 153, 42);
		contentPane.add(btnDeleteBus);
		
		JButton button = new JButton("Back");
		Image img1= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminPanel admpanel=new AdminPanel();
				admpanel.setVisible(true);
			}
		});
		button.setBounds(508, 11, 153, 42);
		contentPane.add(button);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDay.setBounds(362, 288, 48, 14);
		contentPane.add(lblDay);
		
		day = new JTextField();
		day.setFont(new Font("Tahoma", Font.PLAIN, 13));
		day.setBounds(362, 306, 120, 25);
		contentPane.add(day);
		day.setColumns(10);
		
		JLabel lblManageBuses = new JLabel("Manage Buses");
		lblManageBuses.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblManageBuses.setBounds(265, 13, 153, 30);
		contentPane.add(lblManageBuses);
	}
}
