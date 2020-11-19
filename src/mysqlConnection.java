import java.sql.*;

import javax.swing.*;
public class mysqlConnection {
	Connection con=null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle","root","vasudev");
			return con;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);	
			return null;
		}
	}
}
