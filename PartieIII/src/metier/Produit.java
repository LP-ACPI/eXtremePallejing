package metier;

import dao.DAOException;
import dao.fabrique.FabriqueAbstraiteDAO;
import dao.produit.I_ProduitDAO;

public class Produit implements I_Produit {

	private static I_ProduitDAO produitDAO = FabriqueAbstraiteDAO.getInstance().createProduitDAO();
	
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
	public boolean ajouter(int qteAchetee) throws DAOException {
		if(qteAchetee > 0){
			quantiteStock += qteAchetee;
			return produitDAO.update(this);
		}
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) throws DAOException {		
		int nouvQte = quantiteStock-qteVendue;
		if(nouvQte >=0 && qteVendue > 0){
			quantiteStock = nouvQte;
			return produitDAO.update(this);
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


	@Override
	public void setDAO(FabriqueAbstraiteDAO fabrique) {
		produitDAO = fabrique.createProduitDAO();
	}

}
