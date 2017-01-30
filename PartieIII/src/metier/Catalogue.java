package metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.fabrique.FabriqueAbstraiteDAO;
import dao.DAOException;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;


public class Catalogue implements I_Catalogue {
	
	private static I_ProduitDAO   produitDAO;
	private static I_CatalogueDAO catalogueDAO;
	
	private List<I_Produit> lesProduits;
	private String nomCatalogue;
	
	public Catalogue() throws DAOException {
		super();
		setDAO(FabriqueAbstraiteDAO.getInstance());
		this.lesProduits = new ArrayList<I_Produit>();
	}
		
	public Catalogue(String nomCatalogue) throws DAOException {
		this();
		this.nomCatalogue = nomCatalogue.replaceAll("[\\t]", " ").trim();
		produitDAO.setCatalogue(this);
	}

	@Override
	public boolean addProduit(I_Produit produit) throws DAOException {
		if(produitPersistable(produit))
			 if(lesProduits.add(produit))
				 return produitDAO.create(produit);
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) throws DAOException {		
		return addProduit(new Produit(nom, prix, qte));
	}

	@Override
	public int addProduits(List<I_Produit> listP) {
		int out = 0;
		
		if(listP == null)
			return out;
	
		for(I_Produit p: listP)
			if(produitPersistable(p)) 
				out += lesProduits.add(p) ? 1 : 0;
		return out;
	}

	@Override
	public boolean removeProduit(String nomProduit) throws DAOException {
		I_Produit produit = getProduitParNom(nomProduit);
		if(produitValide(produit))
			if(lesProduits.remove(produit))
				return produitDAO.delete(produit);
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) throws DAOException {
		I_Produit produit = getProduitParNom(nomProduit);
		if(produitValide(produit))
			return produit.ajouter(qteAchetee);
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) throws DAOException {
		I_Produit produit = getProduitParNom(nomProduit);
		if(produitValide(produit))
			return produit.enlever(qteVendue);
		return false;
	}

	@Override
	public String[] getNomProduits() {
		String outNomsProduits[] = new String[lesProduits.size()];
		int i = 0;
		
		for(I_Produit p: lesProduits)
			outNomsProduits[i++] = p.getNom();
		
		Arrays.sort(outNomsProduits);
		return outNomsProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double outTotal = 0;
		for(I_Produit p:lesProduits)
			outTotal += p.getPrixStockTTC();
		
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
		
		for(I_Produit p:lesProduits)
			outPut += p + "\n";
		
		outPut += "\nMontant total TTC du stock : "+ String.format("%.2f",getMontantTotalTTC() ) + " €";
		
		return outPut;
	}

	@Override
	public void persist() throws DAOException{
		catalogueDAO.create(this);
	}
	
	@Override
	public void clear() throws DAOException {
		lesProduits.clear();
		catalogueDAO.delete(this);
	}

	public String getNom() {
		return nomCatalogue;
	}

	public void setNomCatalogue(String nomCatalogue) {
		this.nomCatalogue = nomCatalogue;
	}

	private void setDAO(FabriqueAbstraiteDAO fabrique) throws DAOException {
		produitDAO = fabrique.createProduitDAO();
		catalogueDAO = fabrique.createCatalogueDAO();
	}
	

}
