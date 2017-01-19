package dao;

import java.sql.Connection;

public abstract class ConnexionDAO {

	protected static ConnexionDAO instance;
	
	public synchronized static ConnexionDAO getInstance() throws NullPointerException,DAOException {
		if(instance != null)
			return instance;
		else throw new NullPointerException();
	}
	
	public abstract Connection getConnexion();
	public abstract void setConnexion(Connection connexion);
	
	public abstract void closeConnexion() throws DAOException;

}
