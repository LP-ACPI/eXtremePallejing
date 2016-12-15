package DAO;

import java.sql.SQLException;

import Metier.I_Catalogue;
import Metier.I_Produit;

public interface I_ProduitDAO {

	public abstract boolean create(I_Produit p);

	public abstract boolean update(I_Produit p);

	public abstract boolean delete(I_Produit p);

	public abstract I_Produit find(String nomProduit) throws SQLException;

	public abstract I_Catalogue findAll();

}