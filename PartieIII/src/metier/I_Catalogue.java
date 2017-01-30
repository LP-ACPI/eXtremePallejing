package metier;
import java.util.List;

import dao.DAOException;

public interface I_Catalogue {

	public abstract boolean addProduit(I_Produit produit) throws DAOException;
	public abstract boolean addProduit(String nom, double prix, int qte) throws DAOException;
	public abstract int addProduits(List<I_Produit> l);
	public abstract boolean removeProduit(String nom) throws DAOException;
	public abstract boolean acheterStock(String nomProduit, int qteAchetee) throws DAOException;
	public abstract boolean vendreStock(String nomProduit, int qteVendue) throws DAOException;
	public abstract String[] getNomProduits();
	public abstract double getMontantTotalTTC();
	public abstract String toString();
	public abstract String getNom();

	void persist() throws DAOException;
	public abstract void clear() throws DAOException;
}