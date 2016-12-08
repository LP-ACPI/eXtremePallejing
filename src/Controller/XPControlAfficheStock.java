package Controller;

import Entities.Catalogue;

public class XPControlAfficheStock {
	
	private Catalogue catalog;

	public XPControlAfficheStock(Catalogue catal){
		this.catalog = catal;
	}

	public String AfficherCatalogue(){
		return catalog.toString();
	}
	
	
	
}
