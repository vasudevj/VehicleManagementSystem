import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class EditEmp extends JFrame {

	private JPanel contentPane;
	private JTextField eid;
	private JTextField name;
	private JTextField pos;
	private JTextField contact;
	private JTextField password;
	private JTextField bnum;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmp frame = new EditEmp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	
	private void clearFields()
	{
		eid.setText(null);
		name.setText(null);
		pos.setText(null);
		contact.setText(null);
		password.setText(null);
		bnum.setText(null);
	}
	
	public void refresh()
	{
		try{
			String viewer="SELECT * FROM employee;";
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
	public EditEmp() {
		con=mysqlConnection.dbConnector();
		setTitle("Edit Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmployeeId.setBounds(10, 62, 92, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(10, 99, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPosition.setBounds(10, 133, 48, 14);
		contentPane.add(lblPosition);
		
		JLabel lblAssignedPassword = new JLabel("Assigned Password");
		lblAssignedPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssignedPassword.setBounds(10, 212, 112, 20);
		contentPane.add(lblAssignedPassword);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact.setBounds(10, 170, 73, 14);
		contentPane.add(lblContact);
		
		JLabel lblBusNumber = new JLabel("Bus Number");
		lblBusNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBusNumber.setBounds(10, 255, 92, 14);
		contentPane.add(lblBusNumber);
		
		eid = new JTextField();
		eid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		eid.setBounds(131, 60, 75, 25);
		contentPane.add(eid);
		eid.setColumns(10);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		name.setBounds(131, 97, 120, 25);
		contentPane.add(name);
		name.setColumns(10);
		
		pos = new JTextField();
		pos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pos.setBounds(131, 131, 120, 25);
		contentPane.add(pos);
		pos.setColumns(10);
		
		contact = new JTextField();
		contact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contact.setBounds(131, 168, 120, 25);
		contentPane.add(contact);
		contact.setColumns(10);
		
		password = new JTextField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 13));
		password.setBounds(131, 210, 120, 25);
		contentPane.add(password);
		password.setColumns(10);
		
		bnum = new JTextField();
		bnum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bnum.setBounds(131, 250, 75, 25);
		contentPane.add(bnum);
		bnum.setColumns(10);
		
		JButton btnAddNewEmployee = new JButton("Add New Employee");
		Image img2= new ImageIcon(this.getClass().getResource("/addemp.png")).getImage();
		btnAddNewEmployee.setIcon(new ImageIcon(img2));
		btnAddNewEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));

		btnAddNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String adder="INSERT INTO employee VALUES(?,?,?,?,?,?);";
					PreparedStatement pst=con.prepareStatement(adder);
					pst.setString(1, eid.getText());
					pst.setString(2, name.getText());
					pst.setString(3, pos.getText());
					pst.setString(4, contact.getText());
					pst.setString(5, password.getText());
					pst.setString(6, bnum.getText());
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
		btnAddNewEmployee.setBounds(10, 302, 200, 42);
		contentPane.add(btnAddNewEmployee);
		
		JButton btnDelete = new JButton("Delete Employee");
		Image img3= new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
		btnDelete.setIcon(new ImageIcon(img3));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String deleter="DELETE FROM employee WHERE e_id=?;";
					PreparedStatement pst=con.prepareStatement(deleter);
					pst.setString(1, eid.getText());
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
		btnDelete.setBounds(10, 365, 200, 42);
		contentPane.add(btnDelete);
		
		JLabel lblAdddeleteEmployee = new JLabel("Add/Delete Employee");
		lblAdddeleteEmployee.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblAdddeleteEmployee.setBounds(200, 12, 243, 30);
		contentPane.add(lblAdddeleteEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(297, 61, 360, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Back");
		Image img1= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManageEmp mngemp=new ManageEmp();
				mngemp.setVisible(true);
			}
		});
		button.setBounds(504, 11, 153, 42);
		contentPane.add(button);
	}
}
