package Application;

import Metier.I_Catalogue;
import Metier.I_Produit;

public class XPControlStock {

	private static I_Catalogue stock;

	public XPControlStock(I_Catalogue catalogue) {
		stock = catalogue;
	}
	
	public I_Catalogue getCatalogue(){
		return stock;
	}
	
	public void setCatalogue(I_Catalogue catalogue){
		stock = catalogue;
	}
	
	public static Boolean XPApprovisionnerStock(String nomProduit, int quantite){
		if(stock.acheterStock(nomProduit, quantite)){
			I_Produit p = FrontController.getPDAO().find(nomProduit);
			return FrontController.getPDAO().update(p);
		}
		return false;
	}
	
	public static Boolean XPLiquiderStock(String nomProduit, int quantite){
		if(stock.vendreStock(nomProduit, quantite)){
			I_Produit p = FrontController.getPDAO().find(nomProduit);
			return FrontController.getPDAO().update(p);
		}
		return false;
	}
	
	public static String[] listeNomsProduits(){
		return stock.getNomProduits();
	}

}
