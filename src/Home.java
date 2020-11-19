import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Home");
		
		JButton btnAdmin = new JButton("Admin");
		Image img1= new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		btnAdmin.setIcon(new ImageIcon(img1));
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				AdminLogin admlogin=new AdminLogin();
				admlogin.setVisible(true);
			}
		});
		btnAdmin.setBounds(53, 189, 153, 42);
		frame.getContentPane().add(btnAdmin);
		JButton btnEmployee = new JButton("Employee");
		Image img2= new ImageIcon(this.getClass().getResource("/employee.png")).getImage();
		btnEmployee.setIcon(new ImageIcon(img2));
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//frame.dispose();
					EmployeeLogin emplogin=new EmployeeLogin();
					emplogin.setVisible(true);
				}
		});
		btnEmployee.setBounds(265, 189, 153, 42);
		frame.getContentPane().add(btnEmployee);
		
		JButton btnPassenger = new JButton("Passenger");
		Image img3= new ImageIcon(this.getClass().getResource("/passenger.png")).getImage();
		btnPassenger.setIcon(new ImageIcon(img3));
		btnPassenger.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				PassengerLogin passlogin=new PassengerLogin();
				passlogin.setVisible(true);
			}
		});
		btnPassenger.setBounds(471, 189, 153, 42);
		frame.getContentPane().add(btnPassenger);
		
		JLabel lblVehicleManagementSystem = new JLabel("VEHICLE MANAGEMENT SYSTEM");
		lblVehicleManagementSystem.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblVehicleManagementSystem.setBounds(163, 91, 358, 37);
		frame.getContentPane().add(lblVehicleManagementSystem);
		
		JLabel Logo = new JLabel("");
		Image img4= new ImageIcon(this.getClass().getResource("/bus.png")).getImage();
		Logo.setIcon(new ImageIcon(img4));
		Logo.setBounds(310, 11, 64, 64);
		frame.getContentPane().add(Logo);
	}
}
