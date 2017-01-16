package Application;

import java.util.List;

import DAO.ConnexionDAO;
import DAO.I_CatalogueDAO;
import DAO.I_ProduitDAO;
import Fabrique.FabriqueAbstraiteDAO;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Presentation.FenetreAccueil;

public class FrontController {

	private static FrontController instance;
	private static CataloguesObservables cataloguesObserves;
	private static I_CatalogueDAO catalogDAO;
	private static I_ProduitDAO   produitDAO;
	
	public FrontController(){
		catalogDAO = FabriqueAbstraiteDAO.getInstance().createCatalogueDAO();
		produitDAO = FabriqueAbstraiteDAO.getInstance().createProduitDAO();
		
		List<I_Catalogue> listeCatalogs = catalogDAO.readAll();
		int[] nombresProduits   = new int[listeCatalogs.size()];
		String[] nomsCatalogues = new String[listeCatalogs.size()];
		for(int i=0;i<listeCatalogs.size();i++){
			I_Catalogue cTemp = listeCatalogs.get(i);
			produitDAO.setCatalogue(cTemp);
			nomsCatalogues[i]  = cTemp.getNom();
			nombresProduits[i] = produitDAO.getNombreDeProduitsDuCatalogue();
		}
		cataloguesObserves = new CataloguesObservables(nomsCatalogues,nombresProduits);
		cataloguesObserves.attacher(new FenetreAccueil());
	}
	
	public synchronized static FrontController getInstance(){
		if(instance == null){
			instance = new FrontController();
		}
		return instance;
	}

	public static String[] listerCatalogues(){
		return cataloguesObserves.getNomsCatalogues();
	}
	
	public static String[] listerDetailsCatalogues(){
		int nombreDeCatalogues = cataloguesObserves.getNombreCatalogues();
		String[] detailsCatalogues = new String[nombreDeCatalogues];
		String[] nomsCatalogues = cataloguesObserves.getNomsCatalogues();
		String[] nombresDeProduitParCatalogue = cataloguesObserves.getNombresProduitsCatalogues();
		
		for(int i = 0; i<nombreDeCatalogues; i++)
			detailsCatalogues[i] = nomsCatalogues[i] + " : " + nombresDeProduitParCatalogue[i] + " produits";
		return detailsCatalogues;
	}
	
	public static int nombreDeCatalogues(){
		return cataloguesObserves.getNombreCatalogues();
	}
	
	public static void ajouterCatalogue(String nomCatalogue){
		cataloguesObserves.ajouterCatalogue(nomCatalogue);
		catalogDAO.create(new Catalogue(nomCatalogue));
	}	
	public static void supprimerCatalogue(String nomCatalogue){
		I_Catalogue catalogue = new Catalogue(nomCatalogue);
		catalogue.clear();
		cataloguesObserves.supprimerCatalogue(nomCatalogue);
	}

	public static void selectionnerCatalogue(String nomCatalogue){
		I_Catalogue catalogue = catalogDAO.read(nomCatalogue);
		new XPControlAfficheStock(catalogue);
		new XPControlStock(catalogue);
		new XPControlProduits(catalogue);	
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



