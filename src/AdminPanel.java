import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class AdminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Admin Dashboard");
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminPanel = new JLabel("Dashboard");
		lblAdminPanel.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblAdminPanel.setBounds(283, 13, 117, 30);
		contentPane.add(lblAdminPanel);
		
		JButton btnManageEmployees = new JButton("Manage Employees");
		Image img4= new ImageIcon(this.getClass().getResource("/employee.png")).getImage();
		btnManageEmployees.setIcon(new ImageIcon(img4));
		btnManageEmployees.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnManageEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManageEmp mngemp=new ManageEmp();
				mngemp.setVisible(true);
			}
		});
		btnManageEmployees.setBounds(242, 116, 200, 42);
		contentPane.add(btnManageEmployees);
		
		JButton btnManageBuses = new JButton("Manage Buses");
		Image img3= new ImageIcon(this.getClass().getResource("/bus2.png")).getImage();
		btnManageBuses.setIcon(new ImageIcon(img3));
		btnManageBuses.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnManageBuses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManageBus mngbus=new ManageBus();
				mngbus.setVisible(true);
			}
		});
		btnManageBuses.setBounds(242, 189, 200, 42);
		contentPane.add(btnManageBuses);
		
		JButton btnManageRoutes = new JButton("Manage Routes");
		Image img2= new ImageIcon(this.getClass().getResource("/route.png")).getImage();
		btnManageRoutes.setIcon(new ImageIcon(img2));
		btnManageRoutes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnManageRoutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManageRoute mngrte=new ManageRoute();
				mngrte.setVisible(true);
			}
		});
		btnManageRoutes.setBounds(242, 262, 200, 42);
		contentPane.add(btnManageRoutes);
		
		JButton btnLogout = new JButton("Logout");
		Image img1= new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		btnLogout.setIcon(new ImageIcon(img1));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminLogin login=new AdminLogin();
				login.setVisible(true);
			}
		});
		btnLogout.setBounds(521, 11, 153, 42);
		contentPane.add(btnLogout);
		
		JLabel label = new JLabel("");
		label.setBounds(185, 11, 64, 64);
		contentPane.add(label);
	}
}
