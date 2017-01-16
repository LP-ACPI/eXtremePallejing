package Metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAO.I_CatalogueDAO;
import DAO.I_ProduitDAO;
import Fabrique.FabriqueAbstraiteDAO;


public class Catalogue implements I_Catalogue {
	
	private static I_ProduitDAO produitDAO = FabriqueAbstraiteDAO.getInstance().createProduitDAO();
	private static I_CatalogueDAO catalogueDAO = FabriqueAbstraiteDAO.getInstance().createCatalogueDAO();
	
	private List<I_Produit> lesProduits;
	private String nomCatalogue;
	
	public Catalogue() {
		super();
		this.lesProduits = new ArrayList<I_Produit>();
	}
		
	public Catalogue(String nomCatalogue) {
		this();
		this.nomCatalogue = nomCatalogue;
		produitDAO.setCatalogue(this);
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(produitPersistable(produit))
			 if(lesProduits.add(produit))
				 return produitDAO.create(produit);
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {		
		return addProduit(new Produit(nom, prix, qte));
	}

	@Override
	public int addProduits(List<I_Produit> listP) {
		int out = 0;
		
		if(listP == null)
			return out;
		
		for(I_Produit p: listP){
			if(produitPersistable(p)) {
				out += lesProduits.add(p) ? 1 : 0;
			}
		}
		return out;
	}

	@Override
	public boolean removeProduit(String nomProduit) {
		I_Produit produit = getProduitParNom(nomProduit);
		if(produitValide(produit))
			if(lesProduits.remove(produit))
				return produitDAO.delete(produit);
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		I_Produit produit = getProduitParNom(nomProduit);
		if(produitValide(produit)){
			return produit.ajouter(qteAchetee);
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		I_Produit produit = getProduitParNom(nomProduit);
		if(produitValide(produit)){
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
	
	private boolean produitPersistable(I_Produit produit){
		boolean ok = produitValide(produit);
		if(ok){
			ok &= produit.getPrixUnitaireHT()>0;
			ok &= produit.getQuantite()>=0;
			ok &= !hasProductNom(produit.getNom().trim());
		}
		return ok;
	}
	
	private boolean produitValide(I_Produit produit){
		return lesProduits != null && produit != null;
	}
	
	private boolean hasProductNom(String nomProduit){
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
	
	public void persist(){
		catalogueDAO.create(this);
	}
	
	@Override
	public void clear() {
		lesProduits.clear();		
		catalogueDAO.delete(this);
	}

	public String getNom() {
		return nomCatalogue;
	}

	public void setNomCatalogue(String nomCatalogue) {
		this.nomCatalogue = nomCatalogue;
	}

}