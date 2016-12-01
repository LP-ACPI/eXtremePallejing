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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int out = 0;

		return out;
	}

	@Override
	public boolean removeProduit(String nom) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMontantTotalTTC() {
		double outTotal = 0;
		for(I_Produit produit:lesProduits){
			outTotal += produit.getPrixStockTTC();
		}
		return outTotal;
	}
	
	

	@Override
	public String toString() {
		String outPut = "";
		
		for(I_Produit produit:lesProduits){
			outPut += produit + "\n";
		}
		
		outPut += "\nMontant total TTC du stock: "+getMontantTotalTTC() + "€";
		
		return outPut;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
