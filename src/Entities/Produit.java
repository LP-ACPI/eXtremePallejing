package Entities;

public class Produit implements I_Produit {

	int quantiteStock;
	String nom;
	double prixUnitaireHT;
	static double tauxTVA = 0.2;
	
	
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
		super();
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
		this.quantiteStock = quantiteStock;
	}


	@Override
	public boolean ajouter(int qteAchetee) {
		quantiteStock += qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		int nouvQte = quantiteStock-qteVendue;
		if(nouvQte >=0 ){
			quantiteStock = nouvQte;
			return true;
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
		return prixUnitaireHT * tauxTVA;
	}

	@Override
	public double getPrixStockTTC() {
		return getPrixUnitaireTTC() * quantiteStock;
	}

	@Override
	public String toString() {
		return nom +"\t"
				+ "prixHT: " + prixUnitaireHT + "€\t"
				+ "prixTTC: "	+ getPrixUnitaireTTC() +"€\t"
				+ "quantité en stock: " + quantiteStock;
	}

}
