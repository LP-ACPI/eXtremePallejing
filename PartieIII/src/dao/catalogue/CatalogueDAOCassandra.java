package dao.catalogue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class CatalogueDAOCassandra implements I_CatalogueDAO{

	private static PreparedStatement statement;
	private static ResultSet resultSet;
	private static Connection connexion;
	
	public CatalogueDAOCassandra(Connection connexion) {
		super();
		CatalogueDAOCassandra.connexion = connexion;
	}
	
	@Override
	public boolean create(I_Catalogue catalogue){
		String insererCatalogue = "INSERT INTO ks_catalogue.produits_du_catalogue(nom_Catalogue,nom_produit) "
				+ "Values(?,?)";
		try {
			statement = connexion.prepareStatement(insererCatalogue);
			statement.setString(1,catalogue.getNom());
			statement.setString(2,"none");
			statement.executeQuery();
			return true;
		} catch (SQLException e) {
			System.out.println("erreur création catalogue");
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean update(I_Catalogue catalogue) {
		String updateProduitsSQL = "UPDATE ks_catalogue.produits_du_catalogue SET nom_catalogue=?"
				+ " WHERE nom_produit IN (?)";
		try {
			statement = connexion.prepareStatement(updateProduitsSQL);
			statement.setString(1,catalogue.getNom());
			statement.setString(2,catalogue.getNomProduits().toString());
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur modification catalogue");
			e.printStackTrace();
		}
		return getResultSet() != null;		
	}

	@Override
	public boolean delete(I_Catalogue catalogue) {
		String deleteSQL = "DELETE FROM ks_catalogue.produits_du_catalogue"
				+ " WHERE nom_catalogue=?";
		try {
			statement = connexion.prepareStatement(deleteSQL);
			statement.setString(1,catalogue.getNom());
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur suppression catalogue");
			e.printStackTrace();
		}
		return getResultSet() != null;	
	}

	@Override
	public I_Catalogue read(String nomCatalogue) {
		String ProduitsCatalogSQL = "SELECT * From ks_catalogue.produits_du_catalogue"
				+ " WHERE nom_catalogue=? ALLOW_FILTERING";
		I_Catalogue catalog = new Catalogue(nomCatalogue);
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		try {
			statement = connexion.prepareStatement(ProduitsCatalogSQL);
			statement.setString(1,nomCatalogue);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom = getResultSet().getString("nom_produit");
				int qte = getResultSet().getInt("quantite_produit");
				double px  = getResultSet().getDouble("prix_produit");
				listProduits.add(new Produit(nom,px,qte));
			}
			catalog.addProduits(listProduits);
		} catch (SQLException e) {
			System.out.println("erreur recherche catalogue");
			e.printStackTrace();
		}
		return catalog;
	}

	@Override
	public List<I_Catalogue> readAll() {
		String catalogsSQL = "SELECT nom_catalogue From ks_catalogue.produits_du_catalogue";
		List<I_Catalogue> listCatalogs = new ArrayList<I_Catalogue>();
		try {
			statement = connexion.prepareStatement(catalogsSQL);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom = getResultSet().getString("nom_catalogue");
				listCatalogs.add(new Catalogue(nom));
			}
		} catch (SQLException e) {
			System.out.println("erreur liste de catalogues");
			e.printStackTrace();
		}
		return listCatalogs;
	}
	
	public static ResultSet getResultSet() {
		return resultSet;
	}

	public static void setResultSet(ResultSet resultSet) {
		CatalogueDAOCassandra.resultSet = resultSet;
	}
}
