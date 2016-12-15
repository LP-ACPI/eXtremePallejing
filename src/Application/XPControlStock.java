package Application;

import Metier.Catalogue;
import Metier.I_Catalogue;

public class XPControlStock {

	private static I_Catalogue stock;

	public XPControlStock() {
		stock = new Catalogue();
	}
		
	public I_Catalogue getCatalogue(){
		return stock;
	}
	
	public Boolean XPApprovisionnerStock(String nomProduit, int qte){
		return stock.acheterStock(nomProduit, qte);
	}
	
	public Boolean XPLiquiderStock(String nomProduit, int qte){
		return stock.vendreStock(nomProduit, qte);
	}
	

	public String[] listeNomsProduits(){
		return stock.getNomProduits();
	}
	

}
