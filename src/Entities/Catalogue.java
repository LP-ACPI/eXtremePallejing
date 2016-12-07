package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Catalogue implements I_Catalogue{

	private List<I_Produit> lesProduits;
	
	public Catalogue() {
		super();
		this.lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(produit == null 
				|| !okNumValues(produit)
				|| lesProduits.contains(produit)
				|| hasProductNom(produit.getNom())) 
			return false;

		if(lesProduits.add(produit))
			return true;
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {		
		Produit p = new Produit(nom, prix, qte);
		
		if(lesProduits.contains(p) 
				|| hasProductNom(nom.trim())
				|| !okNumValues(p))
			return false;
		
		if(lesProduits.add(p))
			return true;
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		if(l == null) 
			return 0;
		
		int out = 0;
		for(I_Produit p: l){
			if(okNumValues(p) 
					&& !lesProduits.contains(p)
					&& !hasProductNom(p.getNom())) {
				lesProduits.add(p);
				out++;
			}
		}
		return out;
	}

	@Override
	public boolean removeProduit(String nom) {
		boolean out = false;
		for(I_Produit p: lesProduits){
			if(p.getNom() == nom)
				if(lesProduits.remove(p)){
					out = true;
					break;
				}
		}
		return out;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if(qteAchetee <= 0) 
			return false;
		
		boolean out = false;
		for(I_Produit p: lesProduits){
			if(p.getNom() == nomProduit)
				if(p.ajouter(qteAchetee)){
					out = true;
					break;
				}
		}
		return out;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if(qteVendue <= 0) 
			return false;
		boolean out = false;
		for(I_Produit p: lesProduits){
			if(p.getNom() == nomProduit)
				if(p.enlever(qteVendue))
					out = true;
		}
		return out;
	}

	@Override
	public String[] getNomProduits() {
		String out[] = new String[lesProduits.size()];
		int i = 0;
		
		alphaSort();
		
		for(I_Produit p: lesProduits){
			out[i++] = p.getNom();
		}

		return out;
	}

	@Override
	public double getMontantTotalTTC() {
		double outTotal = 0;
		for(I_Produit p:lesProduits){
			outTotal += p.getPrixStockTTC();
		}
		return Math.ceil(outTotal*100) / 100;
	}
	
	public boolean okNumValues(I_Produit p){
		return (p.getPrixUnitaireHT()>0 && p.getQuantite()>=0);
	}
	
	public boolean hasProductNom(String nomProduit){
		boolean out = false;
		
		for(I_Produit p : lesProduits){
			if(p.getNom().equals(nomProduit)){
				out = true;
				break;
			}
		}
		return out;
	}
	
	public void alphaSort(){
		if (lesProduits.size() > 0) {
		  Collections.sort(lesProduits, new Comparator<I_Produit>() {
		      @Override
		      public int compare(final I_Produit object1, final I_Produit object2) {
		          return object1.getNom().compareTo(object2.getNom());
		      }
		  });
		}
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
