import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class PassengerLogin extends JFrame {

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
					PassengerLogin frame = new PassengerLogin();
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
	public PassengerLogin() {
		con=mysqlConnection.dbConnector();
		setTitle("Passenger Login");
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
					String checker="SELECT * FROM passenger WHERE userid=? AND pwd=?;";
					PreparedStatement pst=con.prepareStatement(checker);
					String strPass=String.valueOf(password.getPassword());
					pst.setString(1, username.getText());
					pst.setString(2, strPass);
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "Username and password is correct!");
						String login="UPDATE passenger SET login=1 WHERE userid=?";
						PreparedStatement pst2=con.prepareStatement(login);
						pst2.setString(1, username.getText());
						pst2.execute();
						dispose();
						PassengerPanel passpanel=new PassengerPanel();
						passpanel.setVisible(true);
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
		btnLogin.setBounds(281, 249, 153, 42);
		contentPane.add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(184, 153, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(184, 202, 68, 14);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 13));
		username.setBounds(281, 148, 120, 25);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 13));
		password.setBounds(281, 197, 120, 25);
		contentPane.add(password);
		
		JButton btnRegister = new JButton("Register");
		Image img4= new ImageIcon(this.getClass().getResource("/register.png")).getImage();
		btnRegister.setIcon(new ImageIcon(img4));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PassengerReg passreg=new PassengerReg();
				passreg.setVisible(true);
			}
		});
		btnRegister.setBounds(281, 333, 153, 42);
		contentPane.add(btnRegister);
		
		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewUser.setBounds(184, 347, 65, 14);
		contentPane.add(lblNewUser);
		
		JButton button = new JButton("Back");
		Image img3= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img3));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(509, 11, 153, 42);
		contentPane.add(button);
		
		JLabel lblPassengerLogin = new JLabel("Passenger Login");
		lblPassengerLogin.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblPassengerLogin.setBounds(255, 26, 173, 30);
		contentPane.add(lblPassengerLogin);
		
		JLabel label = new JLabel("");
		Image img1= new ImageIcon(this.getClass().getResource("/passengerbig.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(184, 11, 64, 64);
		contentPane.add(label);
	}
	
}
