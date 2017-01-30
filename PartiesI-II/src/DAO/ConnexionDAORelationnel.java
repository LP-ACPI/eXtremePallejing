package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDAORelationnel extends ConnexionDAO {
	
	private static String driver= "oracle.jdbc.driver.OracleDriver";
	private static String url 	= "jdbc:oracle:thin:@nope";
	private static String login = "test";
	private static String mdp 	= "test";
	private static Connection connexion;
		
	public ConnexionDAORelationnel(){
		super();
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url,login,mdp));
		} catch (SQLException |ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public ConnexionDAORelationnel(String driver,String url,String login,String mdp) {
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url,login,mdp));
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Connexion à la BD échouée");
			e.printStackTrace();
		}
	}

	public static ConnexionDAORelationnel getInstance(){
		if(instance == null)
			instance = new ConnexionDAORelationnel();
		return (ConnexionDAORelationnel) instance;
	}	
	
	public void setConnexion(Connection connexion) {
		ConnexionDAORelationnel.connexion = connexion;
	}
	
	@Override
	public Connection getConnexion() {
		return connexion;
	}

	@Override
	public void closeConnexion() {
		try {
			connexion.close();
			System.out.println("Déconnexion de la base de données");
		} catch (SQLException e) {
			System.out.println("Déconnexion échouée");
			e.printStackTrace();
		}
	}
	
	
}
