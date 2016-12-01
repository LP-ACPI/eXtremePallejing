package Controller;

import Entities.Catalogue;

public class XPControllerAfficheStock {
	
	private Catalogue catalog;

	public XPControllerAfficheStock(Catalogue catal){
		this.catalog = catal;
	}

	public String AfficherCatalogue(){
		return catalog.toString();
	}
	
	
	
}
