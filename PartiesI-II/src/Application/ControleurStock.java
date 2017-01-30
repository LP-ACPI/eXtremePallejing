package Application;

import Metier.I_Catalogue;

public class ControleurStock {

	private static I_Catalogue stock;

	public ControleurStock(I_Catalogue catalogue) {
		stock = catalogue;
	}
	
	public I_Catalogue getCatalogue(){
		return stock;
	}
	
	public void setCatalogue(I_Catalogue catalogue){
		stock = catalogue;
	}
	
	public boolean approvisionnerStock(String nomProduit, int quantite){
		return stock.acheterStock(nomProduit, quantite);
	}
	
	public boolean liquiderStock(String nomProduit, int quantite){
		return stock.vendreStock(nomProduit, quantite);
	}
	
	public static String[] listeNomsProduits(){
		return stock.getNomProduits();
	}

}
