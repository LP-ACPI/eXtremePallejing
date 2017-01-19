package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDAORelationnel extends ConnexionDAO {
	
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url 	 = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
	private static String login  = "necesanym";
	private static String mdp 	 = "Neces#9A";
	private static Connection connexion;
		
	public ConnexionDAORelationnel() throws DAOException{
		super();
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url,login,mdp));
		} catch (SQLException | ClassNotFoundException e) {
			throw new DAOException("Connexion à la base de données a échoué: ", e.getCause());
		}
	}
	
	public ConnexionDAORelationnel(String driver,String url,String login,String mdp) throws DAOException {
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url,login,mdp));
		} catch (Exception e) {
			throw new DAOException("Connexion à la base de données a échoué: ", e.getCause());
		}
	}

	public synchronized static ConnexionDAORelationnel getInstance() throws DAOException{
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
	public void closeConnexion() throws DAOException {
		try {
			connexion.close();
			System.out.println("Déconnexion de la base de données");
		} catch (SQLException e) {	
			throw new DAOException("Déconnexion échouée", e.getCause());			
		}
	}
	
	
}
