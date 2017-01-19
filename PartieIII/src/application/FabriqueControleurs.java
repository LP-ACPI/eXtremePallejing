package application;

import metier.I_Catalogue;

public class FabriqueControleurs {

	private static FabriqueControleurs instance;

	private FabriqueControleurs(){}
	
	public synchronized static FabriqueControleurs getInstance(){
		if(instance == null)
			instance = new FabriqueControleurs();
		return instance;
	}
	
	public static ControleurAccueil fabriquerControleurAccueil(){
		return new ControleurAccueil();
	}
	
	public static ControleurAfficheStock fabriquerControleurAffStock(I_Catalogue catalog){
		return new ControleurAfficheStock(catalog);
	}

	public static ControleurStock fabriquerControleurVariationStock(I_Catalogue catalog){
		return new ControleurStock(catalog);
	}

	public static ControleurProduits fabriquerControleurProduits(I_Catalogue catalog){
		return new ControleurProduits(catalog);
	}
}
