package dao;

import java.sql.Connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnexionDAOMongoDB extends ConnexionDAO {
	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;

	public ConnexionDAOMongoDB() {
		mongoClient   = new MongoClient("localhost",27017);
		mongoDatabase = mongoClient.getDatabase("db_catalogues");
	}
		
	public synchronized static ConnexionDAOMongoDB getInstance() {
		if(instance == null)
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
	public Connection getConnexion() {
		return null;
	}

	@Override
	public void setConnexion(Connection connexion) {}

	@Override
	public void closeConnexion() throws DAOException {
		mongoClient.close();
	}
}
