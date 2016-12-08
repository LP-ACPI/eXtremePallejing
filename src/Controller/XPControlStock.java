package Controller;

import Entities.Catalogue;

public class XPControlStock {

	private Catalogue stock;

	public XPControlStock() {
		this.stock = new Catalogue();
	}
	
	public Catalogue getCatalogue(){
		return this.stock;
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
