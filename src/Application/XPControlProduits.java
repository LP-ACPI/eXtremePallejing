package Application;

import Metier.I_Catalogue;
import Metier.I_Produit;

public class XPControlProduits {

	private static I_Catalogue produits;

	public XPControlProduits(I_Catalogue prods) {
		produits = prods;
	}
	
	public static Boolean XPAjouterProduit(I_Produit p){
		Boolean out = FrontController.getPDAO().create(p);
		return out & produits.addProduit(p);
	}
	
	public static Boolean XPEnleverProduit(String nomProduit){
		Boolean out = FrontController.getPDAO().delete(nomProduit);
		return out & produits.removeProduit(nomProduit);
	}
	
	public static String[] listeNomsProduits(){
		return produits.getNomProduits();
	}
}
