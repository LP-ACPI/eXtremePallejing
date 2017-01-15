package Application;

import DAO.ConnexionDAO;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Presentation.FenetrePrincipale;

public class FrontController {

	private static FrontController instance;
	private static XPControlStock controlStock;
	private static XPControlProduits controlProduit;
	private static XPControlAfficheStock affStock;
	
	public FrontController(){		
		I_Catalogue catalogue = new Catalogue();
		setAffStock(new XPControlAfficheStock(catalogue));
		setControlStock(new XPControlStock(catalogue));
		setControlProduit(new XPControlProduits(catalogue));	
		new FenetrePrincipale();
	}
	
	public synchronized static FrontController getInstance(){
		if(instance == null){
			instance = new FrontController();
		}
		return instance;
	}

	public static void setControlStock(XPControlStock controlStock) {
		FrontController.controlStock = controlStock;
	}

	public static void setControlProduit(XPControlProduits controlProduit) {
		FrontController.controlProduit = controlProduit;
	}

	public static void setAffStock(XPControlAfficheStock affStock) {
		FrontController.affStock = affStock;
	}
	
	public static XPControlStock getControlStock() {
		return controlStock;
	}

	public static XPControlProduits getControlProduit() {
		return controlProduit;
	}

	public static XPControlAfficheStock getAffStock() {
		return affStock;
	}
	
	public static void quit(){
		ConnexionDAO.closeConnexion();
	}

	public static void main(String[] args) {
		FrontController.getInstance();
	}

}



