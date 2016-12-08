package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private Connection cn;
	private String driver;
	private String url;
	private String login;
	private String mdp;
	
	public Connexion(String dr,String ur,String lg,String mp) throws ClassNotFoundException, SQLException{
		driver = dr;
		url = ur;
		login = lg;
		mdp = mp;		
		
		Class.forName(driver);
		cn = DriverManager.getConnection(url,login,mdp);
	}
	
	public Connection getConnexion(){
		return cn;
	}
	
}
