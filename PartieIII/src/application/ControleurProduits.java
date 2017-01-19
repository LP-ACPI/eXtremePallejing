package application;

import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurProduits {

	private static I_Catalogue produits;

	public ControleurProduits(I_Catalogue prods) {
		produits = prods;
	}

	public static boolean XPAjouterProduit(I_Produit p){
		return produits.addProduit(p);
	}
	
	public static boolean XPEnleverProduit(String nomProduit){
		return produits.removeProduit(nomProduit);
	}
	
	public static String[] listeNomsProduits(){
		return produits.getNomProduits();
	}

	public static I_Catalogue getProduits() {
		return produits;
	}

	public static void setProduits(I_Catalogue produits) {
		ControleurProduits.produits = produits;
	}
}
