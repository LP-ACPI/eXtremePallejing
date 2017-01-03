package Application;


import java.sql.SQLException;

import DAO.I_ProduitDAO;
import DAO.ProduitDAOsql;
import Metier.I_Catalogue;
import Presentation.FenetrePrincipale;

public class FrontController {

	private static FrontController instance;
	
	private static I_ProduitDAO PDAO;
	private static I_Catalogue produits;
	private static XPControlStock controlStock;
	private static XPControlProduits controlProduit;
	private static XPControlAfficheStock affStock;
	
	public FrontController(){

		String driver 	= "oracle.jdbc.driver.OracleDriver";
		String url 		= "jdbc:oracle:thin:@162.38.222.149:1521:iut";
		String login 	= "necesanym";
		String mdp 		= "Neces#9A";
		
		try {
			setPDAO(new ProduitDAOsql(driver,url,login,mdp));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProduits(getPDAO().findAll());		
		setAffStock(new XPControlAfficheStock(getProduits()));
		setControlStock(new XPControlStock(getProduits()));
		setControlProduit(new XPControlProduits(getProduits()));
		FrontController.instance = this;
		
		new FenetrePrincipale();
	}
	
	public synchronized static FrontController getInstance(){
		if(instance == null){
			instance = new FrontController();
		}
		return instance;
	}
	
	public static I_Catalogue getProduits() {
		return produits;
	}

	public static void setProduits(I_Catalogue produits) {
		FrontController.produits = produits;
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
		new FrontController();
	}

}



