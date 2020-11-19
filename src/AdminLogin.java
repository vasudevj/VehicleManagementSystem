import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		con=mysqlConnection.dbConnector();
		setTitle("Admin Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblAdminLogin.setBounds(269, 22, 145, 42);
		contentPane.add(lblAdminLogin);
		
		JButton btnLogin = new JButton("Login");
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String checker="SELECT * FROM admin WHERE userid=? AND pwd=?;";
					PreparedStatement pst=con.prepareStatement(checker);
					pst.setString(1, username.getText());
					String strPass=String.valueOf(password.getPassword());
					pst.setString(2, strPass);
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "Username and password is correct!");
						dispose();
						AdminPanel admpanel=new AdminPanel();
						admpanel.setVisible(true);
					}
					else if(count>1)
						JOptionPane.showMessageDialog(null, "Duplicate username and password!");
					else
						JOptionPane.showMessageDialog(null, "Username or password incorrect!");
					pst.close();
					rs.close();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnLogin.setBounds(285, 291, 153, 42);
		Image img3= new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img3));
		contentPane.add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(188, 203, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(188, 246, 68, 14);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(285, 198, 120, 25);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(285, 241, 120, 25);
		contentPane.add(password);
		
		JButton btnBack = new JButton("Back");
		Image img1= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btnBack.setIcon(new ImageIcon(img1));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(521, 11, 153, 42);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		Image img2= new ImageIcon(this.getClass().getResource("/adminbig.png")).getImage();
		label.setIcon(new ImageIcon(img2));
		label.setBounds(188, 11, 64, 64);
		contentPane.add(label);
	}
}
