package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;
public class  ProduitDAOsql implements I_ProduitDAO{
	
	private PreparedStatement statement;
	private ResultSet resultSet;
	private Connection connexion;
	
	public ProduitDAOsql(String driver,String url,String login,String mdp) throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
		connexion = DriverManager.getConnection(url,login,mdp);
		
		
		
	}
	
	@Override
	public boolean create(I_Produit p){
		String insert = "INSERT INTO Produits(nomproduit,quantite,prixUnitaire)"
				+ "Values(?,?,?)";
		try {
			statement = connexion.prepareStatement(insert);
			statement.setString(1, p.getNom());
			statement.setInt(2, p.getQuantite());
			statement.setDouble(3, p.getPrixUnitaireHT());
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getResultSet() != null;
	}
	

	@Override
	public boolean update(I_Produit p){
		String update = "UPDATE Produits SET quantite=?,prixUnitaire=?"
				+ " WHERE nomproduit=?";
		try {
			statement = connexion.prepareStatement(update);
			statement.setInt(1, p.getQuantite());
			statement.setDouble(2, p.getPrixUnitaireHT());
			statement.setString(3, p.getNom());
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getResultSet() != null;
	}
	

	@Override
	public boolean delete(I_Produit p){
		String delete = "DELETE FROM Produits"
			+ " WHERE nomproduit=?";
		try {
			statement = connexion.prepareStatement(delete);
			statement.setString(1, p.getNom());
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return getResultSet() != null;	
	}
	

	@Override
	public I_Produit find(String nomP) throws SQLException{
		String find = "SELECT * FROM Produits"
			+ " WHERE nomproduit=?";
		try {
			statement = connexion.prepareStatement(find);
			statement.setString(1, nomP);
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String nom = getResultSet().getString("nomproduit");
		int qte = getResultSet().getInt("quantite");
		double px = getResultSet().getDouble("prixunitaire");
		
		return new Produit(nom,px,qte);	
	}
	
	

	@Override
	public I_Catalogue findAll(){
		String all = "SELECT * FROM Produits";
		try {
			statement = connexion.prepareStatement(all);
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		while(getResultSet().next()){
//		}
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet rs) {
		this.resultSet = rs;
	}

	public Connection getConnection() {
		return connexion;
	}

	public void setConnection(Connection cn) {
		this.connexion = cn;
	}

}
