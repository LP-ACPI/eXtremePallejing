package Application;

import Metier.I_Catalogue;
import Metier.Produit;

public class XPControlProduits {

	private static I_Catalogue produits;

	public XPControlProduits(I_Catalogue catalogue) {
		produits = catalogue;
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
