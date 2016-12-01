package Controller;

import Entities.Catalogue;

public class XPControlStock {

	private Catalogue stock;

	public XPControlStock() {
		super();
		this.stock = new Catalogue();
	}
	
	public Catalogue getCatalogue(){
		return this.stock;
	}
	

}
