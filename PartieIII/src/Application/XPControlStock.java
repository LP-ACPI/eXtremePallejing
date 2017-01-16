package Application;

import Metier.I_Catalogue;

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
	
	public static boolean XPApprovisionnerStock(String nomProduit, int quantite){
		return stock.acheterStock(nomProduit, quantite);
	}
	
	public static boolean XPLiquiderStock(String nomProduit, int quantite){
		return stock.vendreStock(nomProduit, quantite);
	}
	
	public static String[] listeNomsProduits(){
		return stock.getNomProduits();
	}

}
