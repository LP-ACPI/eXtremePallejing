package Application;

import Metier.Catalogue;
import Metier.Produit;

public class XPControlProduits {

	private Catalogue produits;

	public XPControlProduits(Catalogue produits) {
		this.produits = produits;
	}
	
	public Boolean XPAjouterProduit(Produit p){
		return produits.addProduit(p);
	}
	
	public Boolean XPEnleverProduit(String nomProduit){
		return produits.removeProduit(nomProduit);
	}
	
	public String[] listeNomsProduits(){
		return produits.getNomProduits();
	}
	
	

}
