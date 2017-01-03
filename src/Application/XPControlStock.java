package Application;

import Metier.I_Catalogue;

public class XPControlStock {

	private static I_Catalogue stock;

	public XPControlStock(I_Catalogue catal) {
		stock = catal;
	}
	
	public I_Catalogue getCatalogue(){
		return stock;
	}
	
	public void setCatalogue(I_Catalogue catal){
		stock = catal;
	}
	
	public static Boolean XPApprovisionnerStock(String nomProduit, int qte){
		if(stock.acheterStock(nomProduit, qte))
			return FrontController.getPDAO().update(stock.getProduitByName(nomProduit));
		return false;
	}
	
	public static Boolean XPLiquiderStock(String nomProduit, int qte){
		if(stock.vendreStock(nomProduit, qte))
			return FrontController.getPDAO().update(stock.getProduitByName(nomProduit));
		return false;
	}
	
	public static String[] listeNomsProduits(){
		return stock.getNomProduits();
	}

}
