package metier;

import dao.DAOException;

public interface I_Produit {

	public abstract boolean ajouter(int qteAchetee) throws DAOException;
	public abstract boolean enlever(int qteVendue) throws DAOException;
	public abstract String getNom();
	public abstract int getQuantite();
	public abstract double getPrixUnitaireHT();
	public abstract double getPrixUnitaireTTC();
	public abstract double getPrixStockTTC();
	public abstract String toString();
}