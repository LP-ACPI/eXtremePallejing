package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDAOCassandra extends ConnexionDAO{
	
	private static String driver = "org.apache.cassandra.cql.jdbc.CassandraDriver";
	private static String url 	 = "jdbc:cassandra://127.0.0.1:9160/ks_catalogue";

	private static Connection connexion;
		
	public ConnexionDAOCassandra() throws DAOException{
		super();
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("Connexion à la base de données a échoué: ", e.getCause());
		}
	}
	public ConnexionDAOCassandra(String driver,String url) throws DAOException {
		super();
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url));
		} catch (Exception e) {
			throw new DAOException("Connexion à la base de données a échoué: ", e.getCause());
		}
	}
	
	public ConnexionDAOCassandra(String driver,String url,String login,String mdp) throws DAOException {
		super();
		try {
			Class.forName(driver);
			setConnexion(DriverManager.getConnection(url,login,mdp));
		} catch (Exception e) {
			throw new DAOException("Connexion à la base de données a échoué: ", e.getCause());
		}
	}

	public synchronized static ConnexionDAOCassandra getInstance() throws DAOException{
		if(instance == null)
			instance = new ConnexionDAOCassandra();
		return (ConnexionDAOCassandra) instance;
	}	
	
	@Override
	public Connection getConnexion() {
		return connexion;
	}

	@Override
	public void closeConnexion() throws DAOException {
		try {
			connexion.close();
		} catch (SQLException e) {
			throw new DAOException("Déconnexion échouée", e.getCause());
		}		
	}

	@Override
	public void setConnexion(Connection connexion) {
		ConnexionDAOCassandra.connexion = connexion;		
	}

}
