package Entities;

import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue{

	private List<I_Produit> lesProduits;
	
	public Catalogue() {
		super();
		this.lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(lesProduits.add(produit))
			return true;
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		
		Produit p = new Produit(nom, prix, qte);
		if(lesProduits.add(p))
			return true;
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int out = 0;
		for(I_Produit p: l){
			lesProduits.add(p);
			out++;
		}
		return out;
	}

	@Override
	public boolean removeProduit(String nom) {
		boolean out = false;
		for(I_Produit p: lesProduits){
			if(p.getNom() == nom)
				if(lesProduits.remove(p))
					out = true;
		}
		return out;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getNomProduits() {
		String out[] = new String[lesProduits.size()];
		int i = 0;
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
		return outTotal;
	}
	
	

	@Override
	public String toString() {
		String outPut = "";
		
		for(I_Produit p:lesProduits){
			outPut += p + "\n";
		}
		
		outPut += "\nMontant total TTC du stock: "+getMontantTotalTTC() + "€";
		
		return outPut;
	}

	@Override
	public void clear() {
		lesProduits.clear();
		
	}

}
