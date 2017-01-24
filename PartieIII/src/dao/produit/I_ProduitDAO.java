package dao.produit;

import java.util.List;

import dao.DAOException;
import metier.I_Catalogue;
import metier.I_Produit;

public interface I_ProduitDAO {

	public abstract boolean create(I_Produit p) throws DAOException;

	public abstract boolean update(I_Produit p) throws DAOException;

	public abstract boolean delete(I_Produit p) throws DAOException;

	public abstract I_Produit read(String nomProduit) throws DAOException;

	public abstract List<I_Produit> readAll() throws DAOException;
	
	public abstract void setCatalogue(I_Catalogue catalog);
	
	public abstract int catalogsProductCount() throws DAOException;

}