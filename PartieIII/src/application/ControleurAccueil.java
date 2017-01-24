package application;

import java.util.List;

import application.observateurs.CatalogueInfosObservables;
import dao.ConnexionDAO;
import dao.DAOException;
import dao.catalogue.I_CatalogueDAO;
import dao.fabrique.*;
import dao.produit.I_ProduitDAO;
import metier.Catalogue;
import metier.I_Catalogue;
import presentation.FenetreAccueil;

public class ControleurAccueil {

	private static ControleurAccueil instance;
	private static CatalogueInfosObservables cataloguesObserves;
	private static I_CatalogueDAO catalogDAO;
	private static I_ProduitDAO   produitDAO;
	
	public ControleurAccueil() throws DAOException{
		catalogDAO = FabriqueAbstraiteDAO.getInstance().createCatalogueDAO();
		produitDAO = FabriqueAbstraiteDAO.getInstance().createProduitDAO();
		
		List<I_Catalogue> listeCatalogs = catalogDAO.readAll();
		int[] nombresProduits   = new int[listeCatalogs.size()];
		String[] nomsCatalogues = new String[listeCatalogs.size()];
		for(int i=0;i<listeCatalogs.size();i++){
			I_Catalogue caTemp = listeCatalogs.get(i);
			produitDAO.setCatalogue(caTemp);
			nomsCatalogues[i]  = caTemp.getNom();
			nombresProduits[i] = produitDAO.catalogsProductCount();
		}
		cataloguesObserves = new CatalogueInfosObservables(nomsCatalogues,nombresProduits);
		cataloguesObserves.attacher(new FenetreAccueil());
	}
	
	public synchronized static ControleurAccueil getInstance() throws DAOException{
		if(instance == null){
			instance = new ControleurAccueil();
		}
		return instance;
	}

	public static String[] listerCatalogues(){
		return cataloguesObserves.getNomsCatalogues();
	}
	
	public static String[] listerDetailsCatalogues(){
		return cataloguesObserves.getDetailsCatalogues();
	}
	
	public static int enumererCatalogues(){
		return cataloguesObserves.getNombreCatalogues();
	}
	
	public static boolean ajouterCatalogue(String nomCatalogue) throws DAOException{
		if(cataloguesObserves.ajouterCatalogue(nomCatalogue)){
			new Catalogue(nomCatalogue).persist();
			return true;
		} return false;
	}
	
	public static boolean supprimerCatalogue(String nomCatalogue) throws DAOException{
		if(cataloguesObserves.supprimerCatalogue(nomCatalogue)){
			new Catalogue(nomCatalogue).clear();
			return true;
		} return false;
	}

	public static void selectionnerCatalogue(String nomCatalogue) throws DAOException{
		I_Catalogue catalogue = catalogDAO.read(nomCatalogue);
		new ControleurAfficheStock(catalogue);
		new ControleurProduits(catalogue);
		new ControleurStock(catalogue);
	}

	
	public static void quit(){
		try {
			ConnexionDAO.getInstance().closeConnexion();
		} catch (NullPointerException e) {
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FabriqueAbstraiteDAO.setInstance(new FabriqueDAOMongoDB());
		
		try {
			ControleurAccueil.getInstance();
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
	}

}



