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
	
	public static Boolean XPApprovisionnerStock(String nomProduit, int quantite){
		if(stock.acheterStock(nomProduit, quantite))
			return FrontController.getPDAO()
					.update(stock.getProduitParNom(nomProduit));
		return false;
	}
	
	public static Boolean XPLiquiderStock(String nomProduit, int quantite){
		if(stock.vendreStock(nomProduit, quantite))
			return FrontController.getPDAO()
					.update(stock.getProduitParNom(nomProduit));
		return false;
	}
	
	public static String[] listeNomsProduits(){
		return stock.getNomProduits();
	}

}
