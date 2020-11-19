import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PassengerReg extends JFrame {

	private JPanel contentPane;
	private JTextField fname;
	private JTextField uname;
	private JTextField age;
	private JRadioButton male;
	private JRadioButton female;
	private JRadioButton other;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerReg frame = new PassengerReg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	private JTextField contact;
	private JPasswordField pwd;
	private JButton button;
	private JLabel lblRegistrationForm;
	private JLabel label;
	
	/**
	 * Create the frame.
	 */
	public PassengerReg() {
		con=mysqlConnection.dbConnector();
		setTitle("New Passenger Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Fullname");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(122, 86, 61, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(122, 132, 61, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(122, 168, 61, 14);
		contentPane.add(lblPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAge.setBounds(122, 204, 48, 14);
		contentPane.add(lblAge);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSex.setBounds(122, 235, 48, 14);
		contentPane.add(lblSex);
		
		male = new JRadioButton("Male");
		male.setFont(new Font("Tahoma", Font.PLAIN, 13));
		male.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(male.isSelected())
				{
					female.setSelected(false);
					other.setSelected(false);
				}
			}
		});
		male.setBounds(224, 231, 71, 23);
		contentPane.add(male);
		
		female = new JRadioButton("Female");
		female.setFont(new Font("Tahoma", Font.PLAIN, 13));
		female.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(female.isSelected())
				{
					male.setSelected(false);
					other.setSelected(false);
				}
			}
		});
		female.setBounds(319, 231, 80, 23);
		contentPane.add(female);
		
		other = new JRadioButton("Other");
		other.setFont(new Font("Tahoma", Font.PLAIN, 13));
		other.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(other.isSelected())
				{
					female.setSelected(false);
					male.setSelected(false);
				}
			}
		});
		other.setBounds(420, 231, 61, 23);
		contentPane.add(other);
		
		fname = new JTextField();
		fname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fname.setBounds(260, 81, 200, 25);
		contentPane.add(fname);
		fname.setColumns(10);
		
		uname = new JTextField();
		uname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		uname.setBounds(260, 127, 120, 25);
		contentPane.add(uname);
		uname.setColumns(10);
		
		age = new JTextField();
		age.setFont(new Font("Tahoma", Font.PLAIN, 13));
		age.setBounds(260, 199, 35, 25);
		contentPane.add(age);
		age.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		//Image img2= new ImageIcon(this.getClass().getResource("/register.png")).getImage();
		//btnRegister.setIcon(new ImageIcon(img2));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sex=null;
					String adder="INSERT INTO passenger VALUES(?,?,?,?,?,?,0);";
					PreparedStatement pst=con.prepareStatement(adder);
					pst.setString(1, uname.getText());
					pst.setString(2, fname.getText());
					pst.setString(3, age.getText());
					if(male.isSelected())
						sex="Male";
					else if(female.isSelected())
						sex="Female";
					else if(other.isSelected())
						sex="Other";
					pst.setString(4, sex);
					pst.setString(5, contact.getText());
					String pass=String.valueOf(pwd.getPassword());
					pst.setString(6, pass);
					JOptionPane.showMessageDialog(null, "User Registered!");
					pst.execute();
					pst.close();
					dispose();
					PassengerLogin passlogin=new PassengerLogin();
					passlogin.setVisible(true);
					
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				
			}
		});
		btnRegister.setBounds(260, 330, 153, 42);
		contentPane.add(btnRegister);
		
		contact = new JTextField();
		contact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contact.setBounds(258, 261, 120, 25);
		contentPane.add(contact);
		contact.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact.setBounds(122, 266, 48, 14);
		contentPane.add(lblContact);
		
		pwd = new JPasswordField();
		pwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pwd.setBounds(260, 163, 120, 25);
		contentPane.add(pwd);
		
		button = new JButton("Back");
		Image img1= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EmployeeLogin emplogin=new EmployeeLogin();
				emplogin.setVisible(true);
			}
		});
		button.setBounds(509, 11, 153, 42);
		contentPane.add(button);
		
		lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblRegistrationForm.setBounds(245, 13, 193, 30);
		contentPane.add(lblRegistrationForm);
		
		label = new JLabel("");
		Image img3= new ImageIcon(this.getClass().getResource("/registerbig.png")).getImage();
		label.setIcon(new ImageIcon(img3));
		label.setBounds(158, 11, 64, 64);
		contentPane.add(label);
	}
}
