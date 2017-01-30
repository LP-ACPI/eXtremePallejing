package Application;

import DAO.ConnexionDAO;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.Produit;
import Presentation.FenetrePrincipale;

public class ControleurFrontal {

	private static ControleurFrontal instance;
	private ControleurStock controlStock;
	private ControleurProduits controlProduits;
	private ControleurAffichageStock controlAfficheStock;
	
	public ControleurFrontal(){
		I_Catalogue catalogue = new Catalogue();
		setControlAfficheStock(new ControleurAffichageStock(catalogue));
		setControlStock(new ControleurStock(catalogue));
		setControlProduits(new ControleurProduits(catalogue));
		new FenetrePrincipale(this);
	}
	
	public synchronized static ControleurFrontal getInstance(){
		if(instance == null){
			instance = new ControleurFrontal();
		}
		return instance;
	}
	
	public void setControlAfficheStock(ControleurAffichageStock controlAfficheStock) {
		this.controlAfficheStock = controlAfficheStock;
	}

	public void setControlStock(ControleurStock controlStock) {
		this.controlStock = controlStock;
	}

	public void setControlProduits(ControleurProduits controlProduits) {
		this.controlProduits = controlProduits;
	}

	public boolean approvisionnerStock(String nomProduit, int quantite) {
		return this.controlStock.approvisionnerStock(nomProduit, quantite);
	}
	
	public boolean liquiderStock(String nomProduit, int quantite) {
		return this.controlStock.liquiderStock(nomProduit, quantite);
	}

	public boolean enleverProduit(String nomProduit) {
		return this.controlProduits.enleverProduit(nomProduit);
	}

	public boolean ajouterProduit(String nomProduit, int quantite, double prixHT) {
		return this.controlProduits.ajouterProduit(new Produit(nomProduit,prixHT,quantite));
	}
	
	public String listerContenuStock(){
		return this.controlAfficheStock.AfficherCatalogue();
	}

	public void quit(){
		try {
			ConnexionDAO.getInstance().closeConnexion();
		} catch (NullPointerException e) {}
	}
	
	public static void main(String[] args) {
		ControleurFrontal.getInstance();
	}

}



