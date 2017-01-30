package Application;

import Metier.I_Catalogue;

public class ControleurAffichageStock {
	
	private static I_Catalogue catalog;
	
	public ControleurAffichageStock(I_Catalogue catal){
		catalog = catal;
	}

	public String AfficherCatalogue(){
		return catalog.toString();
	}
	
	public static I_Catalogue getCatalog() {
		return catalog;
	}

	public static void setCatalog(I_Catalogue catalog) {
		ControleurAffichageStock.catalog = catalog;
	}
	
}
