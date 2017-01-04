package Metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Application.FrontController;

public class Catalogue implements I_Catalogue{

	private List<I_Produit> lesProduits;
	
	public Catalogue() {
		super();
		this.lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(accepterProduit(produit))
			return lesProduits.add(produit);
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {		
		Produit p = new Produit(nom, prix, qte);
		if(accepterProduit(p))
			return lesProduits.add(p);				 
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> listP) {
		int out = 0;
		
		if(listP == null)
			return out;
		
		for(I_Produit p: listP){
			if(accepterProduit(p)) {
				lesProduits.add(p);
				out++;
			}
		}
		return out;
	}

	@Override
	public boolean removeProduit(String nomProduit) {		
		if(!(lesProduits == null) && hasProductNom(nomProduit))
			return lesProduits.remove(getProduitParNom(nomProduit));
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if(!(lesProduits == null) && hasProductNom(nomProduit)){
			I_Produit produit = getProduitParNom(nomProduit);
			if(produit.ajouter(qteAchetee))
				return FrontController.getPDAO() == null ? 
						true : FrontController.getPDAO().update(produit);
		}		
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if(!(lesProduits == null) && hasProductNom(nomProduit)){
			I_Produit produit = getProduitParNom(nomProduit);
			if(produit.enlever(qteVendue))
				return FrontController.getPDAO() == null ? 
						true : FrontController.getPDAO().update(produit);
		}		
		return false;
	}

	@Override
	public String[] getNomProduits() {
		String outNomsProduits[] = new String[lesProduits.size()];
		int i = 0;
		
		for(I_Produit p: lesProduits){
			outNomsProduits[i++] = p.getNom();
		}
		
		Arrays.sort(outNomsProduits);

		return outNomsProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double outTotal = 0;
		for(I_Produit p:lesProduits){
			outTotal += p.getPrixStockTTC();
		}
		return (double)Math.round(outTotal*100) / 100;
	}
	
	private Boolean accepterProduit(I_Produit produit){
		Boolean ok = produit != null;
		if(ok){
			ok &= produit.getPrixUnitaireHT()>0;
			ok &= produit.getQuantite()>=0;
			ok &= !lesProduits.contains(produit);
			ok &= !hasProductNom(produit.getNom().trim());
		}
		return ok;
	}
	
//	private Boolean acceptAjoutPDAO(I_Produit produit){
//		Boolean ok = FrontController.getPDAO() != null;
//		if(ok)
//			ok &= FrontController.getPDAO().find(produit.getNom()) == null;
//		return ok;
//	}
	
	private Boolean hasProductNom(String nomProduit){
		return getProduitParNom(nomProduit) != null;
	}
	
	private I_Produit getProduitParNom(String nomProduit){
		I_Produit outProduit = null;
		
		for(I_Produit p : lesProduits){
			if(p.getNom().equals(nomProduit)){
				outProduit = p;
				break;
			}
		}
		return outProduit;
	}

	@Override
	public String toString() {
		String outPut = "";
		
		for(I_Produit p:lesProduits){
			outPut += p + "\n";
		}
		
		outPut += "\nMontant total TTC du stock : "+ String.format("%.2f",getMontantTotalTTC() ) + " €";
		
		return outPut;
	}

	@Override
	public void clear() {
		lesProduits.clear();
	}

}
