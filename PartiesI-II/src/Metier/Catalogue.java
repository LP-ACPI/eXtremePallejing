package Metier;

import java.util.Arrays;
import java.util.List;

import DAO.I_ProduitDAO;
import Fabrique.*;


public class Catalogue implements I_Catalogue {

	private List<I_Produit> lesProduits;
	private static I_ProduitDAO produitDAO = FabriqueDAOXML.createProduitDAO();
	
	public Catalogue() {
		super();
		this.lesProduits = produitDAO.readAll();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(accepterProduit(produit))
			return lesProduits.add(produit) 
					&& produitDAO.create(produit);
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {		
		I_Produit produit = new Produit(nom, prix, qte);
		if(accepterProduit(produit))
			return lesProduits.add(produit)
					&& produitDAO.create(produit);			 
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> listP) {
		int out = 0;
		
		if(listP == null)
			return out;
		
		for(I_Produit p: listP){
			if(accepterProduit(p)) {
				out += lesProduits.add(p)
						&& produitDAO.create(p) 
							? 1 : 0;
			}
		}
		return out;
	}

	@Override
	public boolean removeProduit(String nomProduit) {
		I_Produit produit = getProduitParNom(nomProduit);
		if(!(lesProduits == null) && produit != null){			
			return lesProduits.remove(produit) 
					&& produitDAO.delete(produit);
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		I_Produit produit = getProduitParNom(nomProduit);
		if(!(lesProduits == null) && produit != null){
			return produit.ajouter(qteAchetee);
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		I_Produit produit = getProduitParNom(nomProduit);
		if(!(lesProduits == null) && produit != null){
			return produit.enlever(qteVendue);
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
			ok &= !hasProductNom(produit.getNom().trim());
		}
		return ok;
	}
		
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
		produitDAO.deleteAll();

	}

}
