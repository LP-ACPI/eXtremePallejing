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
	
	
	
}
