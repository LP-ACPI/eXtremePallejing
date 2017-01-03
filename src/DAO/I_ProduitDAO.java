package DAO;

import Metier.I_Catalogue;
import Metier.I_Produit;

public interface I_ProduitDAO {

	public abstract boolean create(I_Produit p);

	public abstract boolean update(I_Produit p);

	public abstract boolean delete(String nomProduit);

	public abstract I_Produit find(String nomProduit);

	public abstract I_Catalogue findAll();
	
	public abstract void deconnect();

}