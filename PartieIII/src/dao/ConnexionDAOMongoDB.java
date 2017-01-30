package dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnexionDAOMongoDB extends ConnexionDAO {
	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;

	public ConnexionDAOMongoDB() throws DAOException{
		mongoClient   = new MongoClient("localhost",27017);
		mongoDatabase = mongoClient.getDatabase("db_catalogues");
		if(mongoClient == null)
			throw new DAOException("Connexion échouée!");
	}
		
	public synchronized static ConnexionDAOMongoDB getInstance() throws DAOException {
		if(instance == null || !(instance instanceof ConnexionDAOMongoDB))
			instance = new ConnexionDAOMongoDB(); 
		return (ConnexionDAOMongoDB) instance;
	}
	
	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}

	public void setMongoDatabase(MongoDatabase mongoDB) {
		mongoDatabase = mongoDB;
	}

	@Override
	public void closeConnexion() throws DAOException {
		mongoClient.close();
		System.out.println("Déconnexion");	
	}
}
