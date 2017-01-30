package Metier;

import DAO.I_ProduitDAO;
import DAO.Fabrique.*;

public class Produit implements I_Produit {

	private static I_ProduitDAO ProduitDAO = FabriqueDAORelationnel.createProduitDAO();
	
	int quantiteStock;
	String nom;
	double prixUnitaireHT;
	static double tauxTVA = 0.2;
	
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
		super();
		this.nom = nom.replaceAll("[\\t]", " ").trim();
		this.prixUnitaireHT = prixUnitaireHT;
		this.quantiteStock = quantiteStock;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if(qteAchetee > 0){
			quantiteStock += qteAchetee;
			return ProduitDAO.update(this);
		}
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {		
		int nouvQte = quantiteStock-qteVendue;
		if(nouvQte >=0 && qteVendue > 0){
			quantiteStock = nouvQte;
			return ProduitDAO.update(this);
		}
		return false;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return (prixUnitaireHT + prixUnitaireHT * tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		double pxTotal = getPrixUnitaireTTC() * quantiteStock;
		return pxTotal;
	}
	
	@Override
	public String toString() {
		return nom +" - "
				+ "prix HT : " + String.format("%.2f", prixUnitaireHT) + " € - "
				+ "prix TTC : "	+ String.format("%.2f",getPrixUnitaireTTC()) +" € - "
				+ "quantité en stock : " + quantiteStock;
	}

}
