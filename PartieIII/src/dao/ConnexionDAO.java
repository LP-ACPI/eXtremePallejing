package dao;


public abstract class ConnexionDAO {

	protected static ConnexionDAO instance;
	
	public synchronized static ConnexionDAO getInstance() throws NullPointerException,DAOException {
		if(instance != null)
			return instance;
		else throw new NullPointerException();
	}
	
	public abstract void closeConnexion() throws DAOException;

}
