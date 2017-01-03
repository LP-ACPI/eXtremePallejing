package Application;

import Metier.I_Catalogue;
import Metier.I_Produit;

public class XPControlProduits {

	private static I_Catalogue produits;

	public XPControlProduits(I_Catalogue prods) {
		produits = prods;
	}

	public static Boolean XPAjouterProduit(I_Produit p){
		if(produits.addProduit(p))
			return FrontController.getPDAO().create(p);
		return false;
	}
	
	public static Boolean XPEnleverProduit(String nomProduit){
		if(produits.removeProduit(nomProduit))
			return FrontController.getPDAO().delete(nomProduit);
		return false;
	}
	
	public static String[] listeNomsProduits(){
		return produits.getNomProduits();
	}
	

	public static I_Catalogue getProduits() {
		return produits;
	}

	public static void setProduits(I_Catalogue produits) {
		XPControlProduits.produits = produits;
	}
}
