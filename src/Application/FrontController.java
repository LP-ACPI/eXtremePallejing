package Application;

import DAO.I_ProduitDAO;
import Fabrique.FabriqueProduitPDAOrelationnel;
import Metier.I_Catalogue;
import Presentation.FenetrePrincipale;

public class FrontController {

	private static FrontController instance;
	
	private static I_ProduitDAO PDAO;
	private static XPControlStock controlStock;
	private static XPControlProduits controlProduit;
	private static XPControlAfficheStock affStock;
	
	public FrontController(){		
		setPDAO(FabriqueProduitPDAOrelationnel.CreateProduitPDAO());
		I_Catalogue catalogue = getPDAO().findAll();
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
	
	public static void setPDAO(I_ProduitDAO pDAO) {
		FrontController.PDAO = pDAO;
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

	public static I_ProduitDAO getPDAO() {
		return PDAO;
	}

	public static void quit(){
		getPDAO().deconnect();
	}
	

	public static void main(String[] args) {
		FrontController.getInstance();
	}

}



