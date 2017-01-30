package application;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.observateurs.CatalogueInfosObservables;
import dao.ConnexionDAO;
import dao.DAOException;
import dao.catalogue.I_CatalogueDAO;
import dao.fabrique.*;
import metier.Catalogue;
import metier.I_Catalogue;
import presentation.FenetreAccueil;

public class ControleurAccueil {

	private static ControleurAccueil instance;
	private static CatalogueInfosObservables cataloguesObserves;
	private static I_CatalogueDAO catalogDAO;
	
	public ControleurAccueil() throws DAOException{
		catalogDAO = FabriqueAbstraiteDAO.getInstance().createCatalogueDAO();
		
		List<I_Catalogue> listeCatalogs = catalogDAO.readAll();
		int[] nombresProduits   = new int[listeCatalogs.size()];
		String[] nomsCatalogues = new String[listeCatalogs.size()];
		for(int i=0;i<listeCatalogs.size();i++){
			I_Catalogue caTemp = listeCatalogs.get(i);
			nomsCatalogues[i]  = caTemp.getNom();
			nombresProduits[i] = catalogDAO.getProductCount(caTemp);
		}
		cataloguesObserves = new CatalogueInfosObservables(nomsCatalogues,nombresProduits);
		cataloguesObserves.attacher(new FenetreAccueil());
		cataloguesObserves.avertir();
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
		
		try {
			FabriqueAbstraiteDAO.setInstance(new FabriqueDAORelationnel());
			ControleurAccueil.getInstance();
			
		} catch (DAOException exception) {
			JFrame frame = new JFrame("Erreur connexion");
			JOptionPane.showMessageDialog(frame,
					exception.getMessage(),
				    "Erreur !",
				    JOptionPane.WARNING_MESSAGE);
			exception.printStackTrace();
			System.exit(0);
		}
	}

}



