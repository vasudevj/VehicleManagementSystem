import java.awt.BorderLayout;

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ManageEmp extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageEmp frame = new ManageEmp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con=null;
	
	/**
	 * Create the frame.
	 */
	public ManageEmp() {
		con=mysqlConnection.dbConnector();
		setTitle("Manage Employees");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEditEmp = new JButton("Add/Delete Employee");
		Image img2= new ImageIcon(this.getClass().getResource("/addemp.png")).getImage();
		btnEditEmp.setIcon(new ImageIcon(img2));
		btnEditEmp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditEmp edit=new EditEmp();
				edit.setVisible(true);
			}
		});
		btnEditEmp.setBounds(388, 358, 275, 42);
		contentPane.add(btnEditEmp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 76, 640, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnViewDetails = new JButton("View Details");
		Image img3= new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		btnViewDetails.setIcon(new ImageIcon(img3));
		btnViewDetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String viewer="SELECT e_id, e_name, pos, contact, b_num FROM employee;";
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
		});
		btnViewDetails.setBounds(23, 358, 153, 42);
		contentPane.add(btnViewDetails);
		
		JButton button = new JButton("Back");
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Image img1= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminPanel admpanel=new AdminPanel();
				admpanel.setVisible(true);
			}
		});
		button.setBounds(510, 11, 153, 42);
		contentPane.add(button);
		
		JLabel lblManageEmployee = new JLabel("Manage Employees");
		lblManageEmployee.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblManageEmployee.setBounds(23, 11, 243, 30);
		contentPane.add(lblManageEmployee);
	}
}
