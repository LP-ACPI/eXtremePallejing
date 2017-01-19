package application;

import metier.I_Catalogue;

public class ControleurAfficheStock {
	
	private static I_Catalogue catalog;

	public ControleurAfficheStock(I_Catalogue catal){
		catalog = catal;
	}

	public static String AfficherCatalogue(){
		return catalog.toString();
	}
	
	public static I_Catalogue getCatalog() {
		return catalog;
	}
	
	public static String getNomCatalog() {
		return catalog.getNom();
	}

	public static void setCatalog(I_Catalogue catalog) {
		ControleurAfficheStock.catalog = catalog;
	}

	
	
}
