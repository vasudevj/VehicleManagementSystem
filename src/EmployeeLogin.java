import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class EmployeeLogin extends JFrame {

	private JPanel contentPane;
	private JTextField Empid;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	public EmployeeLogin() {
		con=mysqlConnection.dbConnector();
		setTitle("Employee Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		Image img2= new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img2));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String checker="SELECT * FROM employee WHERE e_id=? AND pwd=?;";
					PreparedStatement pst=con.prepareStatement(checker);
					pst.setString(1, Empid.getText());
					String strPass=String.valueOf(password.getPassword());
					pst.setString(2, strPass);
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "Employee ID and password is correct!");
						dispose();
						EmployeePanel emppanel=new EmployeePanel();
						emppanel.setVisible(true);
					}
					else if(count>1)
						JOptionPane.showMessageDialog(null, "Duplicate Employee ID and password!");
					else
						JOptionPane.showMessageDialog(null, "Employee ID or password incorrect!");
					pst.close();
					rs.close();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnLogin.setBounds(284, 262, 153, 42);
		contentPane.add(btnLogin);
		
		JLabel lblEmpid = new JLabel("Employee ID");
		lblEmpid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmpid.setBounds(167, 169, 79, 14);
		contentPane.add(lblEmpid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(167, 210, 68, 14);
		contentPane.add(lblPassword);
		
		Empid = new JTextField();
		Empid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Empid.setBounds(284, 164, 75, 25);
		contentPane.add(Empid);
		Empid.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 13));
		password.setBounds(284, 205, 120, 25);
		contentPane.add(password);
		
		JButton button = new JButton("Back");
		Image img3= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img3));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(521, 9, 153, 42);
		contentPane.add(button);
		
		JLabel lblEmployeeLogin = new JLabel("Employee Login");
		lblEmployeeLogin.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblEmployeeLogin.setBounds(256, 11, 171, 30);
		contentPane.add(lblEmployeeLogin);
		
		JLabel label = new JLabel("");
		Image img1= new ImageIcon(this.getClass().getResource("/employeebig.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(182, 6, 64, 64);
		contentPane.add(label);
	}
	
}
