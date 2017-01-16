package Application;

import DAO.ConnexionDAO;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Presentation.FenetrePrincipale;

public class FrontController {

	private static FrontController instance;
	
	public FrontController(){
		I_Catalogue catalogue = new Catalogue();
		new XPControlAfficheStock(catalogue);
		new XPControlStock(catalogue);
		new XPControlProduits(catalogue);	
		new FenetrePrincipale();
	}
	
	public synchronized static FrontController getInstance(){
		if(instance == null){
			instance = new FrontController();
		}
		return instance;
	}
	
	public static void quit(){
		try {
			ConnexionDAO.getInstance().closeConnexion();
		} catch (NullPointerException e) {}
	}

	public static void main(String[] args) {
		FrontController.getInstance();
	}

}



