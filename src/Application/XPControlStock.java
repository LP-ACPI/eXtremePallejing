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
	
	public static Boolean XPApprovisionnerStock(String nomProduit, int qte){
		Boolean out = stock.acheterStock(nomProduit, qte);
		out &= FrontController.getPDAO().update(stock.getProduitByName(nomProduit));
		return out;
	}
	
	public static Boolean XPLiquiderStock(String nomProduit, int qte){
		Boolean out = stock.vendreStock(nomProduit, qte);
		out &= FrontController.getPDAO().update(stock.getProduitByName(nomProduit));
		return out;
	}
	

	public static String[] listeNomsProduits(){
		return stock.getNomProduits();
	}
	

	

}
