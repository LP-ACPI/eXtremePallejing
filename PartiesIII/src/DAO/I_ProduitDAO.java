package DAO;

import java.util.List;

import Metier.I_Catalogue;
import Metier.I_Produit;

public interface I_ProduitDAO {

	public abstract boolean create(I_Produit p);

	public abstract boolean update(I_Produit p);

	public abstract boolean delete(I_Produit p);

	public abstract I_Produit read(String nomProduit);

	public abstract List<I_Produit> readAll();
	
	public abstract void setCatalogue(I_Catalogue catalog);

	public abstract int catalogsProductCount();

}