package DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Metier.Produit;
//TODO
public class  ProduitDAO{
	
	private Statement st;
	private ResultSet rs;
	private Connexion cn;
	
	public ProduitDAO(Connexion cnt){
		cn = cnt;
	}
	
	public boolean create(Produit p){
		String insert = "INSERT INTO Produits(id,nomproduit,quantite,...)"
		try {
			rs = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(Produit p){
		return false;
	}
	
	public boolean delete(Produit p){
		return false;
	}
	
	public boolean find(Produit p){
		return false;
	}
	
	public boolean findAll(Produit p){
		return false;
	}

}
