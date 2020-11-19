import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class ManageRoute extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageRoute frame = new ManageRoute();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	private JTextField rid;
	private JTextField src;
	private JTextField dest;
	private JTextField stops;
	public void refresh()
	{
		try{
			String viewer="SELECT * FROM route;";
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
	public ManageRoute() {
		con=mysqlConnection.dbConnector();
		setTitle("Manage Routes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 70, 636, 190);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnViewDetails = new JButton("View Details");
		Image img4= new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		btnViewDetails.setIcon(new ImageIcon(img4));
		btnViewDetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnViewDetails.setBounds(27, 17, 153, 42);
		contentPane.add(btnViewDetails);
		
		JLabel lblBusNumber = new JLabel("Route ID");
		lblBusNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBusNumber.setBounds(28, 269, 89, 14);
		contentPane.add(lblBusNumber);
		
		JLabel lblNewLabel = new JLabel("Source");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(127, 269, 48, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Destination");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(272, 269, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRouteId = new JLabel("Stops");
		lblRouteId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRouteId.setBounds(413, 269, 48, 14);
		contentPane.add(lblRouteId);
		
		rid = new JTextField();
		rid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rid.setBounds(28, 294, 75, 25);
		contentPane.add(rid);
		rid.setColumns(10);
		
		src = new JTextField();
		src.setFont(new Font("Tahoma", Font.PLAIN, 13));
		src.setBounds(127, 294, 120, 25);
		contentPane.add(src);
		src.setColumns(10);
		
		dest = new JTextField();
		dest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dest.setBounds(272, 294, 120, 25);
		contentPane.add(dest);
		dest.setColumns(10);
		
		stops = new JTextField();
		stops.setFont(new Font("Tahoma", Font.PLAIN, 13));
		stops.setBounds(410, 294, 150, 25);
		contentPane.add(stops);
		stops.setColumns(10);
		
		JButton btnAddRoute = new JButton("Add New Route");
		Image img2= new ImageIcon(this.getClass().getResource("/radd.png")).getImage();
		btnAddRoute.setIcon(new ImageIcon(img2));
		btnAddRoute.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String adder="INSERT INTO route VALUES(?,?,?,?);";
					PreparedStatement pst=con.prepareStatement(adder);
					pst.setString(1, rid.getText());
					pst.setString(2, src.getText());
					pst.setString(3, dest.getText());
					pst.setString(4, stops.getText());
					pst.execute();
					pst.close();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				refresh();
			}
		});
		btnAddRoute.setBounds(28, 347, 153, 42);
		contentPane.add(btnAddRoute);
		
		JButton btnDeleteBus = new JButton("Delete Route");
		Image img3= new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		btnDeleteBus.setIcon(new ImageIcon(img3));
		btnDeleteBus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String deleter="DELETE FROM route WHERE r_id=?;";
					PreparedStatement pst=con.prepareStatement(deleter);
					pst.setString(1, rid.getText());
					pst.execute();
					pst.close();
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				refresh();
			}
		});
		btnDeleteBus.setBounds(407, 347, 153, 42);
		contentPane.add(btnDeleteBus);
		
		JButton button = new JButton("Back");
		Image img1= new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminPanel admpanel=new AdminPanel();
				admpanel.setVisible(true);
			}
		});
		button.setBounds(510, 17, 153, 42);
		contentPane.add(button);
		
		JLabel lblManageRoutes = new JLabel("Manage Routes");
		lblManageRoutes.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblManageRoutes.setBounds(261, 19, 162, 30);
		contentPane.add(lblManageRoutes);
	}
}
