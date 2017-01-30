package dao.produit;

import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dao.ConnexionDAOMongoDB;
import dao.DAOException;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ProduitDAOMongoDB implements I_ProduitDAO{

	private static MongoDatabase mongoDatabase;
	private static MongoCollection<Document> collectionCatalogues;
	private static String nomCatalogue;

	public ProduitDAOMongoDB(ConnexionDAOMongoDB connexion) {
		super();
		mongoDatabase = connexion.getMongoDatabase();	
		collectionCatalogues = mongoDatabase.getCollection("catalogues");
	}

	
	@Override
	public boolean create(I_Produit p) {
		Document produit =  new Document()
				.append("nomProduit", p.getNom())
				.append("quantite", p.getQuantite())
				.append("prixHT", p.getPrixUnitaireHT());
		collectionCatalogues.updateOne(
				new Document("nomCatalogue", nomCatalogue), 
				new Document("$addToSet", new Document("produits",produit))
			);
		return true;
	}

	@Override
	public boolean update(I_Produit p) {
		Document vieuxProduit = new Document()
				.append("nomCatalogue", nomCatalogue)
				.append("produits.nomProduit", p.getNom());
		
		Document nouveauProduit = new Document()
				.append("nomProduit", p.getNom())
				.append("quantite", p.getQuantite())
				.append("prixHT", p.getPrixUnitaireHT());
		
		collectionCatalogues.updateOne(vieuxProduit, 
				new Document("$set", new Document("produits.$", nouveauProduit))
			);
		return true;
	}

	@Override
	public boolean delete(I_Produit p) {
		collectionCatalogues.updateOne(
			new Document("nomCatalogue",nomCatalogue),
				new Document("$pull",
					new Document("produits",
						new Document("nomProduit",p.getNom()
			))));
		return true;
	}

	@Override
	public I_Produit read(String nomProduit) throws DAOException {
		I_Produit produit = null;
		Document catalProdDoc = collectionCatalogues.find(new Document("nomCatalogue", nomCatalogue)).first();
		List<Document> prodColl = (List<Document>) catalProdDoc.get("produits");
		for(Document prodDoc: prodColl)
			if(prodDoc.getString("nomProduit").equals(nomProduit)){
				String nom  = prodDoc.getString("nomProduit");
				int qte     = prodDoc.getInteger("quantite");
				double prix = prodDoc.getDouble("prixHT");
				produit = new Produit(nom,prix,qte);
			}
		return produit;
	}

	@Override
	public List<I_Produit> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCatalogue(I_Catalogue catalog) {
		nomCatalogue = catalog.getNom();
	}

	@Override
	public int catalogsProductCount() {
		Document catal = collectionCatalogues.find(new Document("nomCatalogue",nomCatalogue)).first();
		List<?> lst = (List<?>) catal.get("produits");
		return lst == null ? 0 : lst.size();
	}

}
