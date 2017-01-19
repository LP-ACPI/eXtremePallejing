package dao.produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ProduitDAORelationnel implements I_ProduitDAO {
	
	private static PreparedStatement statement;
	private static ResultSet resultSet;
	private static Connection connexion;
	private static String nomCatalogue;
	
	public ProduitDAORelationnel(Connection connexion) {
		super();
		ProduitDAORelationnel.connexion = connexion;
	}
	
	@Override
	public boolean create(I_Produit p){
		String insert = "INSERT INTO Produits(nomProduit,quantiteProduit,prixProduit,catalogue)"
				+ "Values(?,?,?,?)";
		try {
			System.out.println("insertion produit pour catalogue " + nomCatalogue);
			setStatement(connexion.prepareStatement(insert));
			statement.setString(1, p.getNom());
			statement.setInt(2, p.getQuantite());
			statement.setDouble(3, p.getPrixUnitaireHT());
			statement.setString(4,nomCatalogue);
			
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur création produit");
			e.printStackTrace();
		}
		return getResultSet() != null;		
	}
	

	@Override
	public boolean update(I_Produit p){
		String update = "UPDATE Produits SET quantiteProduit=?,prixProduit=?"
				+ " WHERE nomProduit=? AND catalogue=?";
		try {
			setStatement(connexion.prepareStatement(update));
			statement.setInt(1, p.getQuantite());
			statement.setDouble(2, p.getPrixUnitaireHT());
			statement.setString(3, p.getNom());
			statement.setString(4, nomCatalogue);
			
			setResultSet(statement.executeQuery());

		} catch (SQLException e) {
			System.out.println("erreur mise à jour produit");
			e.printStackTrace();
		}
		return getResultSet() != null;		
	}
	

	@Override
	public boolean delete(I_Produit p){
		String delete = "DELETE FROM Produits"
			+ " WHERE nomProduit=? AND catalogue=?";
		try {
			setStatement(connexion.prepareStatement(delete));
			statement.setString(1, p.getNom());
			statement.setString(2, nomCatalogue);
			
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur suppression produit");
			e.printStackTrace();
		}
		return getResultSet() != null;
	}
	

	@Override
	public I_Produit read(String nomP){
		String find = "SELECT * FROM Produits"
			+ " WHERE nomProduit=? AND catalogue=?";
		I_Produit produit = null;
					
		try {

			setStatement(connexion.prepareStatement(find));
			statement.setString(1, nomP);
			statement.setString(2, nomCatalogue);
			
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom = getResultSet().getString("nomProduit");
				int qte = getResultSet().getInt("quantiteProduit");
				double px  = getResultSet().getDouble("prixProduit");
				produit = new Produit(nom,px,qte);
			}
		} catch (SQLException e) {
			System.out.println("erreur recherche produit");			
			e.printStackTrace();			
		}
		return produit;
	}
	
	@Override
	public List<I_Produit> readAll(){
		String all = "SELECT * FROM PRODUITS WHERE catalogue = ?";
		List<I_Produit> produits= new ArrayList<I_Produit>();
		
		try {
			setStatement(connexion.prepareStatement(all));
			statement.setString(1, nomCatalogue);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom	= getResultSet().getString("nomProduit");
				int qte 	= getResultSet().getInt("quantiteProduit");
				double px 	= getResultSet().getDouble("prixProduit");
				produits.add(new Produit(nom,px,qte));
			}
		} catch (SQLException e) {
			System.out.println("erreur recherche des produits");
			e.printStackTrace();
		}
		return produits;
	}
	
	@Override
	public int catalogsProductCount() {
		String count = "SELECT count(*) as nb_produits FROM PRODUITS WHERE catalogue = ?";
		int totalProduits = 0;
		try {
			setStatement(connexion.prepareStatement(count));
			statement.setString(1, nomCatalogue);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				totalProduits	= getResultSet().getInt("nb_produits");
			}
		} catch (SQLException e) {
			System.out.println("erreur compte des produits");
			e.printStackTrace();
		}
		return totalProduits;
	}

		
	private void setStatement(PreparedStatement statement) {
		ProduitDAORelationnel.statement = statement;
	}

	private ResultSet getResultSet() {
		return resultSet;
	}

	private void setResultSet(ResultSet rs) {
		ProduitDAORelationnel.resultSet = rs;
	}

	@Override
	public void setCatalogue(I_Catalogue catalog) {
		ProduitDAORelationnel.nomCatalogue = catalog.getNom();		
	}
}