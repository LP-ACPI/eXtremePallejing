package DAO;

import java.sql.Connection;

public abstract class ConnexionDAO {

	protected static ConnexionDAO instance;
	
	public static ConnexionDAO getInstance() throws NullPointerException {
		if(instance != null)
			return instance;
		else throw new NullPointerException();
		
	}	
	public abstract Connection getConnexion();
	
	public abstract void closeConnexion();

}
