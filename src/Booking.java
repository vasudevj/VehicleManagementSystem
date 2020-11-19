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
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Booking extends JFrame {

	private JPanel contentPane;
	private JTextField fname;
	private JTextField bpoint;
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
					Booking frame = new Booking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	private JTextField seats;
	private JTextField dpoint;
	private JTextField bnum;
	private JButton button;
	private JLabel lblBookABus;
	
	/**
	 * Create the frame.
	 */
	public Booking() {
		con=mysqlConnection.dbConnector();
		setTitle("Booking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Fullname");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(179, 114, 61, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Boarding Point");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(179, 249, 89, 14);
		contentPane.add(lblUsername);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAge.setBounds(179, 151, 48, 14);
		contentPane.add(lblAge);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSex.setBounds(179, 180, 48, 14);
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
		male.setBounds(233, 176, 71, 23);
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
		female.setBounds(306, 176, 80, 23);
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
		other.setBounds(388, 176, 61, 23);
		contentPane.add(other);
		
		fname = new JTextField();
		fname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fname.setBounds(278, 109, 200, 25);
		contentPane.add(fname);
		fname.setColumns(10);
		
		bpoint = new JTextField();
		bpoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bpoint.setBounds(278, 244, 160, 25);
		contentPane.add(bpoint);
		bpoint.setColumns(10);
		
		age = new JTextField();
		age.setFont(new Font("Tahoma", Font.PLAIN, 13));
		age.setBounds(278, 148, 35, 25);
		contentPane.add(age);
		age.setColumns(10);
		
		JButton btnBook = new JButton("Book");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String user = null;
					String getUser="SELECT userid FROM passenger WHERE login=1;";
					PreparedStatement pst2=con.prepareStatement(getUser);
					ResultSet rs2=pst2.executeQuery();
					while(rs2.next())
						user= rs2.getString(1);
					
					
					int book=0,total=0;
					PreparedStatement pst4=con.prepareStatement("SELECT book, total FROM bus WHERE b_num=?;");
					pst4.setString(1, bnum.getText());
					ResultSet rs4=pst4.executeQuery();
					while(rs4.next())
					{
						book= rs4.getInt(1);
						total=rs4.getInt(2);
					}
					if(book>=total)
					{
						JOptionPane.showMessageDialog(null, "Bus is full! Choose another bus.");
						
						
					}
					else
					{
						if((total-book)>=Integer.parseInt(seats.getText()))
						{
							String sex=null;
							String booker="INSERT INTO booking VALUES(?,?,?,?,?,?,?,?);";
							PreparedStatement pst=con.prepareStatement(booker);
							pst.setString(1, user);
							pst.setString(2, fname.getText());
							pst.setString(3, age.getText());
							if(male.isSelected())
								sex="Male";
							else if(female.isSelected())
								sex="Female";
							else if(other.isSelected())
								sex="Other";
							pst.setString(4, sex);
							pst.setString(5, seats.getText());
							pst.setString(6, bpoint.getText());
							pst.setString(7, dpoint.getText());
							pst.setString(8, bnum.getText());
							pst.execute();
							JOptionPane.showMessageDialog(null, "Booking Done!");
							PreparedStatement pst3=con.prepareStatement("UPDATE bus SET book=book+? WHERE b_num=?;");
							pst3.setString(2, bnum.getText());
							pst3.setInt(1, Integer.parseInt(seats.getText()));
							pst3.execute();
							pst3.close();
							pst.close();
						}
						else
							JOptionPane.showMessageDialog(null, "Seats not available!");
					}
					pst2.close();
					rs2.close();
					pst4.close();
					rs4.close();
					dispose();
					PassengerPanel passpan=new PassengerPanel();
					passpan.setVisible(true);
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				
			}
		});
		btnBook.setBounds(278, 323, 153, 42);
		contentPane.add(btnBook);
		
		seats = new JTextField();
		seats.setFont(new Font("Tahoma", Font.PLAIN, 13));
		seats.setBounds(278, 207, 35, 25);
		contentPane.add(seats);
		seats.setColumns(10);
		
		JLabel lblContact = new JLabel("Seats");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact.setBounds(179, 212, 48, 14);
		contentPane.add(lblContact);
		
		JLabel lblDropPoint = new JLabel("Drop Point");
		lblDropPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDropPoint.setBounds(179, 285, 89, 14);
		contentPane.add(lblDropPoint);
		
		dpoint = new JTextField();
		dpoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dpoint.setBounds(278, 280, 160, 25);
		contentPane.add(dpoint);
		dpoint.setColumns(10);
		
		JLabel lblBusNumber = new JLabel("Bus number");
		lblBusNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBusNumber.setBounds(179, 78, 80, 14);
		contentPane.add(lblBusNumber);
		
		bnum = new JTextField();
		bnum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bnum.setBounds(278, 73, 50, 25);
		contentPane.add(bnum);
		bnum.setColumns(10);
		
		button = new JButton("Back");
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PassengerPanel passpanel=new PassengerPanel();
				passpanel.setVisible(true);
			}
		});
		button.setBounds(505, 11, 153, 42);
		contentPane.add(button);
		
		lblBookABus = new JLabel("Book a bus");
		lblBookABus.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblBookABus.setBounds(283, 11, 117, 30);
		contentPane.add(lblBookABus);
	}
}
