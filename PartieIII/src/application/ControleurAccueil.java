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
	private static I_CatalogueDAO catalogDAO = FabriqueAbstraiteDAO.getInstance().createCatalogueDAO();
	private static I_ProduitDAO   produitDAO = FabriqueAbstraiteDAO.getInstance().createProduitDAO();
	
	public ControleurAccueil(){		
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
	
	public synchronized static ControleurAccueil getInstance(){
		if(instance == null){
			instance = FabriqueControleurs.fabriquerControleurAccueil();
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
	
	public static void ajouterCatalogue(String nomCatalogue){
		cataloguesObserves.ajouterCatalogue(nomCatalogue);
		new Catalogue(nomCatalogue).persist();
	}
	
	public static void supprimerCatalogue(String nomCatalogue){
		cataloguesObserves.supprimerCatalogue(nomCatalogue);
		new Catalogue(nomCatalogue).clear();
	}

	public static void selectionnerCatalogue(String nomCatalogue){
		I_Catalogue catalogue = catalogDAO.read(nomCatalogue);
		FabriqueControleurs.fabriquerControleurAffStock(catalogue);
		FabriqueControleurs.fabriquerControleurVariationStock(catalogue);
		FabriqueControleurs.fabriquerControleurProduits(catalogue);
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
		FabriqueAbstraiteDAO.setInstance(new FabriqueDAOCassandra());
		
		ControleurAccueil.getInstance();
	}

}



