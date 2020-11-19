import java.awt.BorderLayout;

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PassengerPanel extends JFrame {

	private JPanel contentPane;
	private JTable bustable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerPanel frame = new PassengerPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	private JTable booktable;
	
	/**
	 * Create the frame.
	 */
	public PassengerPanel() {
		con=mysqlConnection.dbConnector();
		setTitle("Passenger Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 664, 134);
		contentPane.add(scrollPane);
		
		bustable = new JTable();
		scrollPane.setViewportView(bustable);
		
		JButton btnNewButton = new JButton("View Buses");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(10, 32, 175, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Book a Bus");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					Booking book=new Booking();
					book.setVisible(true);
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnNewButton_2.setBounds(521, 238, 153, 42);
		contentPane.add(btnNewButton_2);
		
		JButton btnViewMyBookings = new JButton("View My Bookings");
		btnViewMyBookings.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewMyBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String user = null;
					String getUser="SELECT userid FROM passenger WHERE login=1;";
					PreparedStatement pst3=con.prepareStatement(getUser);
					ResultSet rs3=pst3.executeQuery();
					while(rs3.next())
						user= rs3.getString(1);
					String bviewer="SELECT * FROM booking WHERE userid=?;";
					PreparedStatement pst2=con.prepareStatement(bviewer);
					pst2.setString(1, user);
					ResultSet rs2=pst2.executeQuery();
					booktable.setModel(DbUtils.resultSetToTableModel(rs2));
					pst2.close();
					rs2.close();
					pst3.close();
					rs3.close();
				}catch(Exception exp)
				{
					
					JOptionPane.showMessageDialog(null, "No bookings found!");
				}
			}
		});
		btnViewMyBookings.setBounds(10, 238, 153, 42);
		contentPane.add(btnViewMyBookings);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 291, 664, 104);
		contentPane.add(scrollPane_1);
		
		booktable = new JTable();
		scrollPane_1.setViewportView(booktable);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String user = null;
					String getUser="SELECT userid FROM passenger WHERE login=1;";
					PreparedStatement pst4=con.prepareStatement(getUser);
					ResultSet rs4=pst4.executeQuery();
					while(rs4.next())
						user= rs4.getString(1);
					String logout="UPDATE passenger SET login=0 WHERE userid=?";
					PreparedStatement pst5=con.prepareStatement(logout);
					pst5.setString(1, user);
					pst5.execute();
					pst4.close();
					rs4.close();
					dispose();
					PassengerLogin passlogin=new PassengerLogin();
					passlogin.setVisible(true);
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnLogout.setBounds(521, 32, 153, 42);
		contentPane.add(btnLogout);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblDashboard.setBounds(283, 32, 117, 30);
		contentPane.add(lblDashboard);
	}
}
