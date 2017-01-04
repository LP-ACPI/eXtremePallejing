package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Metier.I_Catalogue;
import Metier.Catalogue;
import Metier.I_Produit;
import Metier.Produit;

public class  ProduitDAOrelationnel implements I_ProduitDAO{
	
	private static PreparedStatement statement;
	private static ResultSet resultSet;
	private static Connection connexion;
	
	public ProduitDAOrelationnel(String driver,String url,String login,String mdp) throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
		connexion = DriverManager.getConnection(url,login,mdp);
	}
	
	@Override
	public boolean create(I_Produit p){
		String insert = "INSERT INTO Produits(nomProduit,quantiteProduit,prixProduit)"
				+ "Values(?,?,?)";
		try {
			setStatement(connexion.prepareStatement(insert));
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
		String update = "UPDATE Produits SET quantiteProduit=?,prixProduit=?"
				+ " WHERE nomProduit=?";
		try {
			setStatement(connexion.prepareStatement(update));
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
	public boolean delete(String nomProduit){
		String delete = "DELETE FROM Produits"
			+ " WHERE nomProduit=?";
		try {
			setStatement(connexion.prepareStatement(delete));
			statement.setString(1,nomProduit);
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return getResultSet() != null;	
	}
	

	@Override
	public I_Produit find(String nomP){
		String find = "SELECT * FROM Produits"
			+ " WHERE nomProduit=?";
		I_Produit out = null;
		
		try {
			setStatement(connexion.prepareStatement(find));
			statement.setString(1, nomP);
			
			setResultSet(statement.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String nom = null;
		int qte = 0;
		double px = 0;
		
		try {
			while(getResultSet().next()){
				nom = getResultSet().getString("nomProduit");
				qte = getResultSet().getInt("quantiteProduit");
				px  = getResultSet().getDouble("prixProduit");
				out = new Produit(nom,px,qte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
	
	@Override
	public I_Catalogue findAll(){
		String all = "SELECT * FROM PRODUITS";
		I_Catalogue catal = new Catalogue();
		
		try {
			setStatement(connexion.prepareStatement(all));
			setResultSet(statement.executeQuery());
			
			while(getResultSet().next()){
				String nom	= getResultSet().getString("nomProduit");
				int qte 	= getResultSet().getInt("quantiteProduit");
				double px 	= getResultSet().getDouble("prixProduit");
				catal.addProduit(nom, px, qte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return catal;
	}	
	
	private void setStatement(PreparedStatement statement) {
		ProduitDAOrelationnel.statement = statement;
	}

	private ResultSet getResultSet() {
		return resultSet;
	}

	private void setResultSet(ResultSet rs) {
		ProduitDAOrelationnel.resultSet = rs;
	}

	@Override
	public void deconnect() {
		try {
			getResultSet().close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		

}
