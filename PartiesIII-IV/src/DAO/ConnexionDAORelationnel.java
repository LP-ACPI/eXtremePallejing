package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDAORelationnel extends ConnexionDAO{
	
	private static String driver= "oracle.jdbc.driver.OracleDriver";
	private static String url 	= "jdbc:oracle:thin:@162.38.222.149:1521:iut";
	private static String login = "necesanym";
	private static String mdp 	= "Neces#9A";
	private static Connection connexion;
	
	private static ConnexionDAORelationnel instance;
	
	public ConnexionDAORelationnel() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		setConnexion(DriverManager.getConnection(url,login,mdp));
	}
	
	public static ConnexionDAORelationnel getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new ConnexionDAORelationnel();
		return instance;
	}	

	public static Connection getConnexion() {
		return connexion;
	}

	public static void setConnexion(Connection connexion) {
		ConnexionDAORelationnel.connexion = connexion;
	}
	
	public static void closeConnexion(){
		try {
			connexion.close();
		} catch (SQLException e) {
			System.out.println("Déconnexion échouée");
			e.printStackTrace();
		}
	}
	
	
}
