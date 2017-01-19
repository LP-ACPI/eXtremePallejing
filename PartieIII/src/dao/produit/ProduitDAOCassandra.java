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

public class ProduitDAOCassandra implements I_ProduitDAO {

	private static PreparedStatement statement;
	private static ResultSet resultSet;
	private static Connection connexion;
	private static String nomCatalogue;
	
	public ProduitDAOCassandra(Connection connexion) {
		super();
		ProduitDAOCassandra.connexion = connexion;
	}
	
	@Override
	public boolean create(I_Produit p){
		String insertProduit = "INSERT INTO ks_catalogue.produits_du_catalogue"
				+ "(nom_produit,nom_catalogue,prix_produit,quantite_produit)" 
					+"VALUES (?,?,?,?);";
		try {
			System.out.println("insertion produit pour catalogue " + nomCatalogue);
			setStatement(connexion.prepareStatement(insertProduit));
			statement.setString(1, p.getNom());
			statement.setString(2, nomCatalogue);
			statement.setDouble(3, p.getPrixUnitaireHT());
			statement.setInt(4, p.getQuantite());
			
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur création produit");
			e.printStackTrace();
		}
		return getResultSet() != null;		
	}
	

	@Override
	public boolean update(I_Produit p){
		String update = "UPDATE Produits SET quantite_produit=?,prix_produit=?"
				+ " WHERE nom_produit=? AND nom_catalogue=?";
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
		String delete = "DELETE FROM ks_catalogue.produits_du_catalogue"
			+ " WHERE nom_produit=? AND nom_catalogue=?;"
			+ "UPDATE ks_catalogue.catalogue_produits SET id_produit=?"
			+ "WHERE nom_catalogue=?";
		try {
			setStatement(connexion.prepareStatement(delete));
			statement.setString(1, p.getNom());
			statement.setString(2, nomCatalogue);
			statement.setNull(3, -1);
			statement.setString(4, nomCatalogue);
			
			setResultSet(statement.executeQuery());
		} catch (SQLException e) {
			System.out.println("erreur suppression produit");
			e.printStackTrace();
		}
		return getResultSet() != null;
	}
	

	@Override
	public I_Produit read(String nomP){
		String find = "SELECT * FROM ks_catalogue.produits_du_catalogue"
			+ " WHERE nom_produit=? AND nom_catalogue=?";
		I_Produit produit = null;
					
		try {

			setStatement(connexion.prepareStatement(find));
			statement.setString(1, nomP);
			statement.setString(2, nomCatalogue);
			
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom = getResultSet().getString("nom_produit");
				int qte = getResultSet().getInt("quantite_produit");
				double px  = getResultSet().getDouble("prix_produit");
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
		String all = "SELECT * FROM ks_catalogue.produits_du_catalogue WHERE nom_catalogue = ?";
		List<I_Produit> produits= new ArrayList<I_Produit>();
		
		try {
			setStatement(connexion.prepareStatement(all));
			statement.setString(1, nomCatalogue);
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom	= getResultSet().getString("nom_produit");
				int qte 	= getResultSet().getInt("quantite_produit");
				double px 	= getResultSet().getDouble("prix_produit");
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
		String count = "SELECT count(*) as nb_produits FROM ks_catalogue.produits_du_catalogue "
				+ "WHERE nom_catalogue = ? ALLOW FILTERING";
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
		ProduitDAOCassandra.statement = statement;
	}

	private ResultSet getResultSet() {
		return resultSet;
	}

	private void setResultSet(ResultSet rs) {
		ProduitDAOCassandra.resultSet = rs;
	}

	@Override
	public void setCatalogue(I_Catalogue catalog) {
		ProduitDAOCassandra.nomCatalogue = catalog.getNom();		
	}

}
