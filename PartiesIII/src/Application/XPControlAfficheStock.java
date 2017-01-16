package Application;

import Metier.I_Catalogue;

public class XPControlAfficheStock {
	
	private static I_Catalogue catalog;

	public XPControlAfficheStock(I_Catalogue catal){
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
		XPControlAfficheStock.catalog = catalog;
	}

	
	
}
