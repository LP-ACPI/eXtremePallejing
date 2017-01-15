package DAO;

import java.sql.SQLException;

public abstract class ConnexionDAO {

	private static ConnexionDAO instance;
	
	public static ConnexionDAO getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new ConnexionDAORelationnel();
		return instance;
	}	
	
	public static void closeConnexion(){}
}
