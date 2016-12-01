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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNom() {
		
		return nom;
	}

	@Override
	public int getQuantite() {
		// TODO Auto-generated method stub
		return 0;
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
		return nom 
				+ " prixHT: " + prixUnitaireHT 
				+ "€ prixTTC: "	+ getPrixUnitaireTTC()
				+ "€ quantité en stock: " + quantiteStock;
	}

}
