import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class EmployeePanel extends JFrame {

	private JPanel contentPane;
	private JTable bustable;
	private JTable booktable;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePanel frame = new EmployeePanel();
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
	public EmployeePanel() {
		con=mysqlConnection.dbConnector();
		setTitle("Employee Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 664, 137);
		contentPane.add(scrollPane);
		
		bustable = new JTable();
		scrollPane.setViewportView(bustable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 273, 664, 137);
		contentPane.add(scrollPane_1);
		
		booktable = new JTable();
		scrollPane_1.setViewportView(booktable);
		
		JButton btnViewBus = new JButton("View Buses Details");
		Image img2= new ImageIcon(this.getClass().getResource("/bus2.png")).getImage();
		btnViewBus.setIcon(new ImageIcon(img2));
		btnViewBus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String viewer="SELECT b_num, time, book, total, src, dest, stops FROM bus, route WHERE bus.r_id=route.r_id;";
					PreparedStatement pst1=con.prepareStatement(viewer);
					ResultSet rs1=pst1.executeQuery();
					bustable.setModel(DbUtils.resultSetToTableModel(rs1));
					pst1.close();
					rs1.close();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnViewBus.setBounds(10, 11, 200, 42);
		contentPane.add(btnViewBus);
		
		JButton btnViewBook = new JButton("View Bookings");
		Image img1= new ImageIcon(this.getClass().getResource("/ticket.png")).getImage();
		btnViewBook.setIcon(new ImageIcon(img1));
		btnViewBook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String bviewer="SELECT name, age, sex, seats, bpoint, dpoint FROM booking WHERE b_num=?;";
					PreparedStatement pst2=con.prepareStatement(bviewer);
					pst2.setString(1, textField.getText());
					ResultSet rs2=pst2.executeQuery();
					booktable.setModel(DbUtils.resultSetToTableModel(rs2));
					pst2.close();
					rs2.close();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnViewBook.setBounds(184, 220, 153, 42);
		contentPane.add(btnViewBook);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(99, 229, 75, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblBusNumber = new JLabel("Bus Number");
		lblBusNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBusNumber.setBounds(10, 234, 79, 14);
		contentPane.add(lblBusNumber);
		
		JButton logout = new JButton("Logout");
		Image img3= new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		logout.setIcon(new ImageIcon(img3));
		logout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EmployeeLogin emplogin=new EmployeeLogin();
				emplogin.setVisible(true);
			}
		});
		logout.setBounds(521, 11, 153, 42);
		contentPane.add(logout);
		
		JLabel label = new JLabel("Dashboard");
		label.setFont(new Font("Cambria", Font.PLAIN, 25));
		label.setBounds(283, 13, 117, 30);
		contentPane.add(label);
	}
}
