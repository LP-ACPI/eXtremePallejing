package dao.catalogue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class CatalogueDAORelationnel implements I_CatalogueDAO {

	private static PreparedStatement statement;
	private static ResultSet resultSet;
	private static Connection connexion;
	
	public CatalogueDAORelationnel(Connection connexion) {
		super();
		CatalogueDAORelationnel.connexion = connexion;
	}
	
	@Override
	public boolean create(I_Catalogue catalogue){
		String insererCatalogue = "INSERT INTO Catalogues(nomCatalogue) Values(?)";
		try {
			statement = connexion.prepareStatement(insererCatalogue);
			statement.setString(1,catalogue.getNom());			
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur création catalogue");
			e.printStackTrace();
		}
		return getResultSet() != null;		
	}

	@Override
	public boolean update(I_Catalogue catalogue) {
		String updateProduitsSQL = "UPDATE Produits SET catalogue=?"
				+ " WHERE nomProduit IN (?)";
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
		String deleteSQL = "DELETE FROM Catalogues"
				+ " WHERE nomCatalogue=?";
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
	public I_Catalogue read(String nomCatalogue) throws DAOException {
		String ProduitsCatalogSQL = "SELECT * From Produits"
				+ " WHERE catalogue=?";
		I_Catalogue catalog = new Catalogue(nomCatalogue);
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		try {
			statement = connexion.prepareStatement(ProduitsCatalogSQL);
			statement.setString(1,nomCatalogue);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom = getResultSet().getString("nomProduit");
				int qte = getResultSet().getInt("quantiteProduit");
				double px  = getResultSet().getDouble("prixProduit");
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
	public List<I_Catalogue> readAll() throws DAOException {
		String catalogsSQL = "SELECT * From Catalogues";
		List<I_Catalogue> listCatalogs = new ArrayList<I_Catalogue>();
		try {
			statement = connexion.prepareStatement(catalogsSQL);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom = getResultSet().getString("nomCatalogue");
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
		CatalogueDAORelationnel.resultSet = resultSet;
	}

	@Override
	public int getProductCount(I_Catalogue catalogue) throws DAOException {
		String count = "SELECT count(*) as nb_produits FROM PRODUITS WHERE catalogue = ?";
		int totalProduits = 0;
		try {
			statement = connexion.prepareStatement(count);
			statement.setString(1, catalogue.getNom());
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				totalProduits	= getResultSet().getInt("nb_produits");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("erreur compte des produits");
		}
		return totalProduits;
	}

}
