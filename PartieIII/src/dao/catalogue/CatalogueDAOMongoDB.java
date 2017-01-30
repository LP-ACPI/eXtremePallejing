package dao.catalogue;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dao.ConnexionDAOMongoDB;
import dao.DAOException;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class CatalogueDAOMongoDB implements I_CatalogueDAO{

	private static MongoDatabase mongoDatabase;
	private static MongoCollection<Document> collectionCatalogues;

	public CatalogueDAOMongoDB(ConnexionDAOMongoDB connexion) throws DAOException {
		super();
		mongoDatabase = connexion.getMongoDatabase();
		collectionCatalogues = mongoDatabase.getCollection("catalogues");
		if(collectionCatalogues == null)
			throw new DAOException("erreur connexion à mongoDB");
	}

	@Override
	public boolean create(I_Catalogue catalogue) {
		Document catalog = new Document()
			.append("nomCatalogue",catalogue.getNom());
		collectionCatalogues.insertOne(catalog);
		return true;
	}

	@Override
	public boolean update(I_Catalogue catalogue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(I_Catalogue catalogue) {
		collectionCatalogues.deleteMany(new Document("nomCatalogue",catalogue.getNom()));
		return true;
	}

	@Override
	public I_Catalogue read(String nomCatalogue) throws DAOException{
		I_Catalogue catalog = new Catalogue(nomCatalogue);
		List<I_Produit> produitList = new ArrayList<I_Produit>();
		
		Document catal = collectionCatalogues.find(new Document("nomCatalogue",nomCatalogue)).first();
		List<Document> prodsList = (List<Document>) catal.get("produits");
		if(prodsList != null){
			for(Document doc : prodsList){
				String nom  = doc.getString("nomProduit");
				int qte     = doc.getInteger("quantite");
				double prix = doc.getDouble("prixHT");
				produitList.add(new Produit(nom,prix,qte));
			}
			catalog.addProduits(produitList);
		}
		return catalog;
	}

	@Override
	public List<I_Catalogue> readAll() throws DAOException {
		List<I_Catalogue> catalogList = new ArrayList<I_Catalogue>();
		for(Document doc : collectionCatalogues.find()){
			String nomCata = doc.getString("nomCatalogue");
			catalogList.add(new Catalogue(nomCata));
		}
		return catalogList;
	}

	@Override
	public int getProductCount(I_Catalogue catalogue) throws DAOException {
		Document catal = collectionCatalogues.find(new Document("nomCatalogue",catalogue.getNom())).first();
		List<?> lst = (List<?>) catal.get("produits");
		return lst == null ? 0 : lst.size();
	}

}
